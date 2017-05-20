package com.example.huy.clock.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Viethuy_Iris on 4/30/2017.
 */

public class AlarmDataBase {
    SQLiteDatabase database;
    DBHelper dbHelper;
    public AlarmDataBase(Context context){
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();

    }
    public ArrayList<AlarmClock> getAll(){
      //  dbHelper.onUpgrade(database,1,2);
        ArrayList<AlarmClock> lstAlarm = new ArrayList<>();
        String query = "Select * From "+DBHelper.tableName;
        if(database.isOpen()){

        }

        Cursor cursor = database.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                AlarmClock alarmClock = new AlarmClock();
                alarmClock.setId(Integer.parseInt(cursor.getString(0)));
                alarmClock.setHour(cursor.getString(1));
                alarmClock.setMinute(cursor.getString(2));
                alarmClock.setDay(cursor.getString(3));
                alarmClock.setStatus(cursor.getString(4));
                alarmClock.setOnoff(Integer.parseInt(cursor.getString(5)));
                lstAlarm.add(alarmClock);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return lstAlarm;

    }
    public void AddAlarm(AlarmClock alarmClock){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.colhour,alarmClock.getHour());
        contentValues.put(DBHelper.colminute,alarmClock.getMinute());
        contentValues.put(DBHelper.colDay,alarmClock.getDay());
        contentValues.put(DBHelper.colstatus,alarmClock.getStatus());
        contentValues.put(DBHelper.colCheck,alarmClock.getOnoff());
        database.insert(DBHelper.tableName,null,contentValues);
    //    database.close();
    }
    public void reopen(){
        database.close();
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        database.close();
    }
    public void deletelast(AlarmClock alarmClock){
        database.delete(DBHelper.tableName, DBHelper.colID + " = "+alarmClock.getId(),null);
    //    database.close();
    }
    public long updateOnof(AlarmClock alarmClock){
        ContentValues values = new ContentValues();
        values.put(DBHelper.colCheck,alarmClock.getOnoff());
        return database.update(DBHelper.tableName,values,DBHelper.colID+" = "+alarmClock.getId(),null);
    }
}
