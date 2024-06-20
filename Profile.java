package com.example.monogenicdiseases;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.monogenicdiseases.databinding.ActivityMainBinding;
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
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    String sess;
    int session, ttime;
    private TextView user,email,password,ID;
    private String userID;
    ImageView pfp;
    private FirebaseAuth authProfile;
    private Button logout,sessionsbtn,edit,uploadbtn, timespentbtn;
    Uri uri;
    int finInfoTim=0;
    int finSeqTim=0;
    int finStructTim=0;
    int[] a={0,0,0};

    BarChart barChart;
    PieChart pieChart;

    DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Images");
    StorageReference storageReference= FirebaseStorage.getInstance().getReference();

//    ActivityMainBinding binding;
//    ActivityResultLauncher<String> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        StorageReference imageRef2=storageReference.child("images/literally.jpg");
        imageRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(Profile.this).load(uri).error(R.drawable.ic_launcher_background).into(pfp);
            }
        });

        user=findViewById(R.id.pname);
        email=findViewById(R.id.pemail);
        password=findViewById(R.id.ppass);
        ID=findViewById(R.id.pid);
        pfp=findViewById(R.id.pfp);
        uploadbtn=findViewById(R.id.uploadpfp);
//        downloadedbtn=findViewById(R.id.list_downloaded);
        sessionsbtn=findViewById(R.id.sessions);
        timespentbtn=findViewById(R.id.timeSpent);
        barChart=findViewById(R.id.PFPbar_chart);
        pieChart=findViewById(R.id.PFPpie_chart);
//        Seqtime=findViewById(R.id.gtime);
//        Structtime=findViewById(R.id.gtime);

        authProfile=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=authProfile.getCurrentUser();
        reference=FirebaseDatabase.getInstance().getReference("Users");
        userID=firebaseUser.getUid();
        ID.setText(userID);
        reference.child("DATA").child("Information").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Timer Gtime=snapshot.getValue(Timer.class);
                if(Gtime!=null){
                    String Infotime=Gtime.getTime();

//                    Infotime.setText(theTime);
                    String[] i=Infotime.split(":");
                        a[0]=Integer.parseInt(i[0]);
                        a[0]=a[0]*3600;
                        a[1]=Integer.parseInt(i[1]);
                        a[1]=a[1]*60;
                        a[2]=Integer.parseInt(i[2]);
                        a[2]=a[2];

                        for(int j=0;j<3;j++){
                            finInfoTim=finInfoTim+a[j];
                        }

//                    infotime.setText(String.valueOf(finInfoTim));
//                    int seconds=((%86400)%3600)%60;
//                    int minutes=((%86400)%3600)/60;
//                    int hours=((%86400)/3600);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference.child("DATA").child("Sequence").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Timer Gtime=snapshot.getValue(Timer.class);
                if(Gtime!=null){
                    String Seqtime=Gtime.getTime();
//                    Seqtime.setText(theTime);

                    String[] i=Seqtime.split(":");
                    a[0]=Integer.parseInt(i[0]);
                    a[0]=a[0]*3600;
                    a[1]=Integer.parseInt(i[1]);
                    a[1]=a[1]*60;
                    a[2]=Integer.parseInt(i[2]);
                    a[2]=a[2];

                    for(int j=0;j<3;j++){
                        finSeqTim=finSeqTim+a[j];
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference.child("DATA").child("Structure").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Timer Gtime=snapshot.getValue(Timer.class);
                if(Gtime!=null){
                    String Structtime=Gtime.getTime();
//                    Structtime.setText(theTime);

                    String[] i=Structtime.split(":");
                    a[0]=Integer.parseInt(i[0]);
                    a[0]=a[0]*3600;
                    a[1]=Integer.parseInt(i[1]);
                    a[1]=a[1]*60;
                    a[2]=Integer.parseInt(i[2]);
                    a[2]=a[2];

                    for(int j=0;j<3;j++){
                        finStructTim=finStructTim+a[j];
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ttime=finInfoTim+finSeqTim+finStructTim;

        DatabaseReference iRef=FirebaseDatabase.getInstance().getReference("Sessions");
        iRef.child("number").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                sess= dis.getSelected();
                session=Integer.parseInt(sess);
                if (session<=10){
                    session=session++;
                    DiseaseModel model=new DiseaseModel(String.valueOf(ttime));
                    FirebaseDatabase.getInstance().getReference("Sessions").child("number").child(String.valueOf(session)).setValue(model);
                }else{
                    session=1;
                    DiseaseModel model=new DiseaseModel(String.valueOf(ttime));
                    FirebaseDatabase.getInstance().getReference("Sessions").child("number").child(String.valueOf(session)).setValue(model);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayList<BarEntry> barEntries=new ArrayList<>();
        ArrayList<PieEntry> pieEntries=new ArrayList<>();


        timespentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barChart.setVisibility(View.VISIBLE);
                BarEntry barEntry1=new BarEntry(1,finInfoTim);
                barEntries.add(barEntry1);

                BarEntry barEntry2=new BarEntry(2,finSeqTim);
                barEntries.add(barEntry2);

                BarEntry barEntry3=new BarEntry(3,finStructTim);
                barEntries.add(barEntry3);


//                for(int i=1;i<10;i++){
//                    float value=(float)(i*10.0);
//                    BarEntry barEntry=new BarEntry(i,value);
//                    barEntries.add(barEntry);
//                }

                BarDataSet barDataSet=new BarDataSet(barEntries,"Time Spent");
                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                barDataSet.setDrawValues(false);
                barChart.setData(new BarData(barDataSet));
                barChart.animateY(5000);
                barChart.getDescription().setText("Time Spent Chart");
                barChart.getDescription().setTextColor(Color.BLUE);
            }
        });

        sessionsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pieChart.setVisibility(View.VISIBLE);
                DatabaseReference iRef=FirebaseDatabase.getInstance().getReference("Sessions");
                iRef.child("number").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                        sess= dis.getSelected();
                        session=Integer.parseInt(sess);
                        for(int i=1;i<10;i++){
                            float value=(float)(i*10.0);
                            PieEntry pieEntry=new PieEntry(i, value);
                            pieEntries.add(pieEntry);
                        }
                        PieDataSet pieDataSet=new PieDataSet(pieEntries, "Last Ten Sessions");
                        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        pieChart.setData(new PieData(pieDataSet));
                        pieChart.animateXY(5000,5000);
                        pieChart.getDescription().setEnabled(false);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        pfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,2);
            }
        });
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uri!=null){
                    UploadImageToFirebase(uri);
                }else{
                    Toast.makeText(Profile.this,"Please Select Image First!",Toast.LENGTH_SHORT).show();
                }
            }
        });
//
//        binding=ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        launcher=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
//                    @Override
//                    public void onActivityResult(Uri uri) {
//                        binding.imageView.setImageURI(uri);
//
//                        final StorageReference reference=storage.getReference().child("image");
//                        reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                    @Override
//                                    public void onSuccess(Uri uri) {
//                                        database.getReference().child("image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void unused) {
//                                                Toast.makeText(getApplicationContext(),"Image Uploaded",Toast.LENGTH_LONG).show();
//                                            }
//                                        });
//                                    }
//                                });
//                            }
//                        });
//
//                    }
//                });
//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //launcher.launch("image/*");
//            }
//        });

        logout = findViewById(R.id.LOGOUT);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this,Login.class));
            }
        });

//        downloadedbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Profile.this,DownloadedFiles.class));
//            }
//        });

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile=snapshot.getValue(User.class);
                if(userProfile!=null){
                    String fullName=userProfile.getFullname();
                    String useremail=userProfile.getEmail();

                    user.setText(fullName);
                    email.setText(useremail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if(firebaseUser==null){
            Toast.makeText(Profile.this,"Something went wrong! Information could not be retrieved",Toast.LENGTH_LONG).show();
        }else{
            //showUserProfile(firebaseUser);
        }


    }
    private void UploadImageToFirebase(Uri uri) {
        StorageReference file=storageReference.child("images/literally.jpg");
        file.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        ImageModel model=new ImageModel(uri.toString());
                        String Smodel=reference.push().getKey();
                        reference.child(Smodel).setValue(model);
//                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(Profile.this,"Image Uploaded",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Profile.this, "FAILED!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap maps=MimeTypeMap.getSingleton();
        return maps.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 && resultCode==RESULT_OK && data!=null){
            uri=data.getData();
            pfp.setImageURI(uri);
        }
    }

//    public void downloadViaUrl(){
//        StorageReference imageRef2=storageReference.child("images/Info.jpg";
//        imageRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Glide.with(Profile.this).load(uri).error(R.drawable.ic_launcher_background).into(pfp);
//            }
//        });
//    }

//    private void showUserProfile(FirebaseUser firebaseUser){
//        String userID=firebaseUser.getUid();
//        reference= FirebaseDatabase.getInstance().getReference("Users");
//        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ReadWriteUserDetails readWriteUserDetails=snapshot.getValue(ReadWriteUserDetails.class);
//                if(readWriteUserDetails!=null){
//                    //FullName=firebaseUser.getName();
//                    useremail=firebaseUser.getEmail();
//
//                    user.setText(FullName);
//                    email.setText(useremail);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(Profile.this,"Something went wrong",Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
}