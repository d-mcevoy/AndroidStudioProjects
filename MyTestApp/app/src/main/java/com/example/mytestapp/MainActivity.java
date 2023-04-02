package com.example.mytestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // creates variable to link to objects on the screen
    TextView t1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // edits were made beneath this comment

        // links the created variable to the textView in the layout
        t1 = (TextView)findViewById(R.id.t1);
        b1 = (Button)findViewById(R.id.b1);

        // creates an event listener, 'this' means that it is happening inside this class only
        b1.setOnClickListener(this);
    }


    // this was created when 'implements...' was added to line 11
    // this is the event handler
    @Override
    public void onClick(View view) {
        // this implements on the click for b1
        t1.setText("You clicked Change 1");
    }

    public void doSomething(View v) {
        t1.setText("You clicked Change 2");
    }
}