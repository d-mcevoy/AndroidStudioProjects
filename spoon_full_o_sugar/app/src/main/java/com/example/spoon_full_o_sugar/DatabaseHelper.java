package com.example.spoon_full_o_sugar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    // create variables for reference, which contain db name, table names, and version no.
    static final private String DB_NAME = "med_track";
    static final private String DB_TABLE_HIST = "med_hist";
    static final private String DB_TABLE_SCHED_MON = "med_sched_mon";
    static final private String DB_TABLE_SCHED_TUES = "med_sched_tues";
    static final private String DB_TABLE_SCHED_WED = "med_sched_wed";
    static final private String DB_TABLE_SCHED_THUR = "med_sched_thur";
    static final private String DB_TABLE_SCHED_FRI = "med_sched_fri";
    static final private String DB_TABLE_SCHED_SAT = "med_sched_sat";
    static final private String DB_TABLE_SCHED_SUN = "med_sched_sun";
    static final private String DB_TABLE_SCHED_DAILY = "med_sched_daily";
    static final private String DB_TABLE_DRUG_NAMES = "med_names";
    static final private int DB_VER = 1;

    Context ctx;
    SQLiteDatabase DB;

    public DatabaseHelper(Context ct) {
        super(ct,DB_NAME,null,DB_VER);
        ctx = ct;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // to create tables on creation of the database
        db.execSQL("CREATE TABLE "+DB_TABLE_HIST+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, date_taken TEXT, time_taken TEXT, med_name TEXT, quant_taken TEXT, scheduled INTEGER, day INTEGER, month INTEGER, year INTEGER, hour, INTEGER, minute INTEGER);");
        db.execSQL("CREATE TABLE "+DB_TABLE_SCHED_MON+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, med_name TEXT, med_dose Text, med_hour INTEGER, med_minute INTEGER, med_ampm INTEGER);");
        db.execSQL("CREATE TABLE "+DB_TABLE_SCHED_TUES+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, med_name TEXT, med_dose Text, med_hour INTEGER, med_minute INTEGER, med_ampm INTEGER);");
        db.execSQL("CREATE TABLE "+DB_TABLE_SCHED_WED+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, med_name TEXT, med_dose Text, med_hour INTEGER, med_minute INTEGER, med_ampm INTEGER);");
        db.execSQL("CREATE TABLE "+DB_TABLE_SCHED_THUR+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, med_name TEXT, med_dose Text, med_hour INTEGER, med_minute INTEGER, med_ampm INTEGER);");
        db.execSQL("CREATE TABLE "+DB_TABLE_SCHED_FRI+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, med_name TEXT, med_dose Text, med_hour INTEGER, med_minute INTEGER, med_ampm INTEGER);");
        db.execSQL("CREATE TABLE "+DB_TABLE_SCHED_SAT+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, med_name TEXT, med_dose Text, med_hour INTEGER, med_minute INTEGER, med_ampm INTEGER);");
        db.execSQL("CREATE TABLE "+DB_TABLE_SCHED_SUN+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, med_name TEXT, med_dose Text, med_hour INTEGER, med_minute INTEGER, med_ampm INTEGER);");
        db.execSQL("CREATE TABLE "+DB_TABLE_SCHED_DAILY+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, med_name TEXT, med_dose Text, med_hour INTEGER, med_minute INTEGER, med_ampm INTEGER);");
        db.execSQL("CREATE TABLE "+DB_TABLE_DRUG_NAMES+"(_id INTEGER PRIMARY KEY AUTOINCREMENT, med_name TEXT);");
        // logs creation to console for testing confirmation
        Log.i("DATABASE","TABLES CREATED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_HIST);
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_SCHED_MON);
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_SCHED_TUES);
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_SCHED_WED);
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_SCHED_THUR);
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_SCHED_FRI);
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_SCHED_SAT);
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_SCHED_SUN);
        onCreate(db);
    }

    public void insertHist(String date, String time, String name, String dose, int sched, int day, int month, int year, int hour, int minute) {

        Log.i("insertHist:",""+name+", "+dose+", "+date+", "+time);

        DB = getWritableDatabase();

        DB.execSQL("INSERT INTO med_hist (date_taken, time_taken, med_name, quant_taken, scheduled, day, month, year, hour, minute) VALUES('"+date+"','"+time+"','"+name+"','"+dose+"','"+sched+"','"+day+"','"+month+"','"+year+"','"+hour+"','"+minute+"');");
        DB.execSQL("INSERT INTO med_names (med_name) VALUES('"+name+"');");
        // Log.i("DATABASE_HELPER","Table updated: med_hist");
        // Log.i("DATABASE_HELPER:", "Table updated: med_names");
    }

    public Cursor getHist() {
        Log.i("DatabaseHelper","REQUEST RECEIVED");
        DB = getReadableDatabase();
        Cursor cr = DB.rawQuery("Select * FROM med_hist ORDER BY year, month, day, hour, minute", null);
        return cr;
    }

    public Cursor getNames() {
        DB = getReadableDatabase();
        Cursor cr = DB.rawQuery("Select * FROM med_names", null);
        return cr;
    }

    public void insertSched(String name, String dose, Integer hour, Integer minute, Integer ampm, String table) {

        Log.i("insertSched", "Received: "+name+", "+dose+", "+hour+", "+minute+", "+ampm+", "+table);

        DB = getWritableDatabase();

        DB.execSQL("INSERT INTO "+table+" (med_name, med_dose, med_hour, med_minute, med_ampm) VALUES('"+name+"','"+dose+"','"+hour+"','"+minute+"','"+ampm+"');");
        DB.execSQL("INSERT INTO med_names (med_name) VALUES('"+name+"');");
        // Log.i("DATABASE_HELPER","Table updated: "+table);
        // Log.i("DATABASE_HELPER:", "Table updated: med_names");
    }

    public void removeFromSched(int id, String table) {
        Log.i("DB HELPER, removeFromSched()","METHOD CALLED table: "+ table+" ID: "+id);
        DB = getWritableDatabase();

        DB.execSQL("DELETE FROM "+table+" WHERE _id = "+id+";");
    }

    public Cursor getSched(String table){

        Log.i("DB HELPER","getSched() METHOD CALLED. TABLE: "+ table);
        /*
        DB = getWritableDatabase();
        DB.execSQL("DELETE FROM med_sched_daily;");
        DB.execSQL("INSERT INTO med_sched_daily SELECT * FROM "+table+";");
        */
        DB = getReadableDatabase();
        Cursor cr = DB.rawQuery("Select * FROM "+table+" ORDER BY med_hour, med_minute", null);
        return cr;
    }

    public Cursor getDaily(String table) {
        Log.i("DB Helper","getDaily()");
        DB = getWritableDatabase();
        DB.execSQL("DELETE FROM med_sched_daily;");
        DB.execSQL("INSERT INTO med_sched_daily SELECT * FROM "+table+";");

        DB = getReadableDatabase();
        Cursor cr = DB.rawQuery("Select * FROM med_sched_daily ORDER BY med_hour, med_minute", null);
        return cr;
    }

}
