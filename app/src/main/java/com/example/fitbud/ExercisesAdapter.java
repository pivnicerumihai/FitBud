package com.example.fitbud;

import static android.view.View.*;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitbud.Model.ExerciseClass;

import java.text.BreakIterator;
import java.util.ArrayList;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ExerciseClass> exerciseClassArrayList;

    public ExercisesAdapter(Context context, ArrayList<ExerciseClass> exerciseClassArrayList){
        this.context = context;
        this.exerciseClassArrayList = exerciseClassArrayList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_exercises, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExercisesAdapter.ViewHolder holder, int position){
        ExerciseClass exerciseClass = exerciseClassArrayList.get(position);
        holder.list_name.setText(exerciseClass.getName());
        holder.list_image.setImageURI(Uri.parse("android.resource://"+ context.getPackageName() +"/drawable/" + exerciseClass.getImageId()));
        holder.list_difficulty.setText(exerciseClass.getDifficulty());

    }

    public int getItemCount(){
        return exerciseClassArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        public TextView list_difficulty;
        private Button list_name;
        private ImageView list_image;

        public ViewHolder(@NonNull View itemView) {
        super(itemView);
            list_name = itemView.findViewById(R.id.list_name);
            list_name.setOnClickListener(this::onClick);
            list_image = itemView.findViewById(R.id.list_image);
            list_difficulty = itemView.findViewById(R.id.exercise_difficulty);
        }

        @Override
        public void onClick(View view) {
            Intent intent;
            ExerciseClass selectedExercise;
            intent = new Intent(context.getApplicationContext(),ExerciseDetailsActivity.class);
            for(ExerciseClass exercise : exerciseClassArrayList){
                if(exercise.getName().equals(list_name.getText())){
                intent.putExtra("exercise", exercise);
                }
            }

            context.startActivity(intent);
            }
    }
}
