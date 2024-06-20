package com.example.monogenicdiseases;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class Info extends AppCompatActivity {
    TabLayout tab;
    ViewPager viewPager;
    TextView Itxt;
    TextView IntroHeading;
    TextView timerText;
    Timer timer;
    TimerTask timerTask;
    Double time=0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);

        ViewPagerInfoAdapter adapter = new ViewPagerInfoAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);

//        google=findViewById(R.id.Browse1);
        IntroHeading=findViewById(R.id.IntroHead);
        Itxt =findViewById(R.id.info2);

        timer=new Timer();
        timerTask=new TimerTask() {
            @Override
            public void run() {
//                getActivity().runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
                    time++;
                    com.example.monogenicdiseases.Timer timer=new com.example.monogenicdiseases.Timer(getTimerText());
                    FirebaseDatabase.getInstance().getReference("Users").child("DATA").child("Information").setValue(timer);
//                    timerText.setText(getTimerText());


//                    }
//                });
            }
        };
        timer.scheduleAtFixedRate(timerTask,0,1000);

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

    public void onBackPressed() {

//        Timer timer=new Timer(timerText.getText().toString());
//
//        FirebaseDatabase.getInstance().getReference("Users").child("DATA").setValue(timer).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()){
//                    Toast.makeText(Info.this,"Timer set",Toast.LENGTH_LONG).show();
//                }else {
//                    Toast.makeText(Info.this,"Timer not set",Toast.LENGTH_LONG).show();
//                }
//            }
//        });
        timer.cancel();
        super.onBackPressed();
        Intent i=new Intent(Info.this, Home.class);
        startActivity(i);
    }

}