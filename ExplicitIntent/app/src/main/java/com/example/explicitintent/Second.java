package com.example.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Second extends AppCompatActivity {

    // create TextView object to point at the text view on 2nd activity
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // point the created TextView object at the textview on 2nd activity
        txt1 = (TextView)findViewById(R.id.result);

        // get the intent and extras from the activity that started this activity
        Bundle b1 = getIntent().getExtras();
        // get the string from b1
        String s1 = b1.getString("user");
        // put the string value into the text view on 2nd activity
        txt1.setText(s1);
    }
}