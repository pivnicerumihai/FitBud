package com.example.fitbud;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.fitbud.Model.ExerciseClass;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.Reader;

public class ExercisesActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialToolbar toolbar;
    Button btn_chest;
    Button btn_triceps;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        btn_chest = findViewById(R.id.btn_chest);
        btn_chest.setOnClickListener(this::onClick);
        btn_triceps = findViewById(R.id.btn_triceps);
        btn_triceps.setOnClickListener(this::onClick);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);




    }


    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()){
            case R.id.btn_chest:
                intent = new Intent(this,ExercisesList.class);
                intent.putExtra("category", "chest");
                startActivity(intent);
                break;
            case R.id.btn_triceps:
                intent = new Intent(this,ExercisesList.class);
                intent.putExtra("category", "triceps");
                startActivity(intent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}

