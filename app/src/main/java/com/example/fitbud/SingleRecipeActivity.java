package com.example.fitbud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitbud.Classes.OnSwipeTouchListener;
import com.example.fitbud.Model.RecipeClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SingleRecipeActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    private Integer selectedRecipeIndex;
    private ArrayList<RecipeClass> recipeClassArrayList;
    private ImageView ivRecipeImage;
    private TextView tvRecipeName;
    private TextView tvRecipeInstructions;
    private LinearLayoutCompat lvIngredients;
    private ArrayList<String> ingredients;
    private ScrollView container;
    private Integer iterator;
    private SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe);



        recipeClassArrayList =  getIntent().getExtras().getParcelableArrayList("recipes");
        selectedRecipeIndex = getIntent().getIntExtra("index", 0);
        iterator = selectedRecipeIndex;
        System.out.println("Array" + recipeClassArrayList.size());

        ivRecipeImage = findViewById(R.id.iv_single_recipe);
        Picasso.get().load(recipeClassArrayList.get(selectedRecipeIndex).getImageId()).into(ivRecipeImage);

        tvRecipeName = findViewById(R.id.tv_single_recipe_name);
        tvRecipeName.setText(recipeClassArrayList.get(selectedRecipeIndex).getName());

        lvIngredients = findViewById(R.id.ingredients_layout);
        ingredients = recipeClassArrayList.get(selectedRecipeIndex).getIngredients();

        tvRecipeInstructions = findViewById(R.id.tv_cooking_instructions);
        tvRecipeInstructions.setText(recipeClassArrayList.get(selectedRecipeIndex).getCookingInstructions().replace("_b","\n"));

        swipeRefreshLayout = findViewById(R.id.recipe_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        container = findViewById(R.id.container_single_recipe);
        container.setOnTouchListener(new OnSwipeTouchListener(this){

            public void onSwipeRight() {


                iterator--;
                Picasso.get().load(recipeClassArrayList.get(iterator).getImageId()).into(ivRecipeImage);
                tvRecipeName.setText(recipeClassArrayList.get(iterator).getName());
                ingredients = recipeClassArrayList.get(iterator).getIngredients();
                tvRecipeInstructions.setText(recipeClassArrayList.get(iterator).getCookingInstructions());
            }
            public void onSwipeLeft() {

                iterator++;
                Picasso.get().load(recipeClassArrayList.get(iterator).getImageId()).into(ivRecipeImage);
                tvRecipeName.setText(recipeClassArrayList.get(iterator).getName());
                ingredients = recipeClassArrayList.get(iterator).getIngredients();
                tvRecipeInstructions.setText(recipeClassArrayList.get(iterator).getCookingInstructions());
            }
        });
      listIngredients();

    }
    private void listIngredients(){
        for(String ingredient : ingredients){
            TextView tvIngredient = new TextView(this);
            tvIngredient.setTextColor(getResources().getColor(R.color.black));
            tvIngredient.setText(ingredient.replace("_bullet_", "\u25CF"));
            tvIngredient.setTextSize(16);
            tvIngredient.setPadding(10,10,10,10);
            lvIngredients.addView(tvIngredient);
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        this.onTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);

            }
        }, 2000);
    }

}


