package com.example.fitbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fitbud.Model.ExerciseClass;
import com.example.fitbud.Model.StretchClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WorkoutExercisesAdapter extends RecyclerView.Adapter<WorkoutExercisesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ExerciseClass> exerciseClassArrayList;
    private ArrayList<StretchClass> stretchClassArrayList;

    public WorkoutExercisesAdapter(Context context,ArrayList<ExerciseClass> exerciseClassArrayList, ArrayList<StretchClass> stretchClassArrayList){
        this.context = context;
        this.exerciseClassArrayList = exerciseClassArrayList;
        this.stretchClassArrayList = stretchClassArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_workouts_exercises, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(position >= 0) {
            Picasso.get().load(exerciseClassArrayList.get(position + 1).getImageId()).into(holder.ivExerciseImage);
            holder.tvExerciseName.setText(exerciseClassArrayList.get(position + 1).getName());
            holder.tvSetsAndReps.setText(exerciseClassArrayList.get(position + 1).getReps() + " x " + exerciseClassArrayList.get(position + 1).getSets());
        }
    }

    @Override
    public int getItemCount() {
        return exerciseClassArrayList.size() - 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivExerciseImage;
        private TextView tvExerciseName;
        private TextView tvSetsAndReps;
        private Integer totalExercises;
        private Integer totalStretches;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivExerciseImage = itemView.findViewById(R.id.workout_exercise_img);
            tvExerciseName = itemView.findViewById(R.id.workout_exercise_name);
            tvSetsAndReps = itemView.findViewById(R.id.workout_sets_and_reps);
            totalExercises = exerciseClassArrayList.size();
            totalStretches = stretchClassArrayList.size();


        }

    }
}