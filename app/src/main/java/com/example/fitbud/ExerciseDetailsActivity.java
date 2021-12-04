package com.example.fitbud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fitbud.Model.ExerciseClass;

public class ExerciseDetailsActivity extends AppCompatActivity {

    TextView exerciseDescription;
    TextView exerciseName;
    Bundle extras;
    ExerciseClass exercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);


        extras = getIntent().getExtras();
        exercise = extras.getParcelable("exercise");

        exerciseDescription = findViewById(R.id.exercise_description);
        exerciseDescription.setText(exercise.getTextDescription());
        exerciseName = findViewById(R.id.exercise_name);
        exerciseName.setText(exercise.getName());

        generateRecordsTables();
    }

    public void generateRecordsTables(){

        Integer numberOfDays;
        Integer numberOfSets;

        numberOfDays = exercise.getRecords().size();
        for (int i = 0; i < numberOfDays; i++){
         System.out.println("Set number " + i + " " + exercise.getRecords().get(i).getSet());
        }
    }
}