package com.example.huy.clock.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Viethuy_Iris on 4/28/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String dataBase = "Ass";
    public static final String tableName = "AlarmClock";
    public static final String colID = "IDAlarm";
    public static final String colhour = "HourAlarm";
    public static final String colminute = "MinuteAlarm";
    public static final String colstatus = "StatusAlarm";
    public static final String colDay = "DayAlarm";
    public static final String colCheck = "onoff";

    public static final String tableWorld = "WorldClock";
    public static final String worldID = "IDWorld";
    public static final String worldName = "NameWorld";
    public static final String worldTime = "TimeWorld";
    public static final String dolech = "DoLech";

    private static final String createTableWorld = " CREATE TABLE "+tableWorld+"("+worldID+" INTEGER primary key autoincrement,"+worldName+" TEXT,"+worldTime+" TEXT,"+dolech+" INTEGER"+")";
    private static final String createTableAlarm =" CREATE TABLE "+tableName+"("+colID+" INTEGER primary key autoincrement,"+colhour+" TEXT,"+colminute+" TEXT,"+colstatus+" TEXT,"+colDay+" TEXT,"+colCheck+" INTEGER"+")";
    public DBHelper(Context context){
        super(context,dataBase,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("DROP TABLE IF EXISTS " + tableName);
        db.execSQL(createTableWorld);
        db.execSQL(createTableAlarm);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        db.execSQL("DROP TABLE IF EXISTS " + tableWorld);

        // Và tạo lại.
        onCreate(db);

    }

}
