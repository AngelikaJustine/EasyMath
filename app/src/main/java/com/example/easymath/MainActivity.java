package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnquickmath, btnchallenge;
    ImageView logo, setting, rank, rule;
    Animation btnhomeanimation1, btnhomeanimation2, iconhomeanimatesetting, iconhomeanimaterank, iconhomeanimaterule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        setting = findViewById(R.id.setting);
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

    public void btnQuickMathClicked(View view){
        Intent intent = new Intent(this, Level.class);
        startActivity(intent);
    }

    public void btnChallengeClicked(View view){
        Intent intent = new Intent(this, ChallengeActivity.class);
        startActivity(intent);
    }

    public void settingClicked(View view){
        Intent intent = new Intent(this, SettingActivity.class);
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


}
