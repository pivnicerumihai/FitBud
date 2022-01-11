package com.example.fitbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.fitbud.Model.StretchClass;
import com.example.fitbud.Model.WorkoutClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<WorkoutClass> workoutClassArrayList;
    private ArrayList<StretchClass> stretchClassArrayList;
    private CollectionReference workouts;
    private CollectionReference stretches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        recyclerView = findViewById(R.id.rv_workout);
        firebaseFirestore = FirebaseFirestore.getInstance();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        retrieveWorkoutFromDB();
    }

    private void retrieveWorkoutFromDB() {

        workouts = firebaseFirestore.collection("workout");
        stretches = firebaseFirestore.collection("stretch");
        workoutClassArrayList = new ArrayList<WorkoutClass>();
        stretchClassArrayList = new ArrayList<StretchClass>();

        workouts.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()){

                        workoutClassArrayList.add(documentSnapshot.toObject(WorkoutClass.class));

                        populateRecyclerView(workoutClassArrayList);

                    }

            }
        });
    }


    private void populateRecyclerView(ArrayList<WorkoutClass> workoutClassArrayList) {
        recyclerView.setAdapter(new WorkoutListAdapter(this,workoutClassArrayList));
    }
}

