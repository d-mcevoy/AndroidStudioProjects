package com.example.spoon_full_o_sugar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // create instance of buttons, to refer to buttons on view
    Button b1, b2, b3, b4;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // link button variables to buttons on view
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b4 = (Button)findViewById(R.id.b4);

        // create listeners for the buttons, to know when they are clicked
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);

        db = new DatabaseHelper(this);

    }

    @Override
    public void onClick(View v) {
        Intent changeActivity;
        // switch case used to determine which page to point to, when button clicked
        switch(v.getId()) {
            case R.id.b1:
                // link to Medication Logging Page
                changeActivity = new Intent(this,activity_log_meds.class);
                // Log.i("Main Activity","LINK TO LOG MEDS PRESSED");
                startActivity(changeActivity);
                break;
            case R.id.b2:
                // link to Add to Schedule Page
                changeActivity = new Intent(this,activity_add_to_sched.class);
                // Log.i("Main Activity","LINK TO EDIT SCHED PRESSED");
                startActivity(changeActivity);
                break;
            case R.id.b3:
                // link to View Schedule Page
                changeActivity = new Intent(this,activity_view_sched.class);
                // Log.i("Main Activity","LINK TO VIEW TODAY PRESSED");
                startActivity(changeActivity);
                break;
            case R.id.b4:
                // link to View History page
                changeActivity = new Intent(this,activity_view_hist.class);
                // Log.i("Main Activity","LINK TO VIEW HIST PRESSED");
                startActivity(changeActivity);
                break;
        }
    }
}