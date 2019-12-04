package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class ChallengeActivity extends AppCompatActivity {

    TextView countDown;
//    public int counter;
//    private long mTimeLeftInMillis = 600000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        countDown = findViewById(R.id.countDown);

        Typeface SolwayBold = Typeface.createFromAsset(getAssets(), "fonts/SolwayRegular.ttf");
        countDown.setTypeface(SolwayBold);

//        CountDownTimer mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                mTimeLeftInMillis = millisUntilFinished;
//                updateCountDownText();
//            }
//
//            @Override
//            public void onFinish() {
//                mTimerRunning = false;
//                mButtonStartPause.setText("Start");
//                mButtonStartPause.setVisibility(View.INVISIBLE);
//                mButtonReset.setVisibility(View.VISIBLE);
//            }
//        }.start();
    }
}
