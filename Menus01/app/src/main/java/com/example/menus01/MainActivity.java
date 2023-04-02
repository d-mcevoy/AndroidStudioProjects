package com.example.menus01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // for use with context menus for the edit text views
    EditText e1, e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // link to the edit texts in the XML files
        e1 = (EditText)findViewById(R.id.edit1);
        e2 = (EditText)findViewById(R.id.edit2);

        // register both edit text views for a Context menu
        registerForContextMenu(e1);
        registerForContextMenu(e2);
    }

    // to show menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    // to interact with menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handling menu choices:
        switch (item.getItemId()) {
            case R.id.m1:
                // if moving to another activity, intent code goes here
                Toast.makeText(this,"You clicked Settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.m2:
                Toast.makeText(this,"You clicked Mic",Toast.LENGTH_SHORT).show();
                break;
            case R.id.m3:
                Toast.makeText(this,"You clicked Status",Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    // to display context menus from long pressing the edit text fields
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // switch to determine which item is being pressed
        switch (v.getId()) {
            case R.id.edit1:
                getMenuInflater().inflate(R.menu.edit1_menu, menu);
                break;
            case R.id.edit2:
                getMenuInflater().inflate(R.menu.edit2_menu, menu);
                break;
        }
    }

    // to handle selections from the context menus

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        // switch to handle the correct chosen item
        switch (item.getItemId()) {
            case R.id.id1:
                // toast used as an example
                Toast.makeText(this,"You clicked item 1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.id2:
                Toast.makeText(this,"You clicked Item 2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.s1:
                Toast.makeText(this,"You clicked One",Toast.LENGTH_SHORT).show();
                break;
            case R.id.s2:
                Toast.makeText(this,"You clicked Two",Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}