package com.example.spoon_full_o_sugar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class activity_view_hist extends AppCompatActivity {

    // create a RecyclerView instance for use in displaying data
    RecyclerView r1;
    // Creates lists to store the data from the DB
    List name, dose, date, time;
    // records the icon's value to send to recyclerAdapter
    int icon = R.drawable.pill_icon;
    // creates instance of RecycleAdapter
    RecycleAdapter adapt;
    // creates instance of DatabaseHelper
    DatabaseHelper DB;
    // Get access to the ActionBar
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hist);

        // Log.i("activity_view_hist","ACTIVITY STARTED");

        // puts a back button in the action bar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DB = new DatabaseHelper(this);

        Log.i("VIEW HIST","Request made to DatabaseHelper.getHist()");
        Cursor hist = DB.getHist();

        r1 = (RecyclerView) findViewById(R.id.hist_recycler);

        name = new ArrayList();
        dose = new ArrayList();
        date = new ArrayList();
        time = new ArrayList();

        while(hist.moveToNext()){
            date.add(hist.getString(1));
            time.add(hist.getString(2));
            name.add(hist.getString(3));
            dose.add(hist.getString(4));
        }

        adapt = new RecycleAdapter(this,date, time, name, dose, icon);

        r1.setAdapter(adapt);
        r1.setLayoutManager(new LinearLayoutManager(this));

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