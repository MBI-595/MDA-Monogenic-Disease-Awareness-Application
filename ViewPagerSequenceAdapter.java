package com.example.monogenicdiseases;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerSequenceAdapter extends FragmentPagerAdapter {
    public ViewPagerSequenceAdapter(@NonNull FragmentManager fm){

        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SequenceFragment();
        } else {
            return new SeqAnalysisFragment();
        }
    }


    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position){
        if(position==0){
            return "SEQUENCE";
        }else{
            return "ANALYSIS";
        }
    }
}
