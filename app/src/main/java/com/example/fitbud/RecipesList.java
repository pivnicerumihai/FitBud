package com.example.fitbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.fitbud.Model.RecipeClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RecipesList extends AppCompatActivity {

    private Intent intent;
    private RecyclerView rvRecipes;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference recipes;
    private ArrayList<RecipeClass> recipeClassArrayList;
    String recipeType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);

        rvRecipes = findViewById(R.id.rv_recipe_list);
        firebaseFirestore = FirebaseFirestore.getInstance();
        intent = getIntent();
        recipeType = intent.getStringExtra("Meal Type");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvRecipes.setLayoutManager(linearLayoutManager);

        retrieveRecipesFromDB();
    }


private void retrieveRecipesFromDB(){

    recipes = firebaseFirestore.collection(recipeType);
    recipeClassArrayList = new ArrayList<RecipeClass>();

    recipes.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()){
                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                    recipeClassArrayList.add(documentSnapshot.toObject(RecipeClass.class));

                }
                populateRecyclerView(recipeClassArrayList);
            }
        }
    });
}

private void populateRecyclerView(ArrayList<RecipeClass> recipeClassArrayList){
 rvRecipes.setAdapter(new RecipeListAdapter(this,recipeClassArrayList));
}
}