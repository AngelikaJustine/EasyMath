package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LevelOne extends AppCompatActivity {

    private TextView level1question, totalcoin;
    private Button btnans1, btnans2, btnans3, btnans4, numberQuestion;

    private int numb1, numb2;
    private int answer, totalQuestion;

    private Random random = new Random();

    boolean NextQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);

        level1question = findViewById(R.id.level1question);
        totalcoin = findViewById(R.id.totalcoin);
        btnans1 = findViewById(R.id.btnans1);
        btnans2 = findViewById(R.id.btnans1);
        btnans3 = findViewById(R.id.btnans3);
        btnans4 = findViewById(R.id.btnans4);
        numberQuestion = findViewById(R.id.numberQuestion);

        NextQuestion = true;

        playgame();
    }

    public void playgame(){
        level1question = findViewById(R.id.level1question);

        btnans1 = findViewById(R.id.btnans1);
        btnans2 = findViewById(R.id.btnans2);
        btnans3 = findViewById(R.id.btnans3);
        btnans4 = findViewById(R.id.btnans4);
        btnans1.setEnabled(true);
        btnans2.setEnabled(true);
        btnans3.setEnabled(true);
        btnans4.setEnabled(true);

        numb1 = random.nextInt(40) + 5;
        numb2 = random.nextInt(20) + 5;

        answer = numb1 + numb2;

        String temp = numb1 + " + " + numb2;
        level1question.setText(temp);

        printAnswer();

    }

    private void printAnswer(){
        List<Integer> list = new ArrayList<Integer>();

        int rand1 = random.nextInt(10) + 5;
        int rand2 = random.nextInt(5) + 5;
        int rand3 = random.nextInt(10);

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
                values = Double.parseDouble(btn.getText().toString());
                break;

            case R.id.btnans2:
                btn = findViewById(R.id.btnans2);
                values = Double.parseDouble(btn.getText().toString());
                break;

            case R.id.btnans3:
                btn = findViewById(R.id.btnans3);
                values = Double.parseDouble(btn.getText().toString());
                break;

            case R.id.btnans4:
                btn = findViewById(R.id.btnans4);
                values = Double.parseDouble(btn.getText().toString());
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

        final Button finalBtn = btn;

        if(NextQuestion) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finalBtn.setBackgroundColor(Color.rgb(253, 242, 254));
                    playgame();
                }
            }, 500);

        }
        else {
            // Pop up for stop game
        }

    }



}
