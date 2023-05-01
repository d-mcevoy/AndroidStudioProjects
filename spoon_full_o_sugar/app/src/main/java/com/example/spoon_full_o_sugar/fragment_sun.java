package com.example.spoon_full_o_sugar;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class fragment_sun extends Fragment {

    RecyclerView r1;
    RecycleAdapterDelete adapt;
    DatabaseHelper DB;
    ActionBar actionBar;
    Calendar cal;

    List name, dose, time, DB_ID;

    Integer day, icon;
    Cursor sched;
    String timeString, am_pm, postMin;


    public fragment_sun() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mon, container, false);

        r1 = (RecyclerView) view.findViewById(R.id.mon_recycler);
        DB = new DatabaseHelper(view.getContext());
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_WEEK);

        icon = R.drawable.pill_icon;

        Log.i("SUN FRAG","getSched(med_sched_sun)");
        sched = DB.getSched("med_sched_sun");

        name = new ArrayList();
        dose = new ArrayList();
        time = new ArrayList();
        DB_ID = new ArrayList();

        while(sched.moveToNext()) {
            DB_ID.add(sched.getInt(0));

            if(sched.getInt(4)==0) {postMin = "0";} else {postMin = "";}
            name.add(sched.getString(1));
            dose.add(sched.getString(2));
            if(!android.text.format.DateFormat.is24HourFormat(container.getContext())) {
                if (sched.getInt(3)<12){am_pm=" AM";} else {am_pm = " PM";}
                if (sched.getInt(5) == Calendar.PM) {
                    if (sched.getInt(3) < 12) {
                        timeString = (12 + sched.getInt(3)) + ":" + sched.getInt(4)+postMin+ am_pm;
                    } else {
                        timeString = sched.getInt(3) + ":" + sched.getInt(4)+postMin+ am_pm;
                    }
                } else if (sched.getInt(5) == Calendar.AM) {
                    if (sched.getInt(3) < 10) {
                        timeString = sched.getInt(3) + ":" + sched.getInt(4)+postMin+am_pm;
                    } else {
                        timeString = sched.getInt(3) + ":" + sched.getInt(4)+postMin+am_pm;
                    }
                }
            }
            else if(android.text.format.DateFormat.is24HourFormat(container.getContext())){
                if(sched.getInt(3)<10) {
                    timeString = "0" + sched.getInt(3) + ":" + sched.getInt(4)+postMin;
                }
                else {
                    timeString = sched.getInt(3) + ":" + sched.getInt(4)+postMin;
                }
            }
            time.add(timeString);

        }

        adapt = new RecycleAdapterDelete(container.getContext(), name, dose, time, icon, DB_ID, "sun", "fragment_sun");

        r1.setAdapter(adapt);
        r1.setLayoutManager(new LinearLayoutManager(container.getContext()));


        return view;
    }
}