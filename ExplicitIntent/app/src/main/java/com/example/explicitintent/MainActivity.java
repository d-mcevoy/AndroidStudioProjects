package com.example.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // create an editText object to refer to the edit text on Activity 1
    EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // point the created EditText object at the edittext field on Activity 1
        e1 = (EditText)findViewById(R.id.edit1);
    }

    public void doSomething(View view) {
        Intent i1 = new Intent(this, Second.class);
        // put data from editText field in the intent object 'user' is chosen by you
        i1.putExtra("user",e1.getText().toString());
        startActivity(i1);

    }
}