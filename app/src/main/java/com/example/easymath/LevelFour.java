package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LevelFour extends AppCompatActivity {

    private TextView level1question, totalcoin;
    private Button btnans1, btnans2, btnans3, btnans4, numberQuestion, skipquestion, halfanswer;

    private int numb1, numb2;
    private int answer, totalQuestion, coin;

    private Random random = new Random();

    boolean NextQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_four);

        NextQuestion = true;
        totalQuestion = 1;
        coin = 10;

        playgame();
    }

    public void playgame(){
        level1question = findViewById(R.id.level1question);
        numberQuestion = findViewById(R.id.numberQuestion);

        numberQuestion.setText(String.valueOf(totalQuestion));

        checkcoin();

        buttonresetall();

        numb1 = random.nextInt(190) + 10;
        numb2 = random.nextInt(95) + 5;

        while(checkPrime(numb1)){
            numb1 = random.nextInt(190) + 10;
        }
        float x = numb1;
        float tmp = x / numb2;
        while(tmp != Math.rint(tmp)){
            numb2 = random.nextInt(95) + 5;
            tmp = x / numb2;
        }

        answer = numb1 / numb2;

        String temp = numb1 + " / " + numb2;
        level1question.setText(temp);

        printAnswer();

    }

    private boolean checkPrime(int number){
        for(int i=2; i<=number/2; i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }

    private void buttonresetall() {
        btnans1 = findViewById(R.id.btnans1);
        btnans2 = findViewById(R.id.btnans2);
        btnans3 = findViewById(R.id.btnans3);
        btnans4 = findViewById(R.id.btnans4);

        btnans1.setClickable(true);
        btnans2.setClickable(true);
        btnans3.setClickable(true);
        btnans4.setClickable(true);

        btnans1.setEnabled(true);
        btnans2.setEnabled(true);
        btnans3.setEnabled(true);
        btnans4.setEnabled(true);

        btnans1.setBackgroundColor(Color.rgb(253, 242, 254));
        btnans2.setBackgroundColor(Color.rgb(253, 242, 254));
        btnans3.setBackgroundColor(Color.rgb(253, 242, 254));
        btnans4.setBackgroundColor(Color.rgb(253, 242, 254));
    }

    private void checkcoin() {
        skipquestion = findViewById(R.id.skipquestion);
        halfanswer = findViewById(R.id.halfanswer);

        if(coin < 3){
            skipquestion.setClickable(false);
            skipquestion.setAlpha(0);
        }

        if(coin < 2){
            halfanswer.setClickable(false);
            halfanswer.setAlpha(0);
        }

        totalcoin = findViewById(R.id.totalcoin);
        totalcoin.setText(String.valueOf(coin));
    }

    private void printAnswer(){
        List<Integer> list = new ArrayList<Integer>();

        int rand1 = random.nextInt(10) + 5;
        int rand2 = random.nextInt(5) + 5;
        int rand3 = random.nextInt(10) + 1;

        btnans1 = findViewById(R.id.btnans1);
        btnans2 = findViewById(R.id.btnans2);
        btnans3 = findViewById(R.id.btnans3);
        btnans4 = findViewById(R.id.btnans4);

        list.add(answer);
        list.add(answer+rand1);
        list.add(answer-rand2);
        list.add(answer+rand3);

        Collections.shuffle(list);

        btnans1.setText(String.valueOf(list.get(0)));
        btnans2.setText(String.valueOf(list.get(1)));
        btnans3.setText(String.valueOf(list.get(2)));
        btnans4.setText(String.valueOf(list.get(3)));

    }

    public void btnClicked(View view){
        btnans1 = findViewById(R.id.btnans1);
        btnans2 = findViewById(R.id.btnans2);
        btnans3 = findViewById(R.id.btnans3);
        btnans4 = findViewById(R.id.btnans4);
        btnans1.setEnabled(false);
        btnans2.setEnabled(false);
        btnans3.setEnabled(false);
        btnans4.setEnabled(false);

        Button btn = findViewById(R.id.btnans1);
        double values = 0;

        switch (view.getId()) {

            case R.id.btnans1:
                btn = findViewById(R.id.btnans1);
                values = Integer.parseInt(btn.getText().toString());
                break;

            case R.id.btnans2:
                btn = findViewById(R.id.btnans2);
                values = Integer.parseInt(btn.getText().toString());
                break;

            case R.id.btnans3:
                btn = findViewById(R.id.btnans3);
                values = Integer.parseInt(btn.getText().toString());
                break;

            case R.id.btnans4:
                btn = findViewById(R.id.btnans4);
                values = Integer.parseInt(btn.getText().toString());
                break;
        }


        if(values == answer) {
            btn.setBackgroundColor(Color.rgb(142, 255, 142));
//            totalcoin = totalRight + 1;
        }
        else{
            btn.setBackgroundColor(Color.rgb(255, 88, 88));
            NextQuestion = false;
        }

        if(totalQuestion == 10){
            NextQuestion = false;
        }

        final Button finalBtn = btn;

        if(NextQuestion) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finalBtn.setBackgroundColor(Color.rgb(253, 242, 254));
                    updateAfterCorrect();
                    playgame();
                }
            }, 500);

        }
        else {
            if(totalQuestion != 10){
                // Pop up for stop game before correct 10
                backtolevel();
            }
            else{
                // Pop up for stop game to next level
                nextlevel();
            }

        }

    }

    private void nextlevel() {
        Intent intent = new Intent(this, LevelFive.class);
        startActivity(intent);
        finish();
    }

    private void backtolevel(){
        Intent intent = new Intent(this, Level.class);
        startActivity(intent);
        finish();
    }

    private void updateAfterCorrect(){
        coin = coin + 1;
        totalQuestion = totalQuestion + 1;
    }


    public void halfClicked(View view) {
        coin = coin - 2;

        checkcoin();

        btnans1 = findViewById(R.id.btnans1);
        btnans2 = findViewById(R.id.btnans2);
        btnans3 = findViewById(R.id.btnans3);

        int value1 = Integer.parseInt(btnans1.getText().toString());
        int value2 = Integer.parseInt(btnans2.getText().toString());
        int value3 = Integer.parseInt(btnans3.getText().toString());

        if(value1 == answer){
            chooseoff(0);
        }
        else if(value2 == answer){
            chooseoff(1);
        }
        else if(value3 == answer){
            chooseoff(2);
        }
        else{
            chooseoff(3);
        }

    }

    private void chooseoff(int x) {
        int off1 = random.nextInt(4);

        while (off1 == x){
            off1 = random.nextInt(4);
        }

        int off2 = random.nextInt(4);

        while (off2 == x){
            off2 = random.nextInt(4);
        }

        while (off2 == off1){
            off2 = random.nextInt(4);
        }

        ChangeColor(off1);
        ChangeColor(off2);
    }

    public void ChangeColor(int p){
        btnans1 = findViewById(R.id.btnans1);
        btnans2 = findViewById(R.id.btnans2);
        btnans3 = findViewById(R.id.btnans3);
        btnans4 = findViewById(R.id.btnans4);

        if(p == 0) {
            btnans1.setClickable(false);
            btnans1.setBackgroundColor(Color.rgb(255, 88, 88));
        }
        else if(p == 1){
            btnans2.setClickable(false);
            btnans2.setBackgroundColor(Color.rgb(255, 88, 88));
        }
        else if(p == 2){
            btnans3.setClickable(false);
            btnans3.setBackgroundColor(Color.rgb(255, 88, 88));
        }
        else if(p == 3){
            btnans4.setClickable(false);
            btnans4.setBackgroundColor(Color.rgb(255, 88, 88));
        }
    }

    public void skipClicked(View view) {
        coin = coin - 3;
        playgame();
    }
}
