package com.example.huy.clock.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Viethuy_Iris on 5/1/2017.
 */

public class WorldClockDatabase {
    SQLiteDatabase database;
    DBHelper dbHelper;
    public WorldClockDatabase(Context context){
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }
    public void add(Clock clock){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.worldName,clock.getName());
        contentValues.put(DBHelper.worldTime,clock.getTime());
        contentValues.put(DBHelper.dolech,clock.getDolech());
        database.insert(DBHelper.tableWorld,null,contentValues);
    }
    public ArrayList<Clock> getAll(){
       // dbHelper.onUpgrade(database,1,2);
        ArrayList<Clock> lstWorld = new ArrayList<>();
        String query = "Select * From "+DBHelper.tableWorld;

        Cursor cursor = database.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                Clock clock = new Clock();
                clock.setId(Integer.parseInt(cursor.getString(0)));
                clock.setName(cursor.getString(1));
                clock.setTime(cursor.getString(2));
                clock.setDolech(Integer.parseInt(cursor.getString(3)));
                lstWorld.add(clock);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return lstWorld;

    }
    public void deletelast(Clock clock){
        database.delete(DBHelper.tableWorld, DBHelper.worldID + " = "+clock.getId(),null);
        //    database.close();
    }
}
