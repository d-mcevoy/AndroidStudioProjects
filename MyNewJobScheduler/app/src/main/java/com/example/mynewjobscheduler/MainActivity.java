package com.example.mynewjobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    ToggleButton tg;
    JobScheduler myJob;
    JobInfo myJobInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tg = (ToggleButton) findViewById(R.id.myjobbutton);

        tg.setOnCheckedChangeListener(this);

        myJob = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ComponentName componentName = new ComponentName(this, MyNewJob.class);
        if(isChecked) {
            myJobInfo = new JobInfo.Builder(1, componentName)
                    .setRequiresCharging(true)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .setRequiresDeviceIdle(false)
                    .build();

            myJob.schedule(myJobInfo);

        }
        else {
            myJob.cancelAll();
        }

    }
}