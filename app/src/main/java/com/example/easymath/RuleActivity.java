package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class RuleActivity extends AppCompatActivity {

    TextView txtRule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);

        txtRule = findViewById(R.id.txtRule);

        Typeface SolwayBold = Typeface.createFromAsset(getAssets(), "fonts/SolwayBold.ttf");
        txtRule.setTypeface(SolwayBold);
    }
}
