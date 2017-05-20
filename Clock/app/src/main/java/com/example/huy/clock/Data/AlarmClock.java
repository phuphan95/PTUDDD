package com.example.huy.clock.Data;

/**
 * Created by Viethuy_Iris on 4/28/2017.
 */

public class AlarmClock {
    private int id;
    private String hour;
    private String minute;
    private String status; // am or pm
    private String day; // monday,.......
    private int onoff;

    public AlarmClock(int id,String hour, String minute, String status, String day, int onoff) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.status = status;
        this.day = day;
        this.onoff = onoff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AlarmClock() {
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getOnoff() {
        return onoff;
    }

    public void setOnoff(int onoff) {
        this.onoff = onoff;
    }
}
