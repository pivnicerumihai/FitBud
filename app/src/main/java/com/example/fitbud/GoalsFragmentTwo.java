package com.example.fitbud;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.fitbud.Model.UserClass;
import com.example.fitbud.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

//To Do: Convert height and weight to integers.

public class GoalsFragmentTwo extends Fragment implements View.OnClickListener {

    CheckBox cbBodyweight;
    CheckBox cbBarbel;
    CheckBox cbDumbbell;
    CheckBox cbBands;
    CheckBox cbCardio;
    CheckBox cbRower;
    CheckBox cbPullUp;
    Button btnFinish;
    ArrayList availableEquipment;
    FirebaseAuth firebaseAuth;
    String userID;
    FirebaseFirestore firebaseFirestore;
    String heightMeasurement;
    String weightMeasurement;
    String height;
    String weight;
    String goal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goals_two, container, false);


        btnFinish = view.findViewById(R.id.btn_goals_finish);
        btnFinish.setOnClickListener(this);
        cbBodyweight = view.findViewById(R.id.checkbox_bodyweight);
        cbBarbel = view.findViewById(R.id.checkbox_barbel);
        cbDumbbell = view.findViewById(R.id.checkbox_dumbell);
        cbBands = view.findViewById(R.id.checkbox_bands);
        cbCardio = view.findViewById(R.id.checkbox_cardio);
        cbRower = view.findViewById(R.id.checkbox_rower);
        cbPullUp = view.findViewById(R.id.checkbox_pullup);
        availableEquipment = new ArrayList();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getUid();

        // Inflate the layout for this fragment
        heightMeasurement = this.getArguments().getString("heightMeasurement");
        weightMeasurement = this.getArguments().getString("weight");
        height = this.getArguments().getString("height");
        weight = this.getArguments().getString("weight");
        goal = this.getArguments().getString("goal");
        return view;
    }

    @Override
    public void onClick(View view) {
        if(cbBodyweight.isChecked()){
            availableEquipment.add(cbBodyweight.getText());
        }
        if(cbBarbel.isChecked()){
            availableEquipment.add(cbBarbel.getText());
        }
       if(cbDumbbell.isChecked()){
            availableEquipment.add(cbDumbbell.getText());
        }
        if(cbBands.isChecked()){
            availableEquipment.add(cbBands.getText());
        }
        if(cbCardio.isChecked()){
            availableEquipment.add("Cardio");
        }
        if(cbRower.isChecked()){
            availableEquipment.add(cbRower.getText());
        }
         if(cbPullUp.isChecked()){
            availableEquipment.add(cbPullUp.getText());
        }
        System.out.println(availableEquipment);

        firebaseFirestore.collection("users").document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    UserClass userClass = task.getResult().toObject(UserClass.class);
                    userClass.setAvailableEquipment(availableEquipment);
                    userClass.setHeightMeasurement(heightMeasurement);
                    userClass.setWeightMeasurement(weightMeasurement);
                    userClass.setHeight(height);
                    userClass.setWeight(weight);
                    userClass.setGoal(goal);
                    userClass.setFirstTimeLogIn(false);
                    firebaseFirestore.collection("users").document(userID).set(userClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                startActivity(intent);

                            }
                        }
                    });
                }
            }
        });
    }
}