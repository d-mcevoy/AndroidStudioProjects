package com.example.mybroadcastreceiverdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callInBuilt(View view) {
        Toast.makeText(this, "Top Button Push", Toast.LENGTH_SHORT).show();
    }

    public void callCustom(View view) {
        Intent i1 = new Intent();
        i1.setAction("com.myMcevoy.own.MyReceiver.call");
        i1.addCategory("android.intent.category.DEFAULT");
        sendBroadcast(i1);
    }
}