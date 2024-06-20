package com.example.monogenicdiseases;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileWriter;


public class ProvidersFragment extends Fragment {


    public ProvidersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_providers, container, false);
        TextView Ptxt = (TextView) view.findViewById(R.id.providersview);
        TextView ProvidersHeading=(TextView) view.findViewById(R.id.ProvidersHead);

        Button google;
        Button download;

        download=view.findViewById(R.id.Download4);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                    String FILE_NAME="Example.txt";
//                    String Text="PLEASE PLEASE PLEASE WORKKK";
//                    FileOutputStream fos=null;
//                    fos=openFileOutput(FILE_NAME,MODE_PRIVATE);
//                    fos.write(Text.getBytes());
//                    Toast.makeText(Home.this,"Saved to "+getFilesDir()+" /"+FILE_NAME,Toast.LENGTH_LONG).show();

                    File d=getActivity().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                    DatabaseReference disRef=FirebaseDatabase.getInstance().getReference("Disease");
                    disRef.child("Info").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                            String name=dis.getSelected()+" Test Providers.txt";
                            try {
                                 File file=new File(d,name);
                                 FileWriter writer=new FileWriter(file);
                                 writer.append(ProvidersHeading.getText().toString()+": "+Ptxt.getText().toString());
                                 writer.close();
                                Toast.makeText(getActivity(),"File saved to path: "+getActivity().getFilesDir()+"/"+name,Toast.LENGTH_LONG).show();
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

        google=view.findViewById(R.id.Browse4);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri= Uri.parse("https://www.google.com");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });

//        DatabaseReference INFO= FirebaseDatabase.getInstance().getReference("Monogenic Diseases");
//        String ID="Info";
//
//        INFO.child(ID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                DBModel model = snapshot.getValue(DBModel.class);
//                //Itxt.setText(model.getIntro());
//                //Stxt.setText(model.getSypmtoms());
//                //Ttxt.setText(model.getTests());
//                Ptxt.setText(model.getProviders());
//                Log.d("outrageous", model.getSypmtoms());
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.e("DBERROR", error.toString());
//
//            }
//        });

//        DatabaseReference INFO= FirebaseDatabase.getInstance().getReference("Monogenic Diseases");
//        String ID="Info";
//
//        INFO.child(ID).child("SCD").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                DBModel model = snapshot.getValue(DBModel.class);
//                //Itxt.setText(model.getintro());
////                Stxt.setText(model.getSypmtoms());
//                //Ttxt.setText(model.getTests());
//                Ptxt.setText(model.getProviders());
//                //Log.d("outrageous", model.getSypmtoms());
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.e("DBERROR", error.toString());
//
//            }
//        });

        DatabaseReference disRef=FirebaseDatabase.getInstance().getReference("Disease");
        disRef.child("Info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                String select=dis.getSelected();
                ProvidersHeading.setText("Providers of "+select+" tests");

                DatabaseReference INFO= FirebaseDatabase.getInstance().getReference("Monogenic Diseases");
                String ID="Info";

                INFO.child(ID).child(select).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DBModel model = snapshot.getValue(DBModel.class);
//                        Itxt.setText(model.getintro());
//                Stxt.setText(model.getSypmtoms());
                        //Ttxt.setText(model.getTests());
                        Ptxt.setText(model.getProviders());
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
}