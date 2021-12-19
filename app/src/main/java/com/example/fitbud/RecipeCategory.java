package com.example.fitbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class RecipeCategory extends AppCompatActivity implements View.OnClickListener{

    Button btnLowCarb;
    Button btnUnder400;
    Button btnBulk;
    Button btnVegan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_types_list);

        btnLowCarb = findViewById(R.id.btn_low_carb);
        btnLowCarb.setOnClickListener(this);
        btnUnder400 = findViewById(R.id.btn_under400);
        btnBulk = findViewById(R.id.btn_bulk);
        btnBulk.setOnClickListener(this);
        btnVegan = findViewById(R.id.btn_vegan);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,RecipesList.class );
        switch (view.getId()){
            case R.id.btn_low_carb:
            intent.putExtra("Meal Type", "Low Carbohydrates");
            startActivity(intent);
            break;
            case R.id.btn_under400:
                intent.putExtra("Meal Type", "Under 400 Calories");
                startActivity(intent);
                break;
                case R.id.btn_bulk:
                    intent = new Intent(this,RecipesList.class);
                intent.putExtra("Meal Type", "bulk_recipe");
                startActivity(intent);
                break;
                case R.id.btn_vegan:
                intent.putExtra("Meal Type", "Vegan Recipes");
                startActivity(intent);
                break;

        }
    }
}