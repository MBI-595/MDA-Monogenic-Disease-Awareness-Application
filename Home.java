package com.example.monogenicdiseases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class Home extends AppCompatActivity {
    Button mLogOut, downloadAll;
    ImageButton information;
    ImageButton sequence;
    ImageButton structure;
    TextView InfoTxt,SeqTxt,StructTxt;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        mLogOut=findViewById(R.id.logout);
        information=findViewById(R.id.Info);
        sequence=findViewById(R.id.Seq);
        structure=findViewById(R.id.Struct);
        InfoTxt=findViewById(R.id.InfoTextView);
        SeqTxt=findViewById(R.id.SeqTextView);
        StructTxt=findViewById(R.id.StructTextView);
//        downloadAll=findViewById(R.id.DownAll);

        information.setImageResource(R.drawable.prote_info);
//        mLogOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(Home.this, Login.class));
//                finish();
//
//            }
//        });

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Home.this, Info.class));
                finish();
            }
        });

        InfoTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Home.this, Info.class));
                finish();
            }
        });

        sequence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Home.this, Sequence.class));
                finish();
            }
        });

        SeqTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Home.this, Sequence.class));
                finish();
            }
        });

        structure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Home.this, ProteomicStruct.class));
                finish();
            }
        });

        StructTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Home.this, ProteomicStruct.class));
                finish();
            }
        });

//        downloadAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                StorageReference pdfRef2=storageReference.child("sample.pdf");
////                pdfRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
////                    @Override
////                    public void onSuccess(Uri uri) {
////
////                    }
////                });
//                startActivity(new Intent (getApplicationContext(),RetrievePDF.class));
//
//                download();
//            }
//        });


    }

    public void download(){
        storageReference=firebaseStorage.getInstance().getReference();
        ref=storageReference.child("sample");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFiles(Home.this, "sample",".pdf",Environment.DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void downloadFiles(Context context, String fileName, String fileExtension, String destinationDirectory, String url){
        DownloadManager downloadManager=(DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri=Uri.parse(url);

        DownloadManager.Request request=new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context,destinationDirectory,fileName+fileExtension);
        downloadManager.enqueue(request);
    }

    public void onBackPressed() {
        super.onBackPressed();

        Intent i=new Intent(Home.this, Check.class);
        startActivity(i);
    }


}
