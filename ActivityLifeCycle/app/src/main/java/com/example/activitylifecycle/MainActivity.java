package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // creating a toast (small pop-up message)
        // three arguements:
        // context
        // text for display
        // duration; long: 3sec, short 1sec
        Toast.makeText(this,"onCreate Finished",Toast.LENGTH_SHORT).show();
        // log in studio
        Log.i("MainActivity","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"onStart Finished",Toast.LENGTH_SHORT).show();
        Log.i("MainActivity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"onResume Finished",Toast.LENGTH_SHORT).show();
        Log.i("MainActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"onPause Finished",Toast.LENGTH_SHORT).show();
        Log.i("MainActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"onStop Finished",Toast.LENGTH_SHORT).show();
        Log.i("MainActivity","onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"onRestart Finished",Toast.LENGTH_SHORT).show();
        Log.i("MainActivity","onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"onDestroy Finished",Toast.LENGTH_SHORT).show();
        Log.i("MainActivity","onDestroy");
    }
}