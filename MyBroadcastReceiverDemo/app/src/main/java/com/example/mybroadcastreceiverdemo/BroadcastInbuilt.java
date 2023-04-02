package com.example.mybroadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastInbuilt extends BroadcastReceiver {
    public BroadcastInbuilt() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Planey Plane Mode", Toast.LENGTH_SHORT).show();
    }
}