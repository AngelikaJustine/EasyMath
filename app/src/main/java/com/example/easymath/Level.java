package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class Level extends AppCompatActivity {

    TextView txtLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        txtLevel = findViewById(R.id.txtLevel);

        Typeface SolwayBold = Typeface.createFromAsset(getAssets(), "fonts/SolwayBold.ttf");
        txtLevel.setTypeface(SolwayBold);

    }
}
