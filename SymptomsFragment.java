package com.example.monogenicdiseases;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileWriter;
import java.util.Timer;
import java.util.TimerTask;


public class SymptomsFragment extends Fragment {
    String code;
    ImageView i1,i2,i3,i4,i5,i6,i7,i8;
    TextView t1,t2,t3,t4,t5,t6,t7,t8;



    public SymptomsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_symptoms, container, false);
        TextView Stxt = (TextView) view.findViewById(R.id.symptomsview);
        TextView SympHeading=(TextView) view.findViewById(R.id.SympHead);
        i1=(ImageView) view.findViewById(R.id.img1);
        i2=(ImageView) view.findViewById(R.id.img2);
        i3=(ImageView) view.findViewById(R.id.img3);
        i4=(ImageView) view.findViewById(R.id.img4);
        i5=(ImageView) view.findViewById(R.id.img5);
        i6=(ImageView) view.findViewById(R.id.img6);

        t1=(TextView) view.findViewById(R.id.simp1);
        t2=(TextView) view.findViewById(R.id.simp2);
        t3=(TextView) view.findViewById(R.id.simp3);
        t4=(TextView) view.findViewById(R.id.simp4);
        t5=(TextView) view.findViewById(R.id.simp5);
        t6=(TextView) view.findViewById(R.id.simp6);

        DatabaseReference iRef=FirebaseDatabase.getInstance().getReference("Disease");
        iRef.child("Info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                String name=dis.getSelected()+" Symptoms.txt";
                code= dis.getSelected();

                switch(code){
                    case "SCD":
                        i1.setImageResource(R.drawable.infection);
                        i2.setImageResource(R.drawable.cough);
                        i3.setImageResource(R.drawable.pale_skin);
                        i4.setImageResource(R.drawable.tired);
                        i5.setImageResource(R.drawable.vomiting);
                        i6.setImageResource(R.drawable.swelling);

                        t1.setText("1- Infection.");
                        t2.setText("2- Coughs or trouble breathing.");
                        t3.setText("3- Pale skin and lips.");
                        t4.setText("4- Tiredness.");
                        t5.setText("5- Vomiting.");
                        t6.setText("6- Swelling of body.");
                        break;
                    case "Cystic Fibrosis":
                        i1.setImageResource(R.drawable.salty);
                        i2.setImageResource(R.drawable.respiratory);
                        i3.setImageResource(R.drawable.infection);
                        i4.setImageResource(R.drawable.weight);
                        i5.setImageResource(R.drawable.meconium);
                        i6.setImageResource(R.drawable.bulky);

                        t1.setText("1- Salty tasting skin ");
                        t2.setText("2- Chronic respiratory problems ");
                        t3.setText("3- Lungs infection ");
                        t4.setText("4- Poor growth /weight loss");
                        t5.setText("5- Meconium ileus");
                        t6.setText("6- Bulky/ Greasy stool");
                        break;
                    case "Hemophilia":
                        i1.setImageResource(R.drawable.dizziness);
                        i2.setImageResource(R.drawable.nosebleed);
                        i3.setImageResource(R.drawable.bleeding);
                        i4.setImageResource(R.drawable.pain);
                        i5.setImageResource(R.drawable.urine_blood);
                        i6.setImageResource(R.drawable.headache);

                        t1.setText("1. Dizziness");
                        t2.setText("2. Nosebleeds");
                        t3.setText("3. Excessive bleeding");
                        t4.setText("4. Painful joints");
                        t5.setText("5. Blood in urine ");
                        t6.setText("6. Headache");
                        break;
//                    case "Huntington":
//                        i1.setImageResource(R.drawable.infection);
//                        i2.setImageResource(R.drawable.cough);
//                        i3.setImageResource(R.drawable.pale_skin);
//                        i4.setImageResource(R.drawable.infection);
//                        i5.setImageResource(R.drawable.infection);
//                        i6.setImageResource(R.drawable.tired);
//                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Button google;
        Button download;

        download=view.findViewById(R.id.Download2);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File d=getActivity().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                DatabaseReference disRef=FirebaseDatabase.getInstance().getReference("Disease");
                disRef.child("Info").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                        String name=dis.getSelected()+" Symptoms.txt";
                        code= dis.getSelected();
                        try {
                            File file=new File(d,name);
                            FileWriter writer=new FileWriter(file);
                            writer.append(SympHeading.getText().toString()+": "+Stxt.getText().toString());
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

        google=view.findViewById(R.id.Browse2);
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
            }
        });

        DatabaseReference disRef=FirebaseDatabase.getInstance().getReference("Disease");
        disRef.child("Info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                String select=dis.getSelected();
                SympHeading.setText("Symptoms of "+select);

                DatabaseReference INFO= FirebaseDatabase.getInstance().getReference("Monogenic Diseases");
                String ID="Info";

                INFO.child(ID).child(select).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DBModel model = snapshot.getValue(DBModel.class);
//                        Itxt.setText(model.getintro());
                          Stxt.setText(model.getSymptoms());
                        //Ttxt.setText(model.getTests());
                        //Ptxt.setText(model.getProviders());
                        //Log.d("outrageous", model.getSypmtoms());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //Log.e("DBERROR", error.toString());

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }

}