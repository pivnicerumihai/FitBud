package com.example.fitbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitbud.Model.Global;
import com.example.fitbud.Model.UserClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class WelcomeActivity extends AppCompatActivity {

    private EditText etEmailAddress;
    private EditText etPassword;
    private TextView tvResetPassword;
    private MaterialButton btnSignIn;
    private MaterialButton btnAnonymous;
    private MaterialButton btnRegister;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private Global globalUserDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

    etEmailAddress = findViewById(R.id.et_email_address);
    etPassword = findViewById(R.id.et_login_password);
    tvResetPassword = findViewById(R.id.tv_reset_password);
    btnSignIn = findViewById(R.id.btn_signin);
    btnAnonymous = findViewById(R.id.btn_signin_anonymous);
    btnRegister = findViewById(R.id.btn_signin_register);
    firebaseAuth = FirebaseAuth.getInstance();
    firebaseFirestore = FirebaseFirestore.getInstance();
    globalUserDetails = (Global) getApplicationContext();

    btnSignIn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String email = etEmailAddress.getText().toString();
            String password = etPassword.getText().toString();
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        firebaseFirestore.collection("users").document(task.getResult().getUser().getUid()).get().addOnSuccessListener(documentSnapshot -> {
                            UserClass user = documentSnapshot.toObject(UserClass.class);

                            globalUserDetails.setUsername(user.getUsername());
                            globalUserDetails.setEmail(user.getEmail());
                            globalUserDetails.setUserType(user.getUserType());

                            if(user.getFirstTimeLogIn() == true){
                                Intent intent = new Intent(getBaseContext(), GoalsActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                }
            });
        }
    });

    btnRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
            startActivity(intent);
        }
    });

    btnAnonymous.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            globalUserDetails.setUsername("Anonymous");
            globalUserDetails.setEmail("Anonymous");
            globalUserDetails.setUserType("Anonymous");
            startActivity(intent);
        }
    });
    tvResetPassword.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getBaseContext(), ResetPasswordActivity.class);
            startActivity(intent);
        }
    });
    }
}