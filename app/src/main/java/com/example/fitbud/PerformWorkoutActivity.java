package com.example.fitbud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fitbud.Model.ExerciseClass;
import com.example.fitbud.Model.StretchClass;
import com.example.fitbud.Model.WorkoutClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PerformWorkoutActivity extends AppCompatActivity {

    private WorkoutClass workout;
    private RecyclerView rvUpcomingExercises;
    private ArrayList<ExerciseClass> exerciseClassArrayList;
    private ArrayList<StretchClass> stretchClassArrayList;
    private ImageView ivCurrentExercise;
    private TextView tvCurrentExerciseName;
    private TextView tvCurrentExerciseReps;
    private FragmentManager fragmentManager;
    private ImageView ivCurrentExerciseGif;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_workout);

        workout = getIntent().getParcelableExtra("workout");
        exerciseClassArrayList = workout.getExercises();
        stretchClassArrayList = workout.getStretchesList();

        ivCurrentExercise = findViewById(R.id.current_exercise_img);
        tvCurrentExerciseName = findViewById(R.id.current_exercise_name);
        tvCurrentExerciseReps = findViewById(R.id.current_sets_and_reps);
        ivCurrentExerciseGif = findViewById(R.id.workout_exercise_gif);
        //
        fragmentManager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("exercises", exerciseClassArrayList);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_timer, StopWatchFragment.class, bundle).commit();


        rvUpcomingExercises = findViewById(R.id.rv_workouts_exercises);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvUpcomingExercises.setLayoutManager(linearLayoutManager);

        Glide.with(this).load(exerciseClassArrayList.get(0).getVideoDescription()).into(ivCurrentExerciseGif);
        Picasso.get().load(exerciseClassArrayList.get(0).getImageId()).into(ivCurrentExercise);
        tvCurrentExerciseName.setText(exerciseClassArrayList.get(0).getName());
        tvCurrentExerciseReps.setText(exerciseClassArrayList.get(0).getReps() + " x " + exerciseClassArrayList.get(0).getSets());

        rvUpcomingExercises.setAdapter(new WorkoutExercisesAdapter(this, exerciseClassArrayList, stretchClassArrayList));
    }
}