package com.example.fitbud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class GoalsActivity extends AppCompatActivity {

    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);


        getSupportFragmentManager().beginTransaction().add(R.id.goals_framgent_container,new GoalsFragmentOne()).commit();
    }

}