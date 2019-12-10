package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class ChallengeActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 10000;

    private TextView countDown, ofChallenge, txttrueanswerchallenge, txtallquestionchallenge;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private TextView challengequestion;
    private Button btnans1, btnans2, btnans3, btnans4;

    private int numb1, numb2, numb3, s1, s2;
    private int total;
    private int totalQuestion, totalRight;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        countDown = findViewById(R.id.countDown);
        txtallquestionchallenge = findViewById(R.id.txtallquestionchallenge);
        txttrueanswerchallenge = findViewById(R.id.txttrueanswerchallenge);
        ofChallenge = findViewById(R.id.ofChallenge);

        challengequestion = findViewById(R.id.challengequestion);
        btnans1 = findViewById(R.id.btnans1);
        btnans2 = findViewById(R.id.btnans2);
        btnans3 = findViewById(R.id.btnans3);
        btnans4 = findViewById(R.id.btnans4);

        totalQuestion = 0;
        totalRight = 0;

        Typeface SolwayRegular = Typeface.createFromAsset(getAssets(), "fonts/SolwayRegular.ttf");
        Typeface SolwayMedium = Typeface.createFromAsset(getAssets(), "fonts/SolwayMedium.ttf");
        Typeface SolwayBold = Typeface.createFromAsset(getAssets(), "fonts/SolwayBold.ttf");

        countDown.setTypeface(SolwayRegular);
        txtallquestionchallenge.setTypeface(SolwayMedium);
        txttrueanswerchallenge.setTypeface(SolwayMedium);
        ofChallenge.setTypeface(SolwayMedium);
        challengequestion.setTypeface(SolwayBold);
        btnans1.setTypeface(SolwayBold);
        btnans2.setTypeface(SolwayBold);
        btnans3.setTypeface(SolwayBold);
        btnans4.setTypeface(SolwayBold);

        playgamechallenge();

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                mTimerRunning = false;
                gameOver();

            }
        }.start();

        mTimerRunning = true;

    }

    private void gameOver() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.alert_dialog, null);

        TextView score = view.findViewById(R.id.score);
        int totalScore = totalRight * 10;
        score.setText(String.valueOf(totalScore));
        Button playButton = view.findViewById(R.id.playButton);
        Button quitButton = view.findViewById(R.id.quitButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPlayClicked();
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnQuitClicked();
            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.show();
    }

    private void btnPlayClicked(){
        Intent intent = new Intent(this, ChallengeActivity.class);
        startActivity(intent);
        finish();
    }

    private void btnQuitClicked(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        countDown.setText(timeLeftFormatted);
    }

    public void playgamechallenge(){
        totalQuestion = totalQuestion + 1;
        btnans1 = findViewById(R.id.btnans1);
        btnans2 = findViewById(R.id.btnans2);
        btnans3 = findViewById(R.id.btnans3);
        btnans4 = findViewById(R.id.btnans4);
        btnans1.setEnabled(true);
        btnans2.setEnabled(true);
        btnans3.setEnabled(true);
        btnans4.setEnabled(true);

        numb1 = random.nextInt(10) + 5;
        numb2 = random.nextInt(12) + 3;
        numb3 = random.nextInt(13) + 2;
        s1 = random.nextInt(4);
        s2 = random.nextInt(4);

        while(s2 == s1){
            s2 = random.nextInt(4);
        }

        if(s1 == 3){
            while(checkPrime(numb1)){
                numb1 = random.nextInt(10) + 5;
            }
            float x = numb1;
            float temp = x / numb2;
            while(temp != Math.rint(temp) ){
                numb2 = random.nextInt(12) + 3;
                temp = x / numb2;
            }
        }

        else if(s1 < 2 && s2 == 3){
            while(checkPrime(numb2)){
                numb2 = random.nextInt(12) + 3;
            }
            float x = numb2;
            float temp = x / numb3;
            while(temp != Math.rint(temp) ){
                numb3 = random.nextInt(13) + 2;
                temp = x / numb3;
            }
        }
        else if(s1 == 2 && s2 == 3){
            while(checkPrime(numb1 * numb2)){
                numb1 = random.nextInt(10) + 5;
                numb2 = random.nextInt(12) + 3;
            }
            float x = numb1 * numb2;
            float temp = x / numb3;
            while(temp != Math.rint(temp) ){
                numb3 = random.nextInt(13) + 2;
                temp = x / numb3;
            }
        }

        printQuestion();

        if(s2 >= 3 && s1 < 3){
            int temp = countsubtotal(numb2, numb3, s2);
            total = countsubtotal(numb1, temp, s1);
        }
        else {
            int temp = countsubtotal(numb1, numb2, s1);
            total = countsubtotal(temp, numb3, s2);
        }

        int pos = random.nextInt(4);

        printAnswer(total, pos);

    }

    private int countsubtotal(int a, int b, int symbol){

        if(symbol == 0){
            return a + b;
        }
        else if(symbol == 1){
            return a - b;
        }
        else if(symbol == 2){
            return a * b;
        }
        else{
            return a / b;
        }
    }

    private void printQuestion(){

        String temp = numb1 + printQuestionSymbol(s1) + numb2 + printQuestionSymbol(s2) + numb3;
        challengequestion = findViewById(R.id.challengequestion);
        challengequestion.setText(temp);

    }

    private String printQuestionSymbol(int s){

        if(s == 0){
            return " + ";
        }
        else if(s == 1){
            return " - ";
        }
        else if(s == 2){
            return " * ";
        }
        else{
            return " / ";
        }

    }

    private void printAnswer(int total, int pos){

        btnans1 = findViewById(R.id.btnans1);
        btnans2 = findViewById(R.id.btnans2);
        btnans3 = findViewById(R.id.btnans3);
        btnans4 = findViewById(R.id.btnans4);

        int temp1 = random.nextInt(10) + 1;
        int temp2 = random.nextInt(5) + 1;
        int temp3 = random.nextInt(12) + 1;
        int x = random.nextInt(2);
        int y = random.nextInt(2);
        int z = random.nextInt(2);

        if(pos == 0){
            btnans1.setText(String.valueOf(total));
            btnans2.setText(String.valueOf(countsubtotal(total, temp1, x)));
            btnans3.setText(String.valueOf(countsubtotal(total, temp2, y)));
            btnans4.setText(String.valueOf(countsubtotal(total, temp3, z)));
        }
        else if(pos == 1){
            btnans2.setText(String.valueOf(total));
            btnans1.setText(String.valueOf(countsubtotal(total, temp1, x)));
            btnans3.setText(String.valueOf(countsubtotal(total, temp2, y)));
            btnans4.setText(String.valueOf(countsubtotal(total, temp3, z)));
        }
        else if(pos == 2){
            btnans3.setText(String.valueOf(total));
            btnans1.setText(String.valueOf(countsubtotal(total, temp1, x)));
            btnans2.setText(String.valueOf(countsubtotal(total, temp2, y)));
            btnans4.setText(String.valueOf(countsubtotal(total, temp3, z)));
        }
        else{
            btnans4.setText(String.valueOf(total));
            btnans1.setText(String.valueOf(countsubtotal(total, temp1, x)));
            btnans2.setText(String.valueOf(countsubtotal(total, temp2, y)));
            btnans3.setText(String.valueOf(countsubtotal(total, temp3, z)));
        }
    }

    private boolean checkPrime(int number){
        for(int i=2; i<=number/2; i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
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


        if(values == total) {
            btn.setBackgroundColor(Color.rgb(142, 255, 142));
            totalRight = totalRight + 1;
        }
        else{
            btn.setBackgroundColor(Color.rgb(255, 88, 88));

        }

        final Button finalBtn = btn;
        updateTotalQuestion();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finalBtn.setBackgroundColor(Color.rgb(253, 242, 254));
                playgamechallenge();
            }
        }, 500);

    }

    private void updateTotalQuestion(){
        txtallquestionchallenge = findViewById(R.id.txtallquestionchallenge);
        txttrueanswerchallenge = findViewById(R.id.txttrueanswerchallenge);

        txtallquestionchallenge.setText(String.valueOf(totalQuestion));
        txttrueanswerchallenge.setText(String.valueOf(totalRight));
    }
}
