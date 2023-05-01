package com.example.spoon_full_o_sugar;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyOwnPagerAdapter extends FragmentPagerAdapter {

    String data[] = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    public MyOwnPagerAdapter(FragmentManager fm) {super(fm);}

    @Override
    public Fragment getItem(int position) {

            if(position==0) {
                // Log.i("MyOwnPagerAdapter", "MON FRAG CHOSEN");
                return new fragment_mon();
            }
            if(position==1) {
                // Log.i("MyOwnPagerAdapter", "TUE FRAG CHOSEN");
                return new fragment_tues();
            }
            if(position==2) {
                // Log.i("MyOwnPagerAdapter", "WED FRAG CHOSEN");
                return new fragment_wed();
            }
            if(position==3) {
                // Log.i("MyOwnPagerAdapter", "THU FRAG CHOSEN");
                return new fragment_thur();
            }
            if(position==4) {
                // Log.i("MyOwnPagerAdapter", "FRI FRAG CHOSEN");
                return new fragment_fri();
            }
            if(position==5) {
                // Log.i("MyOwnPagerAdapter", "SAT FRAG CHOSEN");
                return new fragment_sat();
            }
            if(position==6) {
                // Log.i("MyOwnPagerAdapter", "SUN FRAG CHOSEN");
                return new fragment_sun();
            }

        return null;
    }

    @Override
    public int getCount() { return data.length; }

    @Override
    public CharSequence getPageTitle(int position) { return data[position]; };

}
