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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class activity_log_meds extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    // assign variable to link to inputs from layout
    EditText dose, date, time;
    AutoCompleteTextView name;
    List namesList;

    // for displaying and saving the time and date
    String timeString, dateString;
    Integer DBday, DBmonth, DByear, DBhour, DBminute;

    // creates an instance of the DatabaseHelper to log data to the DB
    DatabaseHelper db;
    // gets access to the Action Bar
    ActionBar actionBar;
    Cursor names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_meds);

        // Log.i("activity_log_meds","ACTIVITY STARTED");

        db = new DatabaseHelper(this);

        // puts a back button in the action bar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // link variables to inputs on layout
        name = (AutoCompleteTextView) findViewById(R.id.med_name);
        dose = (EditText) findViewById(R.id.med_quant);
        date = (EditText) findViewById(R.id.date_taken);
        time = (EditText) findViewById(R.id.time_taken);

        // this section is intended to display an suggested input list for the drug names
        names = db.getNames();
        namesList = new ArrayList();
        while(names.moveToNext()) {
            if(!namesList.contains(names.getString(1))){
                namesList.add(names.getString(1));
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, namesList);
        // this line can limit the number of suggestions appearing
        // name.setThreshold(NUMBER);
        name.setAdapter(adapter);




        // create listeners to display the date and time pickers

       date.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DialogFragment datePicker = new DatePickerFragment();
               datePicker.show(getSupportFragmentManager(),"Date Picker");
           }
       });
       time.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DialogFragment timePicker = new TimePickerFragment();
               timePicker.show(getSupportFragmentManager(),"Time Picker");
           }
       });




    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // gets the date chosen from the fragment and sets it in the EditText
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        dateString = DateFormat.getDateInstance().format(cal.getTime());
        date.setText(dateString);
        DBday = dayOfMonth;
        DBmonth = month;
        DByear = year;
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
        DBhour = hourOfDay;
        DBminute = minute;

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
            time.setText(timeString);
        }
        else{
            timeString = leading_zero+hourOfDay + ":" + minute + " " + am_pm;
            time.setText(timeString);
        }
    }

    public void saveData(View view) {
        Log.i("Hist save data:",""+name.getText().toString()+", "+dose.getText().toString()+", "+dateString+", "+timeString);
        db.insertHist(dateString, timeString, name.getText().toString(), dose.getText().toString(), 0, DBday, DBmonth, DByear, DBhour, DBminute);
        name.setText("");
        dose.setText("");
        date.setText("");
        time.setText("");
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

    // sharedPreferences created to remember the form details if the user navigates away from the page before submitting the form.
    @Override
    protected void onPause() {
        super.onPause();
        // Toast.makeText(this,"onPause() called", Toast.LENGTH_SHORT).show();
        SharedPreferences sh = getSharedPreferences("medLogShared",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh.edit();
        myEdit.putString("name", name.getText().toString());
        myEdit.putString("dose", dose.getText().toString());
        myEdit.putString("date", dateString);
        myEdit.putString("time", timeString);
        myEdit.commit();

        /*
        Log.i("onPause","Name: "+ name.getText().toString());
        Log.i("onPause","Dose: "+ dose.getText().toString());
        Log.i("onPause","date: "+ dateString);
        Log.i("onPause","time: "+ timeString);
        */
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Toast.makeText(this, "onResume() called", Toast.LENGTH_SHORT).show();
        SharedPreferences sh2 = getSharedPreferences("medLogShared", MODE_APPEND);
        String resumeName = sh2.getString("name","");
        String resumeDose = sh2.getString("dose","");
        String resumeDate = sh2.getString("date", "");
        String resumeTime = sh2.getString("time","");

        /*
        Log.i("onResume","Name: "+ resumeName);
        Log.i("onResume","Dose: "+ resumeDose);
        Log.i("onResume","date: "+ resumeDate);
        Log.i("onResume","time: "+ resumeTime);
         */

        name.setText(resumeName);
        dose.setText(resumeDose);
        // date.setText(resumeDate);
        // time.setText(resumeTime);
    }
}