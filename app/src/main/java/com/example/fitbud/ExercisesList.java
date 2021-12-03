package com.example.fitbud;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.fitbud.Model.ExerciseClass;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class ExercisesList extends AppCompatActivity {
    private RecyclerView exerciseRV;
    private ArrayList<ExerciseClass> exerciseClassArrayList;
    private String category;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_list);

        exerciseRV = findViewById(R.id.RVExercises);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);

        exerciseClassArrayList = new ArrayList<>();
        readJSON();

        ExercisesAdapter exercisesAdapter = new ExercisesAdapter(this,exerciseClassArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        exerciseRV.setLayoutManager(linearLayoutManager);
        exerciseRV.setAdapter(exercisesAdapter);

    }



    public ArrayList<ExerciseClass> readJSON(){
        Gson gson = new Gson();
        Reader reader;
        category = getIntent().getStringExtra("category");

        switch(category){
            case "chest":
            reader = new InputStreamReader(getResources().openRawResource(R.raw.chest_exercises));
                break;
            case "triceps":
                reader = new InputStreamReader(getResources().openRawResource(R.raw.triceps_exercises));
                break;
                default:
                throw new IllegalStateException("Unexpected value: " + category);
        }
        BufferedReader bufferedReader = new BufferedReader(reader);

        ExerciseClass[] exercises = gson.fromJson(bufferedReader, ExerciseClass[].class );

        for(ExerciseClass exerciseClass : exercises) {
            exerciseClassArrayList.add(exerciseClass);

        }


        return exerciseClassArrayList;
        }
}
