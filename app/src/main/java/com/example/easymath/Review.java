package com.example.easymath;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Review extends AppCompatActivity {

    ArrayList<String> arrayOfIncorrect;

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        arrayOfIncorrect = getIntent().getStringArrayListExtra("listofincorrect");

        listView = findViewById(R.id.soalsalah);
        arrayAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1, arrayOfIncorrect);
        listView.setAdapter(arrayAdapter);

    }
}
