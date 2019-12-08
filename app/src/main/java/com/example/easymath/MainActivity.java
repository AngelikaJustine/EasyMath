package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnquickmath, btnchallenge;
    ImageView logo, setting, rank, rule, soundOn, info;
    TextView close;
    Animation btnhomeanimation1, btnhomeanimation2, iconhomeanimatesetting, iconhomeanimaterank, iconhomeanimaterule;
    HomeWatcher mHomeWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        logo = findViewById(R.id.logo);

        setting = findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        rank = findViewById(R.id.rank);
        rule = findViewById(R.id.rule);
        btnquickmath = findViewById(R.id.btnquickmath);
        btnchallenge = findViewById(R.id.btnchallenge);

        btnhomeanimation1 = AnimationUtils.loadAnimation(this, R.anim.btnhomeanimate1);
        btnhomeanimation2 = AnimationUtils.loadAnimation(this, R.anim.btnhomeanimate2);
        iconhomeanimatesetting = AnimationUtils.loadAnimation(this, R.anim.iconhomeanimatesetting);
        iconhomeanimaterank = AnimationUtils.loadAnimation(this, R.anim.iconhomeanimaterank);
        iconhomeanimaterule = AnimationUtils.loadAnimation(this, R.anim.iconhomeanimaterule);

        btnquickmath.startAnimation(btnhomeanimation1);
        btnchallenge.startAnimation(btnhomeanimation2);

        setting.startAnimation(iconhomeanimatesetting);
        rule.startAnimation(iconhomeanimaterule);
        rank.startAnimation(iconhomeanimaterank);

        Typeface SolwayBold = Typeface.createFromAsset(getAssets(), "fonts/SolwayBold.ttf");
        btnquickmath.setTypeface(SolwayBold);
        btnchallenge.setTypeface(SolwayBold);

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

    void showDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.alert_dialog_setting, null);

        soundOn = view.findViewById(R.id.soundOn);
        info = view.findViewById(R.id.info);
        close = view.findViewById(R.id.close);

        soundOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                btnPlayClicked();
                if(mIsBound){
                    mServ.stopMusic();
                    mIsBound = false;
                    soundOn.setImageResource(R.drawable.soundoff);
                } else {
                    mServ.startMusic();
                    mIsBound = true;
                    soundOn.setImageResource(R.drawable.soundon);
                }
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCloseClicked();
            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.show();
    }

    public void btnQuickMathClicked(View view){
        Intent intent = new Intent(this, Level.class);
        startActivity(intent);
    }

    public void btnChallengeClicked(View view){
        Intent intent = new Intent(this, ChallengeActivity.class);
        startActivity(intent);
    }

//    public void settingClicked(){
//        Intent intent = new Intent(this, SettingActivity.class);
//        startActivity(intent);
//
//    }

    private void btnCloseClicked(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void rankClicked(View view){
        Intent intent = new Intent(this, RankActivity.class);
        startActivity(intent);
    }

    public void ruleClicked(View view){
        Intent intent = new Intent(this, RuleActivity.class);
        startActivity(intent);
    }


}
