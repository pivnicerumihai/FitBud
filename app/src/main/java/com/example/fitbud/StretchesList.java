package com.example.fitbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fitbud.Model.StretchClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StretchesList extends AppCompatActivity {

    private RecyclerView RVStretch;
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<StretchClass> stretchClassArrayList;
    private CollectionReference stretches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretch);

        RVStretch = findViewById(R.id.RVStretches);
        firebaseFirestore = FirebaseFirestore.getInstance();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        RVStretch.setLayoutManager(linearLayoutManager);

        retrieveStretchesFromDB();
    }

    private void retrieveStretchesFromDB() {

        stretches = firebaseFirestore.collection("stretch");
        stretchClassArrayList = new ArrayList<StretchClass>();
        stretches.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        stretchClassArrayList.add(documentSnapshot.toObject(StretchClass.class));
                        populateRecyclerView(stretchClassArrayList);
                    }
                }
            }

        });
    }

    private void populateRecyclerView(ArrayList<StretchClass> stretchClassArrayList) {
        RVStretch.setAdapter(new StretchesAdapter(this,stretchClassArrayList));
    }
}


