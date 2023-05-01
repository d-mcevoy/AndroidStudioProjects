package com.example.spoon_full_o_sugar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class DayChangeReceiver extends BroadcastReceiver {

    DatabaseHelper DB;
    String update;
    Integer day;
    Calendar cal;


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Receiver", "onReceive()");
        Toast.makeText(context,"BroadCastReceiver: Day Changed", Toast.LENGTH_SHORT).show();
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_WEEK);
        update = intent.getAction();
        if(update != null && update.equals(Intent.ACTION_DATE_CHANGED)) {
            DB.getDaily(getTable(day));
        }
    }

    public String getTable(Integer d) {
        // Log.i("Broadcast Receiver","Inside getTable");
        switch (d) {
            case 2:
                // Log.i("getTable:", "MONDAY");
                return "med_sched_mon";
            case 3:
                // Log.i("getTable:", "TUESDAY");
                return "med_sched_tues";
            case 4:
                // Log.i("getTable:", "WEDNESDAY");
                return "med_sched_wed";
            case 5:
                // Log.i("getTable:", "THURSDAY");
                return "med_sched_thur";
            case 6:
                // Log.i("getTable:", "FRIDAY");
                return "med_sched_fri";
            case 7:
                // Log.i("getTable:", "SATURDAY");
                return "med_sched_sat";
            case 1:
                // Log.i("getTable:", "SUNDAY");
                return "med_sched_sun";
        }
        return null;
    }

}
