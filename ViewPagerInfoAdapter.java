package com.example.monogenicdiseases;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerInfoAdapter extends FragmentPagerAdapter {


    public ViewPagerInfoAdapter(@NonNull FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new IntroFragment();
        } else if (position == 1) {
            return new SymptomsFragment();
        } else if (position == 2) {
            return new TestsFragment();
        } else {
            return new MapsFragment2();
        }
    }


    @Override
    public int getCount() {
        return 4;
    }


    @Override
    public CharSequence getPageTitle(int position){
        if(position==0){
            return "INTRO";
        }else if(position==1){
            return "SYMPTOMS";
        }else if(position==2){
            return "TESTS";
        }else {
            return "PROVIDERS";
        }
    }
}

