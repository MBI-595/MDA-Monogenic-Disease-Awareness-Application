package com.example.monogenicdiseases;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class IntroFragment extends Fragment {
    String code;
    ImageView normimg, mutantimg, infome;
    TextView fourthText,fifthText, norm, mutant, p1, p2, p3;
    TextView sub1, sub2, sub3, sub4, sub5;
    Timer timer;
    TimerTask timerTask;
    Double time=0.0;

    BarChart barChart;
    PieChart pieChart;



    public IntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        TextView Itxt = (TextView) view.findViewById(R.id.info2);
        TextView IntroHeading=(TextView) view.findViewById(R.id.IntroHead);
        Context context;
//        timerText=(TextView)view.findViewById(R.id.timer);
        norm=(TextView)view.findViewById(R.id.infotxt1);
        mutant=(TextView)view.findViewById(R.id.infotxt2);
        normimg=(ImageView)view.findViewById(R.id.infoimg1);
        mutantimg=(ImageView)view.findViewById(R.id.infoimg2);
        infome=(ImageView)view.findViewById(R.id.infome);
        fourthText=(TextView)view.findViewById(R.id.infotxt4);
        fifthText=(TextView)view.findViewById(R.id.infotxt5);
        pieChart=(PieChart) view.findViewById(R.id.PrevalencePie);
        barChart=(BarChart) view.findViewById(R.id.PrevalenceBar);
        p1=(TextView) view.findViewById(R.id.p1);
        p2=(TextView) view.findViewById(R.id.p2);
        p3=(TextView) view.findViewById(R.id.p3);
        sub1=(TextView) view.findViewById(R.id.sub1);
        sub2=(TextView) view.findViewById(R.id.sub2);
        sub3=(TextView) view.findViewById(R.id.sub3);
        sub4=(TextView) view.findViewById(R.id.sub4);
        sub5=(TextView) view.findViewById(R.id.sub5);

        DatabaseReference iRef=FirebaseDatabase.getInstance().getReference("Disease");
        iRef.child("Info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                String name=dis.getSelected()+" Symptoms.txt";
                code= dis.getSelected();
                switch (code){
                    case "SCD":
                        normimg.setImageResource(R.drawable.pills);
                        mutantimg.setImageResource(R.drawable.scd);
                        infome.setImageResource(R.drawable.gscd);

                        Glide.with(getActivity()).load(R.drawable.gscd).into(infome);

                        norm.setText("Red blood cells are usually round and flexible, so they move easily through blood vessels.");
                        mutant.setText("In sickle cell anemia, some red blood cells are shaped like sickles or crescent moons.");
                        fourthText.setText("HbS (Sickle cell mutation) is caused by a mutation in the beta-globin gene in which the 17th nucleotide is changed from thymine to adenine and as a result, the sixth amino acid in the β-globin chain becomes valine instead of glutamic acid.  (Pecker, L. H., & Lanzkron, S. , 2021) This mutation produces a hydrophobic motif in the deoxygenated HbS tetramer that results in binding between β1 and β2 chains of two hemoglobin molecules. This crystallization produces a polymer nucleus, which grows and fills the red blood cell, disrupting its architecture and flexibility and promoting cellular dehydration, with physical and oxidative cellular stress resulting in a sickle-shaped cell.");
                        fifthText.setText("Sickle Cell Disease usually manifests itself in individuals at about six months of age and claims thousands of lives per year. In recent years, however, due to improved medication and healthcare facilities, the rate of child mortality has decreased dramatically and even for those individuals with sickle cell anemia, the most severe form of the disease, the median age of death was 42 years of age for males and 48 years of age for females.");
                        pieChart.setVisibility(View.GONE);
                        sub1.setVisibility(View.GONE);
                        sub2.setVisibility(View.GONE);
                        sub3.setVisibility(View.GONE);
                        sub4.setVisibility(View.GONE);
                        sub5.setVisibility(View.GONE);

                        ArrayList<BarEntry> barEntries=new ArrayList<>();

                        barChart.setVisibility(View.VISIBLE);
                        BarEntry barEntry1=new BarEntry(1,1);
                        barEntries.add(barEntry1);

                        BarEntry barEntry2=new BarEntry(2,3);
                        barEntries.add(barEntry2);

                        BarEntry barEntry3=new BarEntry(3,6);
                        barEntries.add(barEntry3);

                        BarDataSet barDataSet=new BarDataSet(barEntries,"Percentage population with SCD trait");
                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        barDataSet.setDrawValues(false);
                        barChart.setData(new BarData(barDataSet));
                        barChart.animateY(5000);
                        barChart.getDescription().setText("HBS carriers percentage");
                        barChart.getDescription().setTextColor(Color.BLUE);
                        break;
                    case "Cystic Fibrosis":
                        normimg.setImageResource(R.drawable.hlungs);
                        mutantimg.setImageResource(R.drawable.slungs);
                        infome.setImageResource(R.drawable.gcf);

                        Glide.with(getActivity()).load(R.drawable.gcf).into(infome);

                        norm.setText("Cystic fibrosis affects the cells that produce mucus, sweat and digestive juices that are normally thin and slippery.");
                        mutant.setText("But in people with CF, a defective gene causes the secretions to become sticky and thick.");
                        fourthText.setText("CF is caused by a single gene mutation. A malfunction (mutation) in the cystic fibrosis transmembrane conductance regulator (CFTR) gene on the long arm of chromosome 7 causes a protein to change in cystic fibrosis, which governs the transport of salt in and out of cells. There are around 1,900 mutations that have been reported up to now. Of them, only twenty mutations have a frequency of 0.1%. The most common variant is F508del having 90% allelic frequency. Mutation in the F508del results in the deletion of a single amino acid(Phe) at position 508.");
                        fifthText.setText("The age of onset of cystic fibrosis varies with mutation and severity of the disease.");

                        pieChart.setVisibility(View.GONE);
                        sub1.setVisibility(View.GONE);
                        sub2.setVisibility(View.GONE);
                        sub3.setVisibility(View.GONE);
                        sub4.setVisibility(View.GONE);
                        sub5.setVisibility(View.GONE);

                        p1.setText("F508del");
                        p2.setText("DF508");
                        p3.setVisibility(View.GONE);

                        ArrayList<BarEntry> barrEntries=new ArrayList<>();

                        barChart.setVisibility(View.VISIBLE);
                        BarEntry barrEntry1=new BarEntry(1,33);
                        barrEntries.add(barrEntry1);

                        BarEntry barrEntry2=new BarEntry(2,86);
                        barrEntries.add(barrEntry2);

                        BarDataSet barrDataSet=new BarDataSet(barrEntries,"Percentage mutations of CFTR");
                        barrDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        barrDataSet.setDrawValues(false);
                        barChart.setData(new BarData(barrDataSet));
                        barChart.animateY(5000);
                        barChart.getDescription().setText("CFTR mutations");
                        barChart.getDescription().setTextColor(Color.BLUE);
                        break;
                    case "Hemophilia":
                        normimg.setImageResource(R.drawable.clot);
                        mutantimg.setImageResource(R.drawable.unclot);
                        infome.setImageResource(R.drawable.ii1);
                        fourthText.setText("Hemophilia is usually an inherited bleeding disorder in which the blood does not clot properly. This can lead to spontaneous bleeding as well as bleeding following injuries or surgery. Blood contains many proteins called clotting factors that can help to stop bleeding. Hemophilia A and B are inherited as X-linked recessive disorders. Mostly males are affected by the disease and females are carriers. As they have only one affected X-chromosome, One out of every 5,000 male births has hemophilia. Hemophilia A is characterized by a deficiency in factor VIII (FVIII), while hemophilia B is characterized by a lack in factor IX (FIX). Our focus is Hemophilia A as it is the most common type of bleeding disorder in Pakistan.");
                        fifthText.setText("Mutations in F8 and F9 genes causes hemophilia .Both F8 and F9 genes are located on the X chromosome, the F8 gene is at the end of the long arm at Xq28, and F9 gene on the long arm, more towards the centromere, at Xq27, the F8 gene is at the end of the long arm at Xq28, and F9 gene on the long arm, more towards the centromere, at Xq27");

                        norm.setText("Blood contains many proteins called clotting factors that can help to stop bleeding.");
                        mutant.setText("Hemophilia is usually an inherited bleeding disorder in which the blood does not clot properly.");

                        barChart.setVisibility(View.GONE);
                        p1.setVisibility(View.GONE);
                        p2.setVisibility(View.GONE);
                        p3.setVisibility(View.GONE);
                        ArrayList<PieEntry> pieEntries=new ArrayList<>();

                        pieChart.setVisibility(View.VISIBLE);
                        PieEntry pieEntry1=new PieEntry(1, 52);
                        pieEntries.add(pieEntry1);
                        PieEntry pieEntry2=new PieEntry(2,9 );
                        pieEntries.add(pieEntry2);
                        PieEntry pieEntry3=new PieEntry(3, 3.5);
                        pieEntries.add(pieEntry3);
                        PieEntry pieEntry4=new PieEntry(4, 0.5);
                        pieEntries.add(pieEntry4);
                        PieEntry pieEntry5=new PieEntry(5, 35);
                        pieEntries.add(pieEntry5);

                        PieDataSet pieDataSet=new PieDataSet(pieEntries, "Bleeding disorders in Pakistan by percentage");
                        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        pieChart.setData(new PieData(pieDataSet));
                        pieChart.animateXY(5000,5000);
                        pieChart.getDescription().setEnabled(false);

                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        timer=new Timer();
//        timerTask=new TimerTask() {
//            @Override
//            public void run() {
////                getActivity().runOnUiThread(new Runnable() {
////
////                    @Override
////                    public void run() {
//                        if(timerText!=null){
//                            time++;
//                            com.example.monogenicdiseases.Timer timer=new com.example.monogenicdiseases.Timer(timerText.getText().toString());
//
//                            FirebaseDatabase.getInstance().getReference("Users").child("DATA").child("Information").setValue(timer);
//                            timerText.setText(getTimerText());
//                        }
//
////                    }
////                });
//            }
//        };

//        .addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()){
//                    Toast.makeText(getContext(),"Timer set",Toast.LENGTH_LONG).show();
//                }else {
//                    Toast.makeText(getContext(),"Timer not set",Toast.LENGTH_LONG).show();
//                }
//            }
//        });

//        timer.scheduleAtFixedRate(timerTask,0,1000);

        Button google;
        Button download;

        download=view.findViewById(R.id.Download1);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File d=getActivity().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                DatabaseReference disRef=FirebaseDatabase.getInstance().getReference("Disease");
                disRef.child("Info").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                        code=dis.getSelected();
                        String name=dis.getSelected()+" Intro.txt";
                        try {
                            File file=new File(d,name);
                            FileWriter writer=new FileWriter(file);
                            writer.append(IntroHeading.getText().toString()+": "+Itxt.getText().toString());
                            writer.close();

                            new AlertDialog.Builder(getActivity())
                                    .setMessage("File saved to path: "+getActivity().getFilesDir()+"/"+name)
                                    .setCancelable(false)
                                    .setPositiveButton("Ok",new DialogInterface.OnClickListener(){
                                        @Override
                                        public void onClick(DialogInterface dialog, int which){

                                        }
                                    }).show();

                            //Toast.makeText(getActivity(),"File saved to path: "+getActivity().getFilesDir()+"/"+name,Toast.LENGTH_LONG).show();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



//        download=findViewById(R.id.Download1);
//        download.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String content=IntroHeading.getText().toString()+Itxt.getText().toString();
//
//                File path=getApplicationContext().getFilesDir();
//                try{
//                    FileOutputStream writer=new FileOutputStream(new File(path,"Intro.txt"));
//
//                    writer.write(content.getBytes());
//                    writer.close();
//
//                    Toast.makeText(getApplicationContext(),"Downloaded as file: Intro.txt", Toast.LENGTH_SHORT).show();
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//        });

        google=view.findViewById(R.id.Browse1);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference disRef=FirebaseDatabase.getInstance().getReference("Disease");
                disRef.child("Info").addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                             DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                             code=dis.getSelected();
                             switch (code){
                                  case "SCD":
                                      Uri SCDuri= Uri.parse("https://scholar.google.com/scholar?hl=en&as_sdt=0%2C5&q=sickle+cell+disease&oq=sickle+cell+di");
                                      startActivity(new Intent(Intent.ACTION_VIEW,SCDuri));
                                      break;
                                  case "Cystic Fibrosis":
                                       Uri CYSuri= Uri.parse("https://scholar.google.com/scholar?hl=en&as_sdt=0%2C5&q=cystic+fibrosis&oq=Cystic+");
                                       startActivity(new Intent(Intent.ACTION_VIEW,CYSuri));
                                       break;
                                  case "Hemophilia":
                                       Uri HEMOuri= Uri.parse("https://scholar.google.com/scholar?hl=en&as_sdt=0%2C5&q=Hemophilia&btnG=");
                                       startActivity(new Intent(Intent.ACTION_VIEW,HEMOuri));
                                       break;
                                  case "Huntington":
                                        Uri HUNTuri= Uri.parse("https://scholar.google.com/scholar?hl=en&as_sdt=0%2C5&q=Huntington+disease&btnG=");
                                        startActivity(new Intent(Intent.ACTION_VIEW,HUNTuri));
                                        break;

                                  }

                             }

                             @Override
                             public void onCancelled(@NonNull DatabaseError error) {

                             }
                      });

//                                                           Uri SCDuri = Uri.parse("https://scholar.google.com/scholar?hl=en&as_sdt=0%2C5&q=sickle+cell+disease&oq=sickle+cell+di");
//                startActivity(new Intent(Intent.ACTION_VIEW,SCDuri));
//                switch (code){
//                    case "SCD":
//                        Uri SCDuri= Uri.parse("https://scholar.google.com/scholar?hl=en&as_sdt=0%2C5&q=sickle+cell+disease&oq=sickle+cell+di");
//                        startActivity(new Intent(Intent.ACTION_VIEW,SCDuri));
//                    case "Cystic Fibrosis":
//                        Uri CYSuri= Uri.parse("https://scholar.google.com/scholar?hl=en&as_sdt=0%2C5&q=cystic+fibrosis&oq=Cystic+");
//                        startActivity(new Intent(Intent.ACTION_VIEW,CYSuri));
//                    case "Hemophilia":
//                        Uri HEMOuri= Uri.parse("https://scholar.google.com/scholar?hl=en&as_sdt=0%2C5&q=Hemophilia&btnG=");
//                        startActivity(new Intent(Intent.ACTION_VIEW,HEMOuri));
//                    case "Huntington":
//                        Uri HUNTuri= Uri.parse("https://scholar.google.com/scholar?hl=en&as_sdt=0%2C5&q=Huntington+disease&btnG=");
//                        startActivity(new Intent(Intent.ACTION_VIEW,HUNTuri));
//
//                }
            }
        });
