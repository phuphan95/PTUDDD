package com.example.huy.clock.Data;

import java.sql.Time;

/**
 * Created by Viethuy_Iris on 4/26/2017.
 */

public class Clock {
    private String name;
    private String time;
    private int dolech;
    private int id;

    public Clock(String name, String time, int dolech, int id) {
        this.name = name;
        this.time = time;
        this.dolech = dolech;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clock() {
    }

    public int getDolech() {
        return dolech;
    }

    public void setDolech(int dolech) {
        this.dolech = dolech;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
