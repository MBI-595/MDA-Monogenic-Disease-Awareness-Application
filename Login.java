package com.example.monogenicdiseases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button button;
    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mForgot;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mForgot = findViewById(R.id.ForgotPassword);
        mEmail = findViewById(R.id.L_email);
        mPassword = findViewById(R.id.Password);
        progressBar = findViewById(R.id.LoginProgress);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.Login);
        button = (Button) findViewById(R.id.CreateNewAccount);


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (email.isEmpty() || !email.contains("@gmail")) {
                    mEmail.setError("Email is not Valid");
                } else if (password.isEmpty() || password.length() < 5) {
                    mPassword.setError("Password must be greater than 5 letters");
                } else {
                    Toast.makeText(Login.this, "Logging In!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);

                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //mLoadingBar.dismiss();
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(Login.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, Check.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            } else{
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(Login.this, "ERROR! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }



            }


        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        mForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Forgot.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed(){

        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit the app?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
