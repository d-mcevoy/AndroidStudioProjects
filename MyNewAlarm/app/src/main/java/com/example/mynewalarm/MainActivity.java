package com.example.mynewalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AlarmManager myAlarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

    }


    public void startSomething(View view) {
        Toast.makeText(this,"Alarm Started",Toast.LENGTH_SHORT).show();

        Intent i1 = new Intent();
        i1.setAction("com.example.mynewalarm.receiver");

        PendingIntent pd = PendingIntent.getBroadcast(this, 1, i1, PendingIntent.FLAG_IMMUTABLE);

        myAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(), 1000*5,pd);

    }
    public void stopSomething(View view) {
        Toast.makeText(this, "Alarm Stopped",Toast.LENGTH_SHORT).show();
        Intent i1 = new Intent();
        i1.setAction("com.example.mynewalarm.receiver");

        PendingIntent pd = PendingIntent.getBroadcast(this, 1, i1, PendingIntent.FLAG_IMMUTABLE);

        myAlarmManager.cancel(pd);
    }


}