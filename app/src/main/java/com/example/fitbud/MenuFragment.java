package com.example.fitbud;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

public class MenuFragment extends Fragment implements  View.OnClickListener{

    MaterialButton btn_exercise;
    MaterialButton btn_stretch;
    MaterialButton btn_workout;
    MaterialButton btn_recipe;
    MaterialButton btn_diary;
    MaterialButton btn_social;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        btn_exercise = view.findViewById(R.id.btn_exercise);
        btn_exercise.setOnClickListener(this::onClick);
        btn_stretch = view.findViewById(R.id.btn_stretch);
        btn_stretch.setOnClickListener(this::onClick);
        btn_workout = view.findViewById(R.id.btn_workout);
        btn_recipe = view.findViewById(R.id.btn_recipe);
        btn_recipe.setOnClickListener(this);
        btn_diary = view.findViewById(R.id.btn_diary);
        btn_social = view.findViewById(R.id.btn_feed);

        return view;
    }


    @Override
    public void onClick(View view) {
        Intent intent;


        switch (view.getId()){
            case R.id.btn_exercise:
                intent = new Intent(getActivity(),ExercisesActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_stretch:
                intent = new Intent(getActivity(), StretchesList.class);
                startActivity(intent);
                break;
            case R.id.btn_recipe:
                intent = new Intent(getActivity(), RecipeCategory.class);
                startActivity(intent);
        }
    }
}