package com.example.fitbud;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fitbud.Model.ExerciseClass;
import com.example.fitbud.Model.Global;
import com.example.fitbud.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddExerciseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AppCompatSpinner spinner;
    ImageView ivImagePlaceholder;
    Button btnUploadImage;
    EditText etExerciseName;
    Button btnSaveExercise;
    ExerciseClass exerciseClass;
    Global user;
    String username;
    private FirebaseFirestore firebaseFirestore;
    private Uri imagePath;
    private StorageReference storageReference;

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
        firebaseFirestore = FirebaseFirestore.getInstance();
        user = (Global) getApplicationContext();
        username = user.getUsername();
        storageReference = FirebaseStorage.getInstance().getReference();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.muscle_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 105);
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

            storageReference.putFile(imagePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();
                            exerciseClass.setCategory(spinner.getSelectedItem().toString());
                            exerciseClass.setDifficulty("");
                            exerciseClass.setSets(0);
                            exerciseClass.setReps(0);
                            exerciseClass.setAuthor(username);
                            exerciseClass.setDeletable(true);
                            exerciseClass.setImageId(url);
                            exerciseClass.setHasTimer(false);
                            exerciseClass.setTextDescription(null);
                            exerciseClass.setVideoDescription(null);
                            exerciseClass.setRecords(null);


                            firebaseFirestore.collection("exercise").add(exerciseClass);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("Error", e.toString());
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("Error2", e.toString());
                }
            });


        }
    });

    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 105 && resultCode == RESULT_OK && data.getData()!= null){
            imagePath= data.getData();
            ivImagePlaceholder.setImageURI(imagePath);

        }
    }

    private String getExtension(Uri img_path){
        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(resolver.getType(img_path));
    };




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
