package com.example.fitbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fitbud.Model.ExerciseClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ExercisesList extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView exerciseRV;
    private ArrayList<ExerciseClass> exerciseClassArrayList;
    private String category;
    private MaterialToolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_list);

        exerciseRV = findViewById(R.id.RVExercises);
        floatingActionButton = findViewById(R.id.exercise_fab);
        floatingActionButton.setOnClickListener(this::onClick);
        firebaseFirestore = FirebaseFirestore.getInstance();


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        exerciseRV.setLayoutManager(linearLayoutManager);

        retrieveExercisesFromDB();

    }


    public void retrieveExercisesFromDB(){
        exercises = firebaseFirestore.collection("exercise");
        exerciseClassArrayList = new ArrayList<>();



        exercises.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        System.out.println(documentSnapshot.getId() + " => " + documentSnapshot.getData());
                        exerciseClassArrayList.add(documentSnapshot.toObject(ExerciseClass.class));
                        System.out.println("TESTING" + documentSnapshot.getData());
                    populateRecyclerView(exerciseClassArrayList);
                    }


                }
                else {
                    System.out.println("Error");
                }
            }

        });
    }

    public void populateRecyclerView(ArrayList<ExerciseClass> exerciseClassArrayList){
        exerciseRV.setAdapter(new ExercisesAdapter(this,exerciseClassArrayList));
    }

        @Override
    public void onClick(View view) {
        Intent intent;
        intent = new Intent(this, AddExerciseActivity.class);
        startActivity(intent);
    }
}
