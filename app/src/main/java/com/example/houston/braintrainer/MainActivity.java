package com.example.houston.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RelativeLayout MainLayout;
    Button startButton;
    TextView resultTextView;
    TextView timerTextView;
    TextView points;
    TextView sumTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;

    int score = 0;
    int qcount= 0;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    public void playAgain(View view){
        score = 0;
        qcount = 0;
        timerTextView.setText("30s");
        points.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished){
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }
            @Override
            public void onFinish(){
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");

                float pre2 = score *100/qcount;
                resultTextView.setText("Your Score "+Integer.toString(score)+" out of "+Integer.toString(qcount)+ ", precentage correct:"+Float.toString(pre2)+"%");

            }
        }.start();
    }
    public void generateQuestion(){
        button0 = (Button)findViewById(R.id.option1);
        button1 = (Button)findViewById(R.id.option2);
        button2 = (Button)findViewById(R.id.option3);
        button3 = (Button)findViewById(R.id.option4);
        Random rand = new Random();

        int a =rand.nextInt(21);
        int b = rand.nextInt(21);

        int sum = a+b;

        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        answers.clear();
        int incorrectAnswer;
        locationOfCorrectAnswer = rand.nextInt(4);
        for(int i=0; i<4; i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer==a+b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }
    public void chooseAnswer(View view){
         if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
             resultTextView.setText("Correct");
             score++;
         }else{
             resultTextView.setText("Incorrect");
         }
         qcount++;
         points.setText(Integer.toString(score)+"/"+Integer.toString(qcount));
         generateQuestion();

    }
    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        MainLayout = findViewById(R.id.MainLayout);
        MainLayout.setVisibility(RelativeLayout.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumTextView = findViewById(R.id.sumTextView);
        startButton = findViewById(R.id.startButton);
        playAgainButton = findViewById(R.id.playAgainButton);
        points = findViewById(R.id.pointsTextView);
        resultTextView = findViewById(R.id.resultTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgain(findViewById(R.id.playAgainButton));

    }
}