//
//        DatabaseReference DiseaseRef= FirebaseDatabase.getInstance().getReference("Diseases");
//        DiseaseRef.child("Selected").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                DiseaseModel model=snapshot.getValue(DiseaseModel.class);
//                String ID=model.getSelected();
//                Itxt.setText(ID);
//
////                DatabaseReference INFO= FirebaseDatabase.getInstance().getReference("Monogenic Diseases");
////                String ID2="Info";
////
////                INFO.child(ID2).child("SCD").addValueEventListener(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(@NonNull DataSnapshot snapshot) {
////                        DBModel model = snapshot.getValue(DBModel.class);
////                       // Itxt.setText(model.getIntro());
////                        //Stxt.setText(model.getSypmtoms());
////                        //Ttxt.setText(model.getTests());
////                        //Ptxt.setText(model.getProviders());
////                       // Log.d("outrageous", model.getSypmtoms());
////
////                    }
////
////                    @Override
////                    public void onCancelled(@NonNull DatabaseError error) {
////                        Log.e("DBERROR", error.toString());
////
////                    }
////                });
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        DatabaseReference disRef=FirebaseDatabase.getInstance().getReference("Disease");
        disRef.child("Info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                String select=dis.getSelected();
                IntroHeading.setText("Introduction to "+select);

                DatabaseReference INFO= FirebaseDatabase.getInstance().getReference("Monogenic Diseases");
                String ID="Info";

                INFO.child(ID).child(select).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DBModel model = snapshot.getValue(DBModel.class);
                        Itxt.setText(model.getintro());
//                Stxt.setText(model.getSypmtoms());
                        //Ttxt.setText(model.getTests());
                        //Ptxt.setText(model.getProviders());
                        //Log.d("outrageous", model.getSypmtoms());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("DBERROR", error.toString());

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
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
    };


}