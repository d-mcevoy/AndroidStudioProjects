package com.example.spoon_full_o_sugar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.ActionBar;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class activity_view_sched extends AppCompatActivity {

    RecyclerView r1;
    List name, dose, time, DB_ID;
    RecycleAdapterSched adapt;
    DatabaseHelper DB;
    ActionBar actionBar;
    Calendar cal;
    Integer day, icon;
    Cursor sched;
    String timeString, am_pm, postMin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sched);

        // Log.i("activity_view_sched","ACTIVITY STARTED");

        // puts a back button in the action bar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DB = new DatabaseHelper(this);

        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_WEEK);
        // Log.i("VIEW_SCHEDULE:","Day of week: "+day);

        if(android.text.format.DateFormat.is24HourFormat(this)){am_pm="";}
        postMin = "0";

        icon = R.drawable.pill_icon;

        Log.i("VIEW_SCHED","Table requested: "+getTable(day));
        sched = DB.getSched(getTable(day));

        r1 = (RecyclerView) findViewById(R.id.sched_recycler);

        name = new ArrayList();
        dose = new ArrayList();
        time = new ArrayList();
        DB_ID = new ArrayList();


        while(sched.moveToNext()) {
            DB_ID.add(sched.getInt(0));
            /*
            Log.i("Populating Sched Lists","Name: "+ sched.getString(1));
            Log.i("Populating Sched Lists","Dose: "+ sched.getString(2));
            Log.i("Populating Sched Lists","Hour: "+ sched.getString(3));
            Log.i("Populating Sched Lists","Minute: "+ sched.getString(4));
            Log.i("Populating Sched Lists","AM/PM: "+ sched.getString(5));
             */
            if(sched.getInt(4)==0) {postMin = "0";} else {postMin = "";}
            name.add(sched.getString(1));
            dose.add(sched.getString(2));
            if(!android.text.format.DateFormat.is24HourFormat(this)) {
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
            else if(android.text.format.DateFormat.is24HourFormat(this)){
                if(sched.getInt(3)<10) {
                    timeString = "0" + sched.getInt(3) + ":" + sched.getInt(4)+postMin;
                }
                else {
                    timeString = sched.getInt(3) + ":" + sched.getInt(4)+postMin;
                }
            }
            time.add(timeString);


        }

        adapt = new RecycleAdapterSched(this, name, dose, time, icon, DB_ID);

        r1.setAdapter(adapt);
        r1.setLayoutManager(new LinearLayoutManager(this));

    }


    public String getTable(Integer d) {
        // cal = Calendar.getInstance();
        // day = cal.get(Calendar.DAY_OF_WEEK);
        switch (d) {
            case Calendar.MONDAY:
                // Log.i("getTable:", "MONDAY");
                return "med_sched_mon";
            case Calendar.TUESDAY:
                // Log.i("getTable:", "TUESDAY");
                return "med_sched_tues";
            case Calendar.WEDNESDAY:
                // Log.i("getTable:", "WEDNESDAY");
                return "med_sched_wed";
            case Calendar.THURSDAY:
                // Log.i("getTable:", "THURSDAY");
                return "med_sched_thur";
            case Calendar.FRIDAY:
                // Log.i("getTable:", "FRIDAY");
                return "med_sched_fri";
            case Calendar.SATURDAY:
                // Log.i("getTable:", "SATURDAY");
                return "med_sched_sat";
            case Calendar.SUNDAY:
                // Log.i("getTable:", "SUNDAY");
                return "med_sched_sun";
        }
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}