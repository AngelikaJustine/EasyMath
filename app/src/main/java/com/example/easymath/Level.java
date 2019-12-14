package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Level extends AppCompatActivity {

    TextView txtLevel;
    Button lvl1, lvl2, lvl3, lvl4, lvl5;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        txtLevel = findViewById(R.id.txtLevel);
        lvl1 = findViewById(R.id.lvl1);
        lvl2 = findViewById(R.id.lvl2);
        lvl3 = findViewById(R.id.lvl3);
        lvl4 = findViewById(R.id.lvl4);
        lvl5 = findViewById(R.id.lvl5);

        Typeface SolwayBold = Typeface.createFromAsset(getAssets(), "fonts/SolwayBold.ttf");
        txtLevel.setTypeface(SolwayBold);
        lvl1.setTypeface(SolwayBold);
        lvl2.setTypeface(SolwayBold);
        lvl3.setTypeface(SolwayBold);
        lvl4.setTypeface(SolwayBold);
        lvl5.setTypeface(SolwayBold);

        pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        editor = pref.edit();
        int scoreLv1 = 0;
        int scoreLv2 = 0;
        int scoreLv3 = 0;
        int scoreLv4 = 0;

        if(pref!=null) {
            scoreLv1 = pref.getInt("scoreLv1", 0);
            scoreLv2 = pref.getInt("scoreLv2", 0);
            scoreLv3 = pref.getInt("scoreLv3", 0);
            scoreLv4 = pref.getInt("scoreLv4", 0);
        }
        if (scoreLv1 == 10) {
            lvl2.setEnabled(true);
        }
        if (scoreLv2 == 10) {
            lvl3.setEnabled(true);
        }
        if (scoreLv3 == 10) {
            lvl4.setEnabled(true);
        }
        if (scoreLv4 == 10) {
            lvl5.setEnabled(true);
        }
    }

    public void btnLvl1Clicked(View view) {
        Intent intent = new Intent(this, LevelOne.class);
        startActivity(intent);
        finish();
    }

    public void btnLvl2Clicked(View view) {
        Intent intent = new Intent(this, LevelTwo.class);
        startActivity(intent);
        finish();
    }

    public void btnLvl3Clicked(View view) {
        Intent intent = new Intent(this, LevelThree.class);
        startActivity(intent);
        finish();
    }

    public void btnLvl4Clicked(View view) {
        Intent intent = new Intent(this, LevelFour.class);
        startActivity(intent);
        finish();
    }

    public void btnLvl5Clicked(View view) {
        Intent intent = new Intent(this, LevelFive.class);
        startActivity(intent);
        finish();
    }
}
