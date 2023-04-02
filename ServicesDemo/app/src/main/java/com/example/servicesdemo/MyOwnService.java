package com.example.servicesdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyOwnService extends Service {
    public MyOwnService() {
    }

    @Override
    public void onCreate() {
        // used to demonstrate that service will only be created once  despite multiple requests to start
        super.onCreate();
        Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show();
        Log.i("MyService", "Service has been created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // code to run goes here
        // toast used to demonstrate that it would work
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        Log.i("MyService", "Service has started");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
        Log.i("MyService", "Service has stopped");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}