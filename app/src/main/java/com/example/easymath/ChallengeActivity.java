package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Locale;

public class ChallengeActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 6000;

    TextView countDown, ofChallenge, txttrueanswerchallenge, txtallquestionchallenge;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        countDown = findViewById(R.id.countDown);
        txtallquestionchallenge = findViewById(R.id.txtallquestionchallenge);
        txttrueanswerchallenge = findViewById(R.id.txttrueanswerchallenge);
        ofChallenge = findViewById(R.id.ofChallenge);

        Typeface SolwayBold = Typeface.createFromAsset(getAssets(), "fonts/SolwayRegular.ttf");
        Typeface SolwayMedium = Typeface.createFromAsset(getAssets(), "fonts/SolwayMedium.ttf");

        countDown.setTypeface(SolwayBold);
        txtallquestionchallenge.setTypeface(SolwayMedium);
        txttrueanswerchallenge.setTypeface(SolwayMedium);
        ofChallenge.setTypeface(SolwayMedium);

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
            }
        }.start();

        mTimerRunning = true;
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        countDown.setText(timeLeftFormatted);
    }
}
