package com.example.spoon_full_o_sugar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class activity_add_to_sched extends AppCompatActivity implements View.OnClickListener {

    Button daily, spec_day, no_days;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_sched);

        // Log.i("activity_add_to_sched","ACTIVITY STARTED");

        daily = (Button) findViewById(R.id.sched_daily);
        spec_day = (Button) findViewById(R.id.sched_spec_day);
        no_days = (Button) findViewById(R.id.sched_no_days);

        daily.setOnClickListener(this);
        spec_day.setOnClickListener(this);
        no_days.setOnClickListener(this);

        // puts a back button in the action bar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public void onClick(View v) {
        Intent changeActivity;
        // switch case used to determine which page to point to, when button clicked
        switch(v.getId()) {
            case R.id.sched_daily:
                // link to daily meds schedule add
                changeActivity = new Intent(this,activity_sched_daily.class);
                // Log.i("activity_add_to_sched","LINK TO ADD DAILY MED PRESSED");
                startActivity(changeActivity);
                break;
            case R.id.sched_spec_day:
                // link to specific day schedule add
                changeActivity = new Intent(this,activity_sched_spec_days.class);
                // Log.i("activity_add_to_sched","LINK TO ADD SPEC DAY MED PRESSED");
                startActivity(changeActivity);
                break;
            case R.id.sched_no_days:
                // link to every number of days schedule add
                changeActivity = new Intent(this,activity_del_sched.class);
                // Log.i("activity_add_to_sched","LINK TO REMOVE FROM SCHED PRESSED");
                startActivity(changeActivity);
                break;
        }
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