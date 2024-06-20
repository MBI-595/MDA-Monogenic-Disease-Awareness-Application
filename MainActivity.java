package com.example.monogenicdiseases;

//     XML CODE
//     app:lottie_rawRes="@raw/burger"

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    final Handler handler=new Handler();
    ImageView imageView,splashImg, DNAanimation;
    TextView appName;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.logo);
        splashImg=findViewById(R.id.img);
        appName=findViewById(R.id.app_name);
        DNAanimation=findViewById(R.id.climbing);
//        lottieAnimationView=findViewById(R.id.lottie);

        splashImg.animate().translationY(-4500).setDuration(5000).setStartDelay(4000);
        imageView.animate().translationY(4500).setDuration(5000).setStartDelay(4000);
        appName.animate().translationY(4500).setDuration(5000).setStartDelay(4000);
        DNAanimation.animate().translationY(4500).setDuration(5000).setStartDelay(4000);
//        lottieAnimationView.animate().translationY(2000).setDuration(1000).setStartDelay(4000);

        Glide.with(this).load(R.drawable.dnaclimb).into(DNAanimation);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Runnable runnable=new Runnable(){
            @Override
            public void run(){
                Intent intent=new Intent (MainActivity.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        };

        handler.postDelayed(runnable, 8000);
    }
}