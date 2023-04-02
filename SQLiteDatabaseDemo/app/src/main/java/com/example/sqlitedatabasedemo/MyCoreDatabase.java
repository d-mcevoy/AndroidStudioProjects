package com.example.sqlitedatabasedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyCoreDatabase extends SQLiteOpenHelper {

    static final private String DB_NAME = "Education";
    static final private String DB_TABLE = "students";
    static final private int DB_VER = 1;

    Context ctx;
    SQLiteDatabase myDb;

    public MyCoreDatabase(Context ct) {
        super(ct,DB_NAME,null,DB_VER);
        ctx = ct;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create the database
        db.execSQL("CREATE TABLE "+DB_TABLE+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, stu_name TEXT, college_name TEXT);");
        Log.i("Database","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE+";");
        onCreate(db);

    }

    public void insertData(String s1, String s2) {
        // the arguments passed are not necessary if a contentvalues object is being passed instead
        myDb = getWritableDatabase();

        myDb.execSQL("INSERT INTO "+DB_TABLE+" (stu_name,college_name) VALUES('"+s1+"','"+s2+"');");
        Toast.makeText(ctx, "Data saved Successfully", Toast.LENGTH_SHORT).show();
    }

    public void getAll(){
        myDb = getReadableDatabase();
        // 2nd argument below is for WHERE clauses arguments, these would have been passed to the method
        Cursor cr = myDb.rawQuery("SELECT * FROM "+DB_TABLE, null);
        StringBuilder str = new StringBuilder();

        while(cr.moveToNext()){
            // numbers below are column indexes
            String s1 = cr.getString(1);
            String s2 = cr.getString(2);
            str.append(s1+"     "+s2+"\n");
            Toast.makeText(ctx, str.toString(),Toast.LENGTH_LONG).show();
        }
    }

}
