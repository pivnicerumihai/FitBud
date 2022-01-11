package com.example.fitbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitbud.Model.UserClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;


//To do: catch errors like email already exists, password rules
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername;
    EditText etEmailAddress;
    EditText etConfirmEmail;
    EditText etPassword;
    EditText etConfirmPassword;
    MaterialButton btnRegister;
    FirebaseFirestore firebaseFirestore;
    DocumentReference existingUsernames;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername = findViewById(R.id.et_register_username);
        etEmailAddress = findViewById(R.id.et_register_email);
        etConfirmEmail = findViewById(R.id.et_register_confirm_email);
        etPassword = findViewById(R.id.et_register_password);
        etConfirmPassword = findViewById(R.id.et_register_confirm_password);
        btnRegister = findViewById(R.id.btn_register_account);
        btnRegister.setOnClickListener(this);
        firebaseFirestore = FirebaseFirestore.getInstance();
        existingUsernames = firebaseFirestore.collection("users").document("usernames");
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        String username = etUsername.getText().toString();
        String emailAddress = etEmailAddress.getText().toString();
        String confirmEmail = etConfirmEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        final ArrayList[] usernamesArrayList = {new ArrayList()};

        existingUsernames.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    usernamesArrayList[0] = (ArrayList) documentSnapshot.getData().get("usernames");
                    if(usernamesArrayList[0].contains(username)){
                        Toast.makeText(getBaseContext(),"Username already exists. Please choose another one", Toast.LENGTH_LONG).show();

                    }
                    else if(!emailAddress.equals(confirmEmail)){
                        Toast.makeText(getBaseContext(),"E-mail addresses do not match!", Toast.LENGTH_LONG).show();
                    }
                    else if(!password.equals(confirmPassword)){
                        Toast.makeText(getBaseContext(),"Passwords do not match!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        firebaseAuth.createUserWithEmailAndPassword(emailAddress,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getBaseContext(), "Account Created! You can now sign in using your new credentials", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getBaseContext(),WelcomeActivity.class);
                                    startActivity(intent);
                                    UserClass user = new UserClass(username,emailAddress, null, null, null, null, null, null, null,null, null,true, null,null,"Standard");
                                    firebaseFirestore.collection("users").document(task.getResult().getUser().getUid()).set(user);
                                }
                                else{
                                    Toast.makeText(getBaseContext(),"There was an error!", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
                    }
                }
            }
        });

    }
}