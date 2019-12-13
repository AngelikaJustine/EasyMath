package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Level extends AppCompatActivity {

    TextView txtLevel;
    Button lvl1, lvl2, lvl3, lvl4, lvl5;

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
