package com.example.monogenicdiseases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Check extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;

    ImageButton ScdBtn;
    ImageButton HemoBtn;
    ImageButton cystbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ScdBtn=findViewById(R.id.SCDbtn);
        HemoBtn=findViewById(R.id.Hemophiliabtn);
        cystbtn=findViewById(R.id.cysticbtn);

        ScdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference DiseaseRef= FirebaseDatabase.getInstance().getReference("Disease");
                DiseaseRef.child("Info").child("Selected").setValue("SCD");
                startActivity(new Intent(Check.this, Home.class));

            }
        });

        HemoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference DiseaseRef= FirebaseDatabase.getInstance().getReference("Disease");
                DiseaseRef.child("Info").child("Selected").setValue("Hemophilia");
                startActivity(new Intent(Check.this, Home.class));

            }
        });


        cystbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference DiseaseRef= FirebaseDatabase.getInstance().getReference("Disease");
                DiseaseRef.child("Info").child("Selected").setValue("Cystic Fibrosis");
                startActivity(new Intent(Check.this, Home.class));
            }
        });

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        //toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.nav_SCD){
                    DatabaseReference DiseaseRef= FirebaseDatabase.getInstance().getReference("Disease");
                    DiseaseRef.child("Info").child("Selected").setValue("SCD");
                    Intent i=new Intent(Check.this, Home.class);
                    startActivity(i);

                }else if(id==R.id.nav_CF){
                    DatabaseReference DiseaseRef= FirebaseDatabase.getInstance().getReference("Disease");
                    DiseaseRef.child("Info").child("Selected").setValue("Cystic Fibrosis");
                    Intent i=new Intent(Check.this, Home.class);
                    startActivity(i);
                }else if(id==R.id.nav_HEMO){
                    DatabaseReference DiseaseRef= FirebaseDatabase.getInstance().getReference("Disease");
                    DiseaseRef.child("Info").child("Selected").setValue("Hemophilia");
                    Intent i=new Intent(Check.this, Home.class);
                    startActivity(i);
                }else if(id==R.id.nav_about){
                    Intent i=new Intent(Check.this, About.class);
                    startActivity(i);
                } else if(id==R.id.nav_user){
                    Intent i=new Intent(Check.this, Profile.class);
                    startActivity(i);
                } else if(id==R.id.nav_download) {
                    Intent i = new Intent(Check.this, RetrievePDF.class);
                    startActivity(i);
                }else if(id==R.id.nav_logout){
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(Check.this, Login.class));
                    finish();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}