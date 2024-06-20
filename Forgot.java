package com.example.monogenicdiseases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot extends AppCompatActivity {
    private EditText emailEditText;
    private Button resetPasswordButton;
    private ProgressBar progressBar;

    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        emailEditText=(EditText)findViewById(R.id.forgotemail);
        resetPasswordButton=(Button) findViewById(R.id.Sendmail);
        progressBar=(ProgressBar)findViewById(R.id.bar);

        fauth=FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                resetPassword();
            }
        });
    }
    private void resetPassword(){
        String email=emailEditText.getText().toString().trim();
        if(email.isEmpty()){
            emailEditText.setError("Email is required!");
            emailEditText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        fauth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Forgot.this,"Check your email to  reset your password!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Forgot.this, Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else{
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Forgot.this, "ERROR! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}