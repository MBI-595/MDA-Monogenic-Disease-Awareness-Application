package com.example.monogenicdiseases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class ProteomicStruct extends AppCompatActivity {
    ImageView imageView, n_interact,m_interact;
    ImageView mutated_imageView, comp, connect, disconnect;
    Button n_cn3d, m_cn3d;
    TextView timerText, norm,inorm,mutant,imutant, txtconnect, txtdisconnect;
    Timer timer;
    TimerTask timerTask;
    Double time=0.0;
    String code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proteomic_struct);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageView=findViewById(R.id.rand);
        mutated_imageView=findViewById(R.id.mutated_image);
        n_cn3d=findViewById(R.id.normal_cn3d);
        m_cn3d=findViewById(R.id.mutated_cn3d);
        n_interact=findViewById(R.id.rand1);
        m_interact=findViewById(R.id.mutated_image2);
        norm=findViewById(R.id.normstructtxt);
        inorm=findViewById(R.id.norminteracttxt);
        mutant=findViewById(R.id.mutantstructtxt);
        imutant=findViewById(R.id.mutantinteracttxt);
        comp=findViewById(R.id.comp);
        connect=findViewById(R.id.connect);
        disconnect=findViewById(R.id.disconnect);
        txtconnect=findViewById(R.id.txtconnect);
        txtdisconnect=findViewById(R.id.txtdisconnect);

        DatabaseReference iRef=FirebaseDatabase.getInstance().getReference("Disease");
        iRef.child("Info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DiseaseModel dis = snapshot.getValue(DiseaseModel.class);
                code = dis.getSelected();

                switch (code) {
                    case "SCD":
//                        imageView.setImageResource(R.drawable.sHBA);
                        Glide.with(getApplicationContext()).load(R.drawable.hemo_2).into(imageView);
                        Glide.with(getApplicationContext()).load(R.drawable.hemo_gif).into(mutated_imageView);

                        norm.setText("Hemoglobin Reference Structure");
                        inorm.setText("Hemoglobin Structure Interactions");
                        mutant.setText("HBS Hemoglobin Structure");
                        imutant.setText("HBS Structure interactions");

                        m_interact.setImageResource(R.drawable.i1gzx);
                        n_interact.setImageResource(R.drawable.i2hbs);

                        comp.setImageResource(R.drawable.scd_comp);
                        connect.setVisibility(View.GONE);
                        disconnect.setVisibility(View.GONE);
                        txtconnect.setVisibility(View.GONE);
                        txtdisconnect.setVisibility(View.GONE);
                        break;
                    case "Cystic Fibrosis":
                        Glide.with(getApplicationContext()).load(R.drawable.ae_uak).into(imageView);
                        Glide.with(getApplicationContext()).load(R.drawable.ae_2bbt).into(mutated_imageView);

                        norm.setText("CFTR Reference Structure");
                        inorm.setText("CFTR Structure Interactions");
                        mutant.setText("Mutated CFTR Structure");
                        imutant.setText("Mutated CFTR interactions");

                        m_interact.setImageResource(R.drawable.i5uak);
                        n_interact.setImageResource(R.drawable.i2bbt);

                        comp.setImageResource(R.drawable.cf_comp);
                        connect.setImageResource(R.drawable.cf_connect);
                        disconnect.setImageResource(R.drawable.cf_disconnect);
                        break;
                    case "Hemophilia":
                        Glide.with(getApplicationContext()).load(R.drawable.ae_1d7p).into(imageView);
                        Glide.with(getApplicationContext()).load(R.drawable.ae_3cdz).into(mutated_imageView);

                        norm.setText("FactorVIII Reference Structure");
                        inorm.setText("FactorVIII Structure Interactions");
                        mutant.setText("Mutated FactorVIII Structure");
                        imutant.setText("Mutated FactorVIII interactions");

                        m_interact.setImageResource(R.drawable.i1d7p);
                        n_interact.setImageResource(R.drawable.i3cdz);

                        comp.setImageResource(R.drawable.cf_comp);
                        connect.setImageResource(R.drawable.hemo_connect);
                        disconnect.setImageResource(R.drawable.hemo_disconnect);
                        break;

//                        n_interact.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                new AlertDialog.Builder(ProteomicStruct.this)
//                                        .setMessage("The interactions between the chains of Hemoglobin are displayed in this interaction diagram")
//                                        .setCancelable(true)
//                                        .show();
//
//                            }
//                        });
//
//                        m_interact.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                new AlertDialog.Builder(ProteomicStruct.this)
//                                        .setMessage("The interactions between the chains of Hemoglobin are displayed in this interaction diagram")
//                                        .setCancelable(true)
//                                        .show();
//
//                            }
//                        });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference iRef=FirebaseDatabase.getInstance().getReference("Disease");
                iRef.child("Info").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                        code= dis.getSelected();
                        switch (code){
                            case "SCD":
                                new AlertDialog.Builder(ProteomicStruct.this)
                                        .setMessage("1GZX\n" +
                                                "oxy T state haemoglobin: oxygen bound at all four haems\n" +
                                                "PDB DOI: 10.2210/pdb1GZX/pdb\n" +
                                                "Classification: OXYGEN TRANSPORT\n" +
                                                "Organism(s): Homo sapiens\n" +
                                                "Mutation(s): No \n" +
                                                "Total Structure Weight: 64.68 kDa \n" +
                                                "Atom Count: 4769 \n" +
                                                "Modelled Residue Count: 574 \n" +
                                                "Deposited Residue Count: 574 \n" +
                                                "Unique protein chains: 2")
                                        .setCancelable(true)
                                        .show();
                                break;
                            case "Cystic Fibrosis":
                                new AlertDialog.Builder(ProteomicStruct.this)
                                        .setMessage("5UAK\n" +
                                                "Dephosphorylated, ATP-free human cystic fibrosis transmembrane conductance regulator (CFTR)\n" +
                                                "PDB DOI: 10.2210/pdb5UAK/pdbEntry: 5UAK supersedes: 5U71EM Map EMD-8516: EMDB EMDataResource\n" +
                                                "Classification: MEMBRANE PROTEIN, HYDROLASE\n" +
                                                "Organism(s): Homo sapiens\n" +
                                                "Expression System: Homo sapiens\n" +
                                                "Mutation(s): No \n" +
                                                "Membrane Protein: Yes \n" +
                                                "Macromolecule Content\n" +
                                                "\n" +
                                                "Total Structure Weight: 170.99 kDa \n" +
                                                "Atom Count: 9232 \n" +
                                                "Modelled Residue Count: 1158 \n" +
                                                "Deposited Residue Count: 1508 \n" +
                                                "Unique protein chains: 2")
                                        .setCancelable(true)
                                        .show();
                                break;
                            case "Hemophilia":
                                new AlertDialog.Builder(ProteomicStruct.this)
                                        .setMessage("1D7P\n" +
                                                "Crystal structure of the c2 domain of human factor viii at 1.5 a resolution at 1.5 A\n" +
                                                "PDB DOI: 10.2210/pdb1D7P/pdb\n" +
                                                "Classification: BLOOD CLOTTING\n" +
                                                "Organism(s): Homo sapiens\n" +
                                                "Expression System: Saccharomyces cerevisiae\n" +
                                                "Mutation(s): Yes \n" +
                                                "Membrane Protein: Yes\n" +
                                                "Macromolecule Content\n" +
                                                "\n" +
                                                "Total Structure Weight: 18.46 kDa \n" +
                                                "Atom Count: 1538 \n" +
                                                "Modelled Residue Count: 159 \n" +
                                                "Deposited Residue Count: 159 \n" +
                                                "Unique protein chains: 1")
                                        .setCancelable(true)
                                        .show();
                                break;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        mutated_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference iRef=FirebaseDatabase.getInstance().getReference("Disease");
                iRef.child("Info").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                        code= dis.getSelected();
                        switch (code){
                            case "SCD":
                                new AlertDialog.Builder(ProteomicStruct.this)
                                        .setMessage("2HBS\n" +
                                                "THE HIGH RESOLUTION CRYSTAL STRUCTURE OF DEOXYHEMOGLOBIN S\n" +
                                                "PDB DOI: 10.2210/pdb2HBS/pdb\n" +
                                                "Classification: OXYGEN TRANSPORT\n" +
                                                "Organism(s): Homo sapiens\n" +
                                                "Mutation(s): Yes \n \n" +
                                                "Macromolecule Content\n" +
                                                "\n" +
                                                "Total Structure Weight: 128.97 kDa \n" +
                                                "Atom Count: 9677 \n" +
                                                "Modelled Residue Count: 1148 \n" +
                                                "Deposited Residue Count: 1148 \n" +
                                                "Unique protein chains: 2")
                                        .setCancelable(true)
                                        .show();
                                break;
                            case "Cystic Fibrosis":
                                new AlertDialog.Builder(ProteomicStruct.this)
                                        .setMessage("2BBT\n" +
                                                "Human deltaF508 NBD1 with two solublizing mutations.\n" +
                                                "PDB DOI: 10.2210/pdb2BBT/pdb\n" +
                                                "Classification: TRANSPORT PROTEIN\n" +
                                                "Organism(s): Homo sapiens\n" +
                                                "Expression System: Escherichia coli\n" +
                                                "Mutation(s): Yes \n\n" +
                                                "Macromolecule Content\n" +
                                                "\n" +
                                                "Total Structure Weight: 65.95 kDa \n" +
                                                "Atom Count: 4172 \n" +
                                                "Modelled Residue Count: 507 \n" +
                                                "Deposited Residue Count: 580 \n" +
                                                "Unique protein chains: 1")
                                        .setCancelable(true)
                                        .show();
                                break;
                            case "Hemophilia":
                                new AlertDialog.Builder(ProteomicStruct.this)
                                        .setMessage("3CDZ\n" +
                                                "Crystal structure of human factor VIII\n" +
                                                "PDB DOI: 10.2210/pdb3CDZ/pdb\n" +
                                                "Classification: BLOOD CLOTTING\n" +
                                                "Organism(s): Homo sapiens\n" +
                                                "Expression System: Cricetulus griseus\n" +
                                                "Mutation(s): Yes \n \n" +
                                                "Macromolecule Content\n" +
                                                "\n" +
                                                "Total Structure Weight: 167.25 kDa \n" +
                                                "Atom Count: 10317 \n" +
                                                "Modelled Residue Count: 1261 \n" +
                                                "Deposited Residue Count: 1438 \n" +
                                                "Unique protein chains: 2")
                                        .setCancelable(true)
                                        .show();
                                break;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        n_cn3d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference iRef=FirebaseDatabase.getInstance().getReference("Disease");
                iRef.child("Info").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                        code= dis.getSelected();
                        switch (code){
                            case "SCD":
                                Uri SCDuri= Uri.parse("https://www.ncbi.nlm.nih.gov/Structure/pdb/1GZX");
                                startActivity(new Intent(Intent.ACTION_VIEW,SCDuri));
                                break;
                            case "Cystic Fibrosis":
                                Uri Cysturi= Uri.parse("https://www.ncbi.nlm.nih.gov/Structure/pdb/5UAK");
                                startActivity(new Intent(Intent.ACTION_VIEW,Cysturi));
                                break;
                            case "Hemophilia":
                                Uri Hemouri= Uri.parse("https://www.ncbi.nlm.nih.gov/Structure/pdb/1D7P");
                                startActivity(new Intent(Intent.ACTION_VIEW,Hemouri));
                                break;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        m_cn3d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference iRef=FirebaseDatabase.getInstance().getReference("Disease");
                iRef.child("Info").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                        code= dis.getSelected();
                        switch (code){
                            case "SCD":
                                Uri SCDuri= Uri.parse("https://www.ncbi.nlm.nih.gov/Structure/pdb/2HBS");
                                startActivity(new Intent(Intent.ACTION_VIEW,SCDuri));
                                break;
                            case "Cystic Fibrosis":
                                Uri CFuri= Uri.parse("https://www.ncbi.nlm.nih.gov/Structure/pdb/2BBT");
                                startActivity(new Intent(Intent.ACTION_VIEW,CFuri));
                                break;
                            case "Hemophilia":
                                Uri Hemouri= Uri.parse("https://www.ncbi.nlm.nih.gov/Structure/pdb/3CDZ");
                                startActivity(new Intent(Intent.ACTION_VIEW,Hemouri));
                                break;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


//        timerText=(TextView)findViewById(R.id.structTimer);
        timer=new Timer();
        timerTask=new TimerTask() {
            @Override
            public void run() {
                time++;
                if(timerText!=null){
                    com.example.monogenicdiseases.Timer timer=new com.example.monogenicdiseases.Timer(getTimerText());

                    FirebaseDatabase.getInstance().getReference("Users").child("DATA").child("Structure").setValue(timer);
//                    timerText.setText(getTimerText());
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask,0,1000);

    }
    private String getTimerText() {
        int rounded=(int)Math.round(time);
        int seconds=((rounded%86400)%3600)%60;
        int minutes=((rounded%86400)%3600)/60;
        int hours=((rounded%86400)/3600);
        return formatTime(seconds,minutes,hours);
    }

    private String formatTime(int seconds, int minutes, int hours) {
        return String.format("%02d",hours)+":"+
                String.format("%02d",minutes)+":"+
                String.format("%02d",seconds);
    }

    @Override
    public void onBackPressed() {
        timer.cancel();
        super.onBackPressed();
    }
}