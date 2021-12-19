package com.example.fitbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitbud.Model.ExerciseClass;
import com.example.fitbud.Model.RecipeClass;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {


    private ArrayList<RecipeClass> recipeClassArrayList;
    private Context context;

    public RecipeListAdapter(Context context, ArrayList<RecipeClass> recipeClassArrayList){
        this.context = context;
        this.recipeClassArrayList = recipeClassArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_meals, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecipeClass recipeClass = recipeClassArrayList.get(position);
        holder.tvRecipeName.setText(recipeClass.getName());
        holder.tvCookTime.setText(recipeClass.getCookingTime());
        holder.tvCalories.setText(recipeClass.getCalories().toString());
        holder.tvProtein.setText(recipeClass.getProteins().toString());
        holder.tvCarbs.setText(recipeClass.getCarbohydrates().toString());
        holder.tvFats.setText(recipeClass.getFats().toString());
        Picasso.get().load(recipeClass.getImageId()).into(holder.ivRecipeImage);
    }

    @Override
    public int getItemCount() {
        return recipeClassArrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView ivRecipeImage;
        TextView tvRecipeName;
        TextView tvCookTime;
        TextView tvCalories;
        TextView tvProtein;
        TextView tvCarbs;
        TextView tvFats;
        MaterialButton btnViewRecipe;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            ivRecipeImage = itemView.findViewById(R.id.list_recipe_image);
            tvRecipeName = itemView.findViewById(R.id.tv_recipe_name);
            tvCookTime = itemView.findViewById(R.id.tv_cook_time);
            tvCalories = itemView.findViewById(R.id.tv_calories);
            tvProtein = itemView.findViewById(R.id.tv_proteins);
            tvCarbs = itemView.findViewById(R.id.tv_carbs);
            tvFats = itemView.findViewById(R.id.tv_fats);
            btnViewRecipe = itemView.findViewById(R.id.btn_view_recipe);
            btnViewRecipe.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            RecipeClass selectedRecipe = recipeClassArrayList.get(getAdapterPosition()) ;
            Intent intent;
            intent = new Intent(context.getApplicationContext(),SingleRecipeActivity.class);
            intent.putParcelableArrayListExtra("recipes", recipeClassArrayList);
            intent.putExtra("index", getAdapterPosition());
            context.startActivity(intent);



        }
    }

}


