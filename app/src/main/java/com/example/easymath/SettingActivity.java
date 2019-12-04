package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    TextView txtSetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        txtSetting = findViewById(R.id.txtSetting);

        Typeface SolwayBold = Typeface.createFromAsset(getAssets(), "fonts/SolwayBold.ttf");
        txtSetting.setTypeface(SolwayBold);
    }
}
