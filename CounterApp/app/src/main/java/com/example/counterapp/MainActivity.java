package com.example.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity  {

    TextView t1;
    /*
    code to take input from textView
    String url=editText.getText().toString();
     */


    int count = 0;

    // connects UI to Java code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (TextView)findViewById(R.id.t1);

        if(savedInstanceState != null){
            String count = savedInstanceState.getString("count");
            if(t1 != null)
                t1.setText(count);
        }

    }

    // saves the count in the event the activity is destroyed, code added to onCreate() above too
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("count",String.valueOf(t1.getText()));
    }

    public void plusOne(View v){
        count++;
        t1.setText(Integer.toString(count));
    }

    // implicit intents
    public void visitWeb(View v){

        Uri uri;
        uri = Uri.parse("http:www.rte.ie");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);

    }

    public void call(View v){
        Uri uri = Uri.parse("tel:00353879777683");
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(it);
    }

    // explicit intent
    public void other(View v){
        Intent other = new Intent(this,otherActivity.class);
        startActivity(other);
    }

}