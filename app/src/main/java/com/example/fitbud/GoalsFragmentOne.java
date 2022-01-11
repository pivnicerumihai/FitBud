 package com.example.fitbud;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

 public class GoalsFragmentOne extends Fragment {

    AppCompatSpinner heightSpinner;
    AppCompatSpinner weightSpinner;
    AppCompatSpinner goalsSpinner;
    Button btnNext;
    EditText etHeight;
    EditText etWeight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goals_one, container, false);

        btnNext = view.findViewById(R.id.btn_goals_next);
        heightSpinner = view.findViewById(R.id.goals_height_spinner);
        weightSpinner = view.findViewById(R.id.goals_weight_spinner);
        goalsSpinner = view.findViewById(R.id.set_goal_spinner);
        etHeight = view.findViewById(R.id.et_goals_height);
        etWeight = view.findViewById(R.id.et_goals_weight);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("heightMeasurement", heightSpinner.getSelectedItem().toString());
                bundle.putString("weightMeasurement", weightSpinner.getSelectedItem().toString());
                bundle.putString("goal", goalsSpinner.getSelectedItem().toString());
                bundle.putString("height", etHeight.getText().toString() );
                bundle.putString("weight", etWeight.getText().toString());

                GoalsFragmentTwo goalsFragmentTwo = new GoalsFragmentTwo();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                goalsFragmentTwo.setArguments(bundle);

                fragmentTransaction.replace(R.id.goals_framgent_container, goalsFragmentTwo).commit();
            }
        });


        return view;
    }


 }
