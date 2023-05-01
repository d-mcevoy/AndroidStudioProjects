package com.example.spoon_full_o_sugar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class activity_sched_daily extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    EditText med_dose;
    AutoCompleteTextView med_name;
    List namesList;
    TextView time_pick;
    // Button save_button;
    String timeString;
    DatabaseHelper db;
    ActionBar actionBar;
    Integer hour, min, AMPM;
    String[] dayDB = {"med_sched_mon", "med_sched_tues", "med_sched_wed", "med_sched_thur", "med_sched_fri", "med_sched_sat", "med_sched_sun"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sched_daily);

        Log.i("activity_sched_daily","ACTIVITY STARTED");

        med_name = (AutoCompleteTextView) findViewById(R.id.daily_med_name);
        med_dose = (EditText) findViewById(R.id.daily_med_dose);
        time_pick = (TextView) findViewById(R.id.daily_time_pick);
        // save_button = (Button) findViewById(R.id.daily_save_button);

        // puts a back button in the action bar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);

        // this section is intended to display an suggested input list for the drug names
        Cursor names = db.getNames();
        namesList = new ArrayList();
        while(names.moveToNext()) {
            if(!namesList.contains(names.getString(1))){
                namesList.add(names.getString(1));
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, namesList);
        // this line can limit the number of suggestions appearing
        // med_name.setThreshold(NUMBER);
        med_name.setAdapter(adapter);

        time_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"Time Picker");
            }
        });
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // gets the time chosen from the fragment and sets it in the EditText

        // String am_pm needed for precise display of time, dependant on system time settings
        String am_pm = "";
        String leading_zero = "";
        Calendar setTime = Calendar.getInstance();
        setTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        setTime.set(Calendar.MINUTE, minute);
        hour = hourOfDay;
        min = minute;
        AMPM = setTime.get(Calendar.AM_PM);


        if (android.text.format.DateFormat.is24HourFormat(view.getContext())) {
            am_pm = "";
        }
        else{
            if (setTime.get(Calendar.AM_PM) == Calendar.AM)
                am_pm = "AM";
            else if (setTime.get(Calendar.AM_PM) == Calendar.PM)
                am_pm = "PM";
        }
        // this deals with displaying 24hour time with a leading zero before 10AM
        Boolean ampm = am_pm =="";
        Boolean b410 = (setTime.get(Calendar.HOUR_OF_DAY))<10;
        if(ampm && b410){
            leading_zero = "0";
        }

        if(setTime.get(Calendar.MINUTE)<10) {
            timeString = leading_zero+hourOfDay + ":0" + minute + " " + am_pm;
            time_pick.setText(timeString);
        }
        else{
            timeString = leading_zero+hourOfDay + ":" + minute + " " + am_pm;
            time_pick.setText(timeString);
        }
    }

    public void saveData(View view) {
        for(String table : dayDB) {
            Log.i("ACTIVITY_SCHED_DAILY:","Sent: "+med_name.getText()+", "+med_dose.getText().toString()+", "+hour+", "+min+", "+AMPM+", "+table);
            db.insertSched(med_name.getText().toString(), med_dose.getText().toString(), hour, min, AMPM, table);
        }
        med_name.setText("");
        med_dose.setText("");
        time_pick.setText(R.string.time_instruction);
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

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sh = getSharedPreferences("medLogShared",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh.edit();
        myEdit.putString("name", med_name.getText().toString());
        myEdit.putString("dose", med_dose.getText().toString());
        myEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh2 = getSharedPreferences("medLogShared", MODE_APPEND);
        String resumeName = sh2.getString("name,","");
        String resumeDose = sh2.getString("does", "");

        // code to set text in EditTextFields
        med_name.setText(resumeName);
        med_dose.setText(resumeDose);


    }
}