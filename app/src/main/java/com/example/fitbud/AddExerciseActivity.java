package com.example.fitbud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fitbud.Model.ExerciseClass;
import com.example.fitbud.R;
import com.squareup.picasso.Picasso;

public class AddExerciseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AppCompatSpinner spinner;
    ImageView ivImagePlaceholder;
    Button btnUploadImage;
    EditText etExerciseName;
    Button btnSaveExercise;
    ExerciseClass exerciseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        spinner = findViewById(R.id.muscle_list_spinner);
        ivImagePlaceholder = findViewById(R.id.image_placeholder);
        btnUploadImage = findViewById(R.id.btn_add_exercise_upload_image);
        etExerciseName = findViewById(R.id.et_exercise_name);
        btnSaveExercise = findViewById(R.id.btn_save_exercise);
        exerciseClass = new ExerciseClass();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.muscle_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              UPLOAD IMAGE WITH PICASSO TO DB STORAGE
            }
        });

    btnSaveExercise.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(etExerciseName.getText().toString().isEmpty()){
                Toast.makeText(getBaseContext(),"You must name your exercise",Toast.LENGTH_LONG).show();
            }
            else {
                exerciseClass.setName(etExerciseName.getText().toString());
            }
            exerciseClass.setCategory(spinner.getSelectedItem().toString());
            exerciseClass.setDifficulty("");
            exerciseClass.setSets(0);
            exerciseClass.setReps(0);
//            GET USER NAME FOR AUTHOR
            exerciseClass.setAuthor("Test");
            exerciseClass.setDeletable(true);
            exerciseClass.setImageId("Test");
            exerciseClass.setHasTimer(false);
            exerciseClass.setTextDescription(null);
            exerciseClass.setVideoDescription(null);
            exerciseClass.setRecords(null);

//            UPLOAD EXERCISE TO DB
            System.out.println(exerciseClass);
        }
    });
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}