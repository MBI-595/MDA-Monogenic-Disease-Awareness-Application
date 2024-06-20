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


public class TestsFragment extends Fragment {
    String code;
    TextView test1,test2,test3;
    ImageView i1,i2,i3,i4,i5,i6;
    TextView t1,t2,t3,t4,t5,t6;


    public TestsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tests, container, false);
        TextView Ttxt = (TextView) view.findViewById(R.id.testsview);
        TextView TestsHeading=(TextView) view.findViewById(R.id.TestHead);
        i1=(ImageView) view.findViewById(R.id.testpic1);
        i2=(ImageView) view.findViewById(R.id.testpic2);
        i3=(ImageView) view.findViewById(R.id.testpic3);
        i4=(ImageView) view.findViewById(R.id.testpic4);
        i5=(ImageView) view.findViewById(R.id.testpic5);
        i6=(ImageView) view.findViewById(R.id.testpic6);
        t1=(TextView) view.findViewById(R.id.testtxt1);
        t2=(TextView) view.findViewById(R.id.testtxt2);
        t3=(TextView) view.findViewById(R.id.testtxt3);
        t4=(TextView) view.findViewById(R.id.testtxt4);
        t5=(TextView) view.findViewById(R.id.testtxt5);
        t6=(TextView) view.findViewById(R.id.testtxt6);
        test2=(TextView) view.findViewById(R.id.testsview2);
        test3=(TextView) view.findViewById(R.id.testsview3);

        DatabaseReference iRef=FirebaseDatabase.getInstance().getReference("Disease");
        iRef.child("Info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DiseaseModel dis = snapshot.getValue(DiseaseModel.class);
                String name = dis.getSelected() + " Symptoms.txt";
                code = dis.getSelected();

                switch (code) {
                    case "SCD":
                        i1.setImageResource(R.drawable.sickling);
                        i2.setImageResource(R.drawable.solubility);
                        i3.setVisibility(View.GONE);
                        i4.setVisibility(View.GONE);
                        i5.setVisibility(View.GONE);
                        i6.setVisibility(View.GONE);

                        t1.setText("1. SICKLING TEST");
                        t2.setText("2. SOLUBILITY TEST");
                        t3.setVisibility(View.GONE);
                        t4.setVisibility(View.GONE);
                        t5.setVisibility(View.GONE);
                        t6.setVisibility(View.GONE);

                        test2.setText("SOLUBILITY TEST:  A sickle turbidity tube test involves the addition of dithionite to a blood sample. If the mixture becomes cloudy, Hb S is likely present.");
                        test3.setText("HEMOGLOBIN ELECTROPHORESIS: The substitution of the nonpolar Valine for the charged Glutamic acid results in decreased mobility of the HbS in the electric field as compared to HbA. This altered mobility is due to the presence of less negative charge on the two B-globin chains.");
                        break;
                    case "Cystic Fibrosis":
                        i1.setImageResource(R.drawable.sweat_test);
                        i2.setImageResource(R.drawable.genetics_testing);
                        i3.setVisibility(View.GONE);
                        i4.setVisibility(View.GONE);
                        i5.setVisibility(View.GONE);
                        i6.setVisibility(View.GONE);

                        t1.setText("1- SWEAT TEST");
                        t2.setText("2- GENETIC TESTING");
                        t3.setVisibility(View.GONE);
                        t4.setVisibility(View.GONE);
                        t5.setVisibility(View.GONE);
                        t6.setVisibility(View.GONE);

                        test2.setText(" GENETIC TESTING: When a new born child has signs and symptoms confirmed, diagnosis of CF can usually be done by using Blood Test.  Since it is an inherited disease, family members should be checked for possible diagnosis. They are also able to test your unborn child as well.");
                        test3.setVisibility(View.GONE);
                        break;
                    case "Hemophilia":
                        i1.setImageResource(R.drawable.factor);
                        i2.setImageResource(R.drawable.blood_count);
                        i3.setImageResource(R.drawable.dna_test);
                        i4.setImageResource(R.drawable.sweat_test);
                        i5.setImageResource(R.drawable.family_history);
                        i6.setVisibility(View.GONE);

                        t1.setText("1. Blood test, clotting factor levels");
                        t2.setText("2. Blood counts");
                        t3.setText("3. DNA testing");
                        t4.setText("4. Assessment of bleeding times");
                        t5.setText("5. Family history");
                        t6.setVisibility(View.GONE);

                        test2.setVisibility(View.GONE);
                        test3.setVisibility(View.GONE);
                        break;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button google;
        Button download;

        download=view.findViewById(R.id.Download3);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File d=getActivity().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                DatabaseReference disRef=FirebaseDatabase.getInstance().getReference("Disease");
                disRef.child("Info").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                        code= dis.getSelected();
                        String name=dis.getSelected()+" Tests.txt";
                        try {
                            File file=new File(d,name);
                            FileWriter writer=new FileWriter(file);
                            writer.append(TestsHeading.getText().toString()+": "+Ttxt.getText().toString());
                            writer.close();
                            new AlertDialog.Builder(getActivity())
                                    .setMessage("File saved to path: "+getActivity().getFilesDir()+"/"+name)
                                    .setCancelable(false)
                                    .setPositiveButton("Ok",new DialogInterface.OnClickListener(){
                                        @Override
                                        public void onClick(DialogInterface dialog, int which){

                                        }
                                    }).show();
                           /// Toast.makeText(getActivity(),"File saved to path: "+getActivity().getFilesDir()+"/"+name,Toast.LENGTH_LONG).show();
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

        google=view.findViewById(R.id.Browse3);
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
//                Uri uri= Uri.parse("https://scholar.google.com/scholar?hl=en&as_sdt=0%2C5&q=sickle+cell+disease&oq=sickle+cell+di");
//                startActivity(new Intent(Intent.ACTION_VIEW,uri));
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
//                Ttxt.setText(model.getTests());
//                //Ptxt.setText(model.getProviders());
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

//        DatabaseReference INFO= FirebaseDatabase.getInstance().getReference("Monogenic Diseases");
//        String ID="Info";
//
//        INFO.child(ID).child("SCD").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                DBModel model = snapshot.getValue(DBModel.class);
//               // Itxt.setText(model.getintro());
////                Stxt.setText(model.getSypmtoms());
//                Ttxt.setText(model.getTests());
//                //Ptxt.setText(model.getProviders());
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
                TestsHeading.setText("Tests for "+select);

                DatabaseReference INFO= FirebaseDatabase.getInstance().getReference("Monogenic Diseases");
                String ID="Info";

                INFO.child(ID).child(select).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DBModel model = snapshot.getValue(DBModel.class);
//                        Itxt.setText(model.getintro());
//                        Stxt.setText(model.getSypmtoms());
                        Ttxt.setText(model.getTests());
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
}