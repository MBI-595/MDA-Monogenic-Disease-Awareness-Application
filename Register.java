package com.example.monogenicdiseases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText inputName, inputEmail,inputPassword,inputConfirmPassword;
    //final Handler handler=new Handler();
    Button canc;
    Button btnRegister;
    TextView alreadyHaveAccount;
    FirebaseAuth mAuth;
    DatabaseReference userRef;
    //ProgressDialog mLoadingBar;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//       userRef=FirebaseDatabase.getInstance().getReference("Profiles");
//       userRef.child("user").setValue("New Userrrrr");
        setContentView(R.layout.activity_register);




//        DatabaseReference UserRef=FirebaseDatabase.getInstance().getReference("Profiles");
//        UserRef.child("user").setValue("NEW USER");
        inputName=findViewById(R.id.name);
        inputEmail=findViewById(R.id.email);
        inputPassword=findViewById(R.id.rPassword);
        inputConfirmPassword=findViewById(R.id.ConfirmPassword);
        btnRegister=findViewById(R.id.SignUp);
        canc=findViewById(R.id.Cancel);
        progressBar=findViewById(R.id.progressBar);
        alreadyHaveAccount=findViewById(R.id.AlreadyHaveAcc);
        mAuth=FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // AttemptRegistration();

                String name=inputName.getText().toString();
                String email=inputEmail.getText().toString();
                String password=inputPassword.getText().toString();
                String confirmpassword=inputConfirmPassword.getText().toString();

                String realemail="";
                char[] cArray = email.toCharArray();

                for(int i=0; cArray[i]!='@';i++){
                    realemail=realemail+cArray[i];
                }
                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Profiles");
                databaseReference.child(realemail).setValue(name);


                if (email.isEmpty() || !email.contains("@gmail")){
                    inputEmail.setError("Email is not Valid");
                } else if (password.isEmpty() || password.length()<5){
                    inputPassword.setError("Password must be greater than 5 letters");
                } else if(!confirmpassword.equals(password)){
                    inputConfirmPassword.setError("Passwords do not match!");
                }else {
                    Toast.makeText(Register.this, "Registering!",Toast.LENGTH_SHORT).show();
//                    DatabaseReference userRef= FirebaseDatabase.getInstance().getReference("UserInfo");
//                    userRef.child(email).setValue(name);

//                    Runnable runnable=new Runnable(){
//                        @Override
//                        public void run(){
//                            Toast.makeText(Register.this, "Please Wait!",Toast.LENGTH_SHORT).show();
//                            Intent intent=new Intent (Register.this, Login.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);
//                            finish();
//                        }
//                    };

                    //handler.postDelayed(runnable, 5000);

                    progressBar.setVisibility(View.VISIBLE);
                    //mLoadingBar.setTitle("Registration");
                    //mLoadingBar.setMessage("Please wait while we load your credentials!");
                   // mLoadingBar.setCanceledOnTouchOutside(false);
                   // mLoadingBar.show();
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task){
                            if(task.isSuccessful()){

                                User user=new User(name, email);
                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().
                                        getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(Register.this,"Data Entered",Toast.LENGTH_LONG).show();
                                        }else {
                                            Toast.makeText(Register.this,"ERROR, DATA NOT ENTERED",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

//                                String realemail="";
//                                char[] cArray = email.toCharArray();
//
//                                for(int i=0; cArray[i]!='@';i++){
//                                    realemail=realemail+cArray[i];
//                                }
//                                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Profiles");
//                                databaseReference.child(realemail).setValue(name);
                                //mLoadingBar.dismiss();
//                                DatabaseReference userRef= FirebaseDatabase.getInstance().getReference("userInfo");
//                                userRef.child(email).setValue(name);
//                                userRef.child("name").setValue(name);
//                                DatabaseReference dataReference=FirebaseDatabase.getInstance().getReference(email);
//                                dataReference.setValue(name);


                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(Register.this, "Registration successful! Pls Login!",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Register.this,Login.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();

                            }else{
                                //mLoadingBar.dismiss();
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(Register.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });

        canc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Copyright");
//                databaseReference.setValue("ALLL RIGHTS RESERVED");
                Toast.makeText(Register.this,"New Account not created!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent (Register.this,Login.class);
                startActivity(intent);
            }
        });
        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Register.this,"Sign In Please!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent (Register.this,Login.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed(){

        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to cancel your registration?")
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

//    private void AttemptRegistration(){
//        String name=inputName.getText().toString();
//        String email=inputEmail.getText().toString();
//        String password=inputPassword.getText().toString();
//        String confirmpassword=inputConfirmPassword.getText().toString();
//
//        if (email.isEmpty() || !email.contains("@gmail")){
//            inputEmail.setError("Email is not Valid");
//        } else if (password.isEmpty() || password.length()<5){
//            inputPassword.setError("Password must be greater than 5 letters");
//        } else if(!confirmpassword.equals(password)){
//            inputConfirmPassword.setError("Passwords do not match!");
//        }else {
//            Toast.makeText(Register.this, "Registering!",Toast.LENGTH_SHORT).show();
//
//            Runnable runnable=new Runnable(){
//                @Override
//                public void run(){
//                    Toast.makeText(Register.this, "Please Wait!",Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent (Register.this, Login.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                    finish();
//                }
//            };
//
//            handler.postDelayed(runnable, 5000);
//
//            progressBar.setVisibility(View.VISIBLE);
//            mLoadingBar.setTitle("Registration");
//            mLoadingBar.setMessage("Please wait while we load your credentials!");
//            mLoadingBar.setCanceledOnTouchOutside(false);
//            mLoadingBar.show();
//            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
//                @Override
//                        public void onComplete(@NonNull Task<AuthResult> task){
//                    if(task.isSuccessful()){
//                        mLoadingBar.dismiss();
//                        Toast.makeText(Register.this, "Registration successful",Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(Register.this,Home.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                        finish();
//                    }else{
//                        mLoadingBar.dismiss();
//                        Toast.makeText(Register.this,"Registration Failed",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//
//
//        }
//    }
}