package com.example.fitbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

//    Declare Variables
    private EditText etEmail;
    private Button btnResetPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        etEmail = findViewById(R.id.reset_password_email);
        btnResetPassword = findViewById(R.id.reset_password_button);

//        Connect to Firebase Authentication service
        firebaseAuth = FirebaseAuth.getInstance();

//        OnClickListener to send  reset password link;

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress;
//                Check if Edit Text is empty
                if(etEmail.getText().toString().length() == 0){
                    Toast.makeText(getBaseContext(),"No e-mail password entered", Toast.LENGTH_LONG).show();
                }
                else{
                 emailAddress = etEmail.getText().toString();
                 firebaseAuth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         if(task.isSuccessful()){
                             Toast.makeText(getBaseContext(), "Reset link sent. Please check your e-mail", Toast.LENGTH_LONG).show();
                         }
                     else{
                         Toast.makeText(getBaseContext(), "E-mail is not registered with FitBud.", Toast.LENGTH_LONG).show();
                         }
                     }

                 });
                }
            }
        });
    }
}