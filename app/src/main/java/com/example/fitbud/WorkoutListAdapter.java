package com.example.fitbud;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitbud.Model.ExerciseClass;
import com.example.fitbud.Model.RecipeClass;
import com.example.fitbud.Model.WorkoutClass;

import java.util.ArrayList;

public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.ViewHolder>{

    private Context context;
    private ArrayList<WorkoutClass> workoutClassArrayList;

    public WorkoutListAdapter(Context context, ArrayList<WorkoutClass> workoutClassArrayList){
        this.context = context;
        this.workoutClassArrayList = workoutClassArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_workouts,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutListAdapter.ViewHolder holder, int position) {
        WorkoutClass workoutClass = workoutClassArrayList.get(position);
        ArrayList musclesTrained = workoutClass.getMusclesWorked();
        ArrayAdapter arrayAdapter = new ArrayAdapter(context,R.layout.muscles_trained_list, musclesTrained);
        holder.tvWorkoutName.setText(workoutClass.getName());
        if(workoutClass.getReviews() < -10){
            holder.ivOnFire.setImageResource(R.drawable.outline_thumb_down_white_24);
        }
        else if(workoutClass.getReviews() > 10){
            holder.ivOnFire.setImageResource(R.drawable.outline_local_fire_department_white_24);
        }
        else {
            holder.ivOnFire.setImageResource(R.drawable.outline_sentiment_neutral_white_24);
        }
        holder.lvMusclesTrained.setAdapter(arrayAdapter);
    }

    @Override
    public int getItemCount() {
        return workoutClassArrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvWorkoutName;
        ImageView ivOnFire;
        GridView lvMusclesTrained;
        Button btnAccessWorkout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWorkoutName = itemView.findViewById(R.id.tv_workout_name);
            ivOnFire = itemView.findViewById(R.id.iv_onfire);
            lvMusclesTrained = itemView.findViewById(R.id.grid_muscles_trained);
            btnAccessWorkout = itemView.findViewById(R.id.btn_access_workout);
            btnAccessWorkout.setOnClickListener(view -> {
                Intent intent = new Intent(context.getApplicationContext(), PerformWorkoutActivity.class);
                for(WorkoutClass workoutClass : workoutClassArrayList){
                    if(workoutClass.getName().equals(tvWorkoutName.getText())){
                        intent.putExtra("workout", workoutClass);

                        break;

                    }
                }
                context.startActivity(intent);
            });
        }

    }


}
