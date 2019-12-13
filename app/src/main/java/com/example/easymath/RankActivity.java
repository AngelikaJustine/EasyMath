package com.example.easymath;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static java.lang.System.in;

public class RankActivity extends AppCompatActivity {

    TextView name1, name2, name3, score1, score2, score3;
    private DatabaseReference root;

    private String name, score;
    private ArrayList<String> data;
    private ArrayList<ArrayList> allData;

    private int h1, h2, h3;

    private String name1fromdata, score1fromdata, name2fromdata, score2fromdata, name3fromdata, score3fromdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        root = FirebaseDatabase.getInstance().getReference();

        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);
        score1 = findViewById(R.id.score1);
        score2 = findViewById(R.id.score2);
        score3 = findViewById(R.id.score3);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                allData = new ArrayList<>();
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();

                while (items.hasNext()){
                    data = new ArrayList<>();
                    DataSnapshot item = items.next();
                    name = item.child("name").getValue().toString();
                    score = item.child("score").getValue().toString();
                    data.add(name);
                    data.add(score);
                    allData.add(data);
                }

                ArrayList<String> data1 = allData.get(0);
                ArrayList<String> data2 = allData.get(1);
                ArrayList<String> data3 = allData.get(2);

                name1fromdata = data1.get(0);
                score1fromdata = data1.get(1);

                name2fromdata = data2.get(0);
                score2fromdata = data2.get(1);

                name3fromdata = data3.get(0);
                score3fromdata = data3.get(1);

                h1 = Integer.parseInt(score1fromdata);
                h2 = Integer.parseInt(score2fromdata);
                h3 = Integer.parseInt(score3fromdata);

                printbaseonscore();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void printbaseonscore() {
        if(h1 >= h2 && h1 >= h3){
            name1.setText(name1fromdata);
            score1.setText(score1fromdata);

            if(h2 >= h3){
                name2.setText(name2fromdata);
                score2.setText(score2fromdata);

                name3.setText(name3fromdata);
                score3.setText(score3fromdata);
            }else{
                name2.setText(name3fromdata);
                score2.setText(score3fromdata);

                name3.setText(name2fromdata);
                score3.setText(score2fromdata);
            }
        }
        else if(h2 >= h1 && h2 >= h3){
            name1.setText(name2fromdata);
            score1.setText(score2fromdata);

            if(h1 >= h3){
                name2.setText(name1fromdata);
                score2.setText(score1fromdata);

                name3.setText(name3fromdata);
                score3.setText(score3fromdata);
            }else{
                name2.setText(name3fromdata);
                score2.setText(score3fromdata);

                name3.setText(name1fromdata);
                score3.setText(score1fromdata);
            }
        }else{
            name1.setText(name3fromdata);
            score1.setText(score3fromdata);

            if(h1 >= h2){
                name2.setText(name1fromdata);
                score2.setText(score1fromdata);

                name3.setText(name2fromdata);
                score3.setText(score2fromdata);
            }else{
                name2.setText(name2fromdata);
                score2.setText(score2fromdata);

                name3.setText(name1fromdata);
                score3.setText(score1fromdata);
            }
        }
    }


}