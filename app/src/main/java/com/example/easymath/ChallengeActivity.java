package com.example.easymath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class ChallengeActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 60000;

    private TextView countDown, ofChallenge, txttrueanswerchallenge, txtallquestionchallenge;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private TextView challengequestion;
    private Button btnans1, btnans2, btnans3, btnans4;

    private int numb1, numb2, numb3, s1, s2;
    private int total;
    private int totalQuestion, totalRight;
    private String question;

    private Random random = new Random();

    Context context;
    CharSequence textRight, textFalse;
    int duration;
    Toast toast;

    ArrayList<String> arrayOfIncorrect;

    private DatabaseReference root;
    private String name, score, inputname;
    private ArrayList<String> data;
    private ArrayList<ArrayList> allData;
    private int h1, h2, h3;
    private String name1fromdata, score1fromdata, name2fromdata, score2fromdata, name3fromdata, score3fromdata;
    Map<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        context = getApplicationContext();
        duration = 500;
        textRight = "Correct";
        textFalse = "Incorrect";

        arrayOfIncorrect = new ArrayList<>();

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

//        FOR HIGHSCORE FROM FIREBASE
        root = FirebaseDatabase.getInstance().getReference();
        gethighscore();

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

                if(totalRight * 10 <= h3){
                    goToReview();
                }
                else{
//                    updatehighscore("Feo");
                    inputName();
                }
            }
        }.start();

        mTimerRunning = true;

    }

    private void gethighscore() {

        root = FirebaseDatabase.getInstance().getReference().getRoot();

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                allData = new ArrayList<>();
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();

                while (items.hasNext()){
                    data = new ArrayList<>();
                    DataSnapshot item = items.next();
                    name = item.child("name").getValue().toString();
                    score = item.child("score").getValue().toString();
                    data.add(name);
                    data.add(score);
                    allData.add(data);
                }

                ArrayList<String> data1 = allData.get(0);
                ArrayList<String> data2 = allData.get(1);
                ArrayList<String> data3 = allData.get(2);

                name1fromdata = data1.get(0);
                score1fromdata = data1.get(1);

                name2fromdata = data2.get(0);
                score2fromdata = data2.get(1);

                name3fromdata = data3.get(0);
                score3fromdata = data3.get(1);

                h1 = Integer.parseInt(score1fromdata);
                h2 = Integer.parseInt(score2fromdata);
                h3 = Integer.parseInt(score3fromdata);

                checkPosition();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void checkPosition() {

        String temp2;
        int temp1;

        if(h1 >= h2 && h1 >= h3){
            if(h2 < h3) {
                temp1 = h3;
                h3 = h2;
                h2 = temp1;

                temp2 = name3fromdata;
                name3fromdata = name2fromdata;
                name2fromdata = temp2;

            }
        }

        else if(h2 >= h1 && h2 >= h3){

            temp1 = h2;
            h2 = h1;
            h1 = temp1;

            temp2 = name2fromdata;
            name2fromdata = name1fromdata;
            name1fromdata = temp2;

            if(h2 < h3) {
                temp1 = h3;
                h3 = h2;
                h2 = temp1;

                temp2 = name3fromdata;
                name3fromdata = name2fromdata;
                name2fromdata = temp2;

            }
        }

        else{
            temp1 = h3;
            h3 = h1;
            h1 = temp1;

            temp2 = name3fromdata;
            name3fromdata = name1fromdata;
            name1fromdata = temp2;

            if(h2 < h3) {
                temp1 = h3;
                h3 = h2;
                h2 = temp1;

                temp2 = name3fromdata;
                name3fromdata = name2fromdata;
                name2fromdata = temp2;

            }
        }

    }

    private void updatehighscore(String newName) {

        if(totalRight * 10 > h1) {
            updaterank1(newName);
        }

        else if (totalRight * 10 > h2){
            updaterank2(newName);
        }

        else if(totalRight * 10 > h3){
            updaterank3(newName);
        }

    }

    private void updaterank3(String newName) {
        map = new HashMap<String, Object>();

        root.child("three").removeValue();
        map.put("name", newName);
        map.put("score", totalRight * 10);
        root.child("three").updateChildren(map);
    }

    private void updaterank2(String newName) {

        map = new HashMap<String, Object>();

        root.child("two").removeValue();
        map.put("name", newName);
        map.put("score", totalRight * 10);
        root.child("two").updateChildren(map);

        root.child("three").removeValue();
        map.put("name", name2fromdata);
        map.put("score", h2);
        root.child("three").updateChildren(map);
    }

    private void updaterank1(String newName) {
        map = new HashMap<String, Object>();

        root.child("one").removeValue();
        map.put("name", newName);
        map.put("score", totalRight * 10);
        root.child("one").updateChildren(map);

        root.child("two").removeValue();
        map.put("name", name1fromdata);
        map.put("score", h1);
        root.child("two").updateChildren(map);

        root.child("three").removeValue();
        map.put("name", name2fromdata);
        map.put("score", h2);
        root.child("three").updateChildren(map);
    }

    private void backToMain(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.alert_dialog_back, null);

        TextView score = view.findViewById(R.id.score);
        int totalScore = totalRight * 10;
        score.setText(String.valueOf(totalScore));
        Button playButton = view.findViewById(R.id.playButton);
        Button quitButton = view.findViewById(R.id.quitButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnQuitClicked();
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPlayClicked();
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

    private void goToReview() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View views = inflater.inflate(R.layout.alert_dialog, null);

        TextView score = views.findViewById(R.id.score);
        int totalScore = totalRight * 10;
        score.setText(String.valueOf(totalScore));
        Button reviewButton = views.findViewById(R.id.reviewButton);
        Button playButton = views.findViewById(R.id.playButton);
        Button quitButton = views.findViewById(R.id.quitButton);

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                review();
            }
        });

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
                .setView(views)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.show();

    }

    private void review(){
        Intent intent = new Intent(this, Review.class);
        intent.putStringArrayListExtra("listofincorrect", arrayOfIncorrect);
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

        if(s2 >= 2 && s1 < 2){
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

        question = numb1 + printQuestionSymbol(s1) + numb2 + printQuestionSymbol(s2) + numb3;
        challengequestion = findViewById(R.id.challengequestion);
        challengequestion.setText(question);

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
            toast = Toast.makeText(context, textRight, duration);
            toast.show();
        }
        else{
            btn.setBackgroundColor(Color.rgb(255, 88, 88));
            toast = Toast.makeText(context, textFalse, duration);
            toast.show();
            question = question + " = " + total;
            arrayOfIncorrect.add(question);
        }

        final Button finalBtn = btn;
        updateTotalQuestion();



    }

    private void updateTotalQuestion(){
        txtallquestionchallenge = findViewById(R.id.txtallquestionchallenge);
        txttrueanswerchallenge = findViewById(R.id.txttrueanswerchallenge);

        txtallquestionchallenge.setText(String.valueOf(totalQuestion));
        txttrueanswerchallenge.setText(String.valueOf(totalRight));
    }

    public void endGame(View view) {
        mCountDownTimer.cancel();
        if(totalRight * 10 <= h3){
            backToMain();
        }
        else{
            inputName();

        }
    }

    private void inputName(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View views = inflater.inflate(R.layout.alert_dialog_highscore, null);

        TextView scr = views.findViewById(R.id.score);
        scr.setText(String.valueOf(totalRight * 10));

        Button enter = views.findViewById(R.id.enter);
        final EditText nameinput = views.findViewById(R.id.nameinput);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputname = nameinput.getText().toString();

                if(inputname == null){
                    inputname = "Unknown";
                }

                updatehighscore(inputname);
            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(views)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.show();
        
    }
}
