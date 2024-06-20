package com.example.monogenicdiseases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DBTest extends AppCompatActivity {

    Button btn;
    TextView Itxt;
    TextView Stxt;
    TextView Ttxt;
    TextView Ptxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtest);
        btn=findViewById(R.id.tstbutton);
        Itxt=findViewById(R.id.info2);
        Stxt=findViewById(R.id.symptomsview);
        Ttxt=findViewById(R.id.testsview);
        Ptxt=findViewById(R.id.providersview);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference INFO= FirebaseDatabase.getInstance().getReference("Monogenic Diseases");
                String ID="Info";
//                DBModel dbModel=new DBModel("Sickle cell disease is an inherited blood disorder marked by defective hemoglobin. It inhibits the ability of hemoglobin in red blood cells to carry oxygen. Sickle cells tend to stick together, blocking small blood vessels causing painful and damaging complications","Anemia. " +
//                        "Sickle cells break apart easily and die. " +
//                        "Episodes of pain. " +
//                        "Periodic episodes of extreme pain, called pain crises, are a major symptom of sickle cell anemia. " +
//                        "Swelling of hands and feet. " +
//                        "Frequent infections. " +
//                        "Delayed growth or puberty. " +
//                        "Vision problems.", "GENETIC TESTING","HOSPITALS");
//
//                    INFO.child(ID).setValue(dbModel);

                INFO.child(ID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DBModel model = snapshot.getValue(DBModel.class);
                        Itxt.setText(model.getintro());
                        Stxt.setText(model.getSymptoms());
                        Ttxt.setText(model.getTests());
                        Ptxt.setText(model.getProviders());
                        Log.d("outrageous", model.getSymptoms());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("DBERROR", error.toString());

                    }
                });



            }
        });


    }

    public void onBackPressed() {
        super.onBackPressed();

        Intent i=new Intent(DBTest.this, Diseases.class);
        startActivity(i);
    }
}