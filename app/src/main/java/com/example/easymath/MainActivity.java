package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnquickmath, btnchallenge;
    ImageView logo, rank, rule, soundOn, info;
    Animation btnhomeanimation1, btnhomeanimation2, iconhomeanimatesetting, iconhomeanimaterank, iconhomeanimaterule, infoanimate;
    HomeWatcher mHomeWatcher;

    SharedPreferences pref2;
    SharedPreferences.Editor editor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        soundOn = findViewById(R.id.soundOn);
        rank = findViewById(R.id.rank);
        rule = findViewById(R.id.rule);
        btnquickmath = findViewById(R.id.btnquickmath);
        btnchallenge = findViewById(R.id.btnchallenge);
        info = findViewById(R.id.info);

        btnhomeanimation1 = AnimationUtils.loadAnimation(this, R.anim.btnhomeanimate1);
        btnhomeanimation2 = AnimationUtils.loadAnimation(this, R.anim.btnhomeanimate2);
        iconhomeanimatesetting = AnimationUtils.loadAnimation(this, R.anim.iconhomeanimatesetting);
        iconhomeanimaterank = AnimationUtils.loadAnimation(this, R.anim.iconhomeanimaterank);
        iconhomeanimaterule = AnimationUtils.loadAnimation(this, R.anim.iconhomeanimaterule);
        infoanimate = AnimationUtils.loadAnimation(this, R.anim.infoanimate);

        btnquickmath.startAnimation(btnhomeanimation1);
        btnchallenge.startAnimation(btnhomeanimation2);

        soundOn.startAnimation(iconhomeanimatesetting);
        rule.startAnimation(iconhomeanimaterule);
        rank.startAnimation(iconhomeanimaterank);

        info.startAnimation(infoanimate);

        Typeface SolwayBold = Typeface.createFromAsset(getAssets(), "fonts/SolwayBold.ttf");
        btnquickmath.setTypeface(SolwayBold);
        btnchallenge.setTypeface(SolwayBold);

        pref2 = getSharedPreferences("MyPref2", Context.MODE_PRIVATE);
        editor2 = pref2.edit();

        //Bind Music Service
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);

        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
            @Override
            public void onHomeLongPressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
        });
        mHomeWatcher.startWatch();

        soundOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mIsBound){
                    mServ.stopMusic();
                    mIsBound = false;
                    soundOn.setImageResource(R.drawable.soundoff);

                    editor2.putBoolean("mIsBound", false);
                    editor2.commit();
                } else {
                    mServ.startMusic();
                    mIsBound = true;
                    soundOn.setImageResource(R.drawable.soundon);

                    editor2.putBoolean("mIsBound", true);
                    editor2.commit();
                }
            }
        });
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(pref2!=null) {
                    if (pref2.getBoolean("mIsBound", true)) {
//                musicOn();
                    }
                    else {
                        musicOff();
                    }
                }
            }
        }, 500);

    }

    private boolean mIsBound = false;
    private MusicService mServ;

    private ServiceConnection Scon = new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mServ != null) {
            mServ.resumeMusic();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isScreenOn();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //UNBIND Music Service
        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        stopService(music);

    }

    public void btnQuickMathClicked(View view){
        Intent intent = new Intent(this, Level.class);
        startActivity(intent);
    }

    public void btnChallengeClicked(View view){
        Intent intent = new Intent(this, ChallengeActivity.class);
        startActivity(intent);
    }

    public void rankClicked(View view){
        Intent intent = new Intent(this, RankActivity.class);
        startActivity(intent);
    }

    public void ruleClicked(View view){
        Intent intent = new Intent(this, RuleActivity.class);
        startActivity(intent);
    }

    public void infoclicked(View view) {
        Intent intent = new Intent(this, Info.class);
        startActivity(intent);
    }

    private void musicOn(){
        mServ.startMusic();
        mIsBound = true;
        soundOn.setImageResource(R.drawable.soundon);
    }

    private void musicOff(){
        mServ.stopMusic();
        mIsBound = false;
        soundOn.setImageResource(R.drawable.soundoff);
    }
}
