package com.example.monogenicdiseases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Diseases extends AppCompatActivity {

    ImageButton ScdBtn;
    ImageButton HuntBtn;
    ImageButton HemoBtn;
    ImageButton cystbtn;

    String Branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        ScdBtn=findViewById(R.id.SCDbtn);
//        HuntBtn=findViewById(R.id.Huntingtonbtn);
//        HemoBtn=findViewById(R.id.Hemophiliabtn);
//        cystbtn=findViewById(R.id.cysticbtn);
//
//        ScdBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatabaseReference DiseaseRef= FirebaseDatabase.getInstance().getReference("Disease");
//                DiseaseRef.child("Selected").setValue("SCD");
//                startActivity(new Intent(Diseases.this, Home.class));
//                finish();
//
//            }
//        });
//
//        HuntBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatabaseReference DiseaseRef= FirebaseDatabase.getInstance().getReference("Disease");
//                DiseaseRef.child("Selected").setValue("Huntington");
//                startActivity(new Intent(Diseases.this, Home.class));
//                finish();
//
//
//            }
//        });
//
//        HemoBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatabaseReference DiseaseRef= FirebaseDatabase.getInstance().getReference("Disease");
//                DiseaseRef.child("Selected").setValue("Hemophilia");
//                startActivity(new Intent(Diseases.this, Home.class));
//                finish();
//
//
//            }
//        });
//
//
//        cystbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatabaseReference DiseaseRef= FirebaseDatabase.getInstance().getReference("Disease");
//                DiseaseRef.child("Selected").setValue("Cystic Fibrosis");
//
//                startActivity(new Intent(Diseases.this, Home.class));
//                finish();
//            }
//        });


    }
}