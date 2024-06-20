package com.example.monogenicdiseases;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class Sequence extends AppCompatActivity {
    TabLayout tab;
    ViewPager viewPager;
    Timer timer;
    TimerTask timerTask;
    Double time=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
                FirebaseDatabase.getInstance().getReference("Users").child("DATA").child("Sequence").setValue(timer);
//                    timerText.setText(getTimerText());


//                    }
//                });
            }
        };
        timer.scheduleAtFixedRate(timerTask,0,1000);

        tab = findViewById(R.id.SeqTab);
        viewPager = findViewById(R.id.SeqPager);

        ViewPagerSequenceAdapter adapter = new ViewPagerSequenceAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
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
        timer.cancel();
        super.onBackPressed();

        Intent i=new Intent(Sequence.this, Home.class);
        startActivity(i);
    }
}