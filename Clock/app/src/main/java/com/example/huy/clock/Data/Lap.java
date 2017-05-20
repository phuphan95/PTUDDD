package com.example.huy.clock.Data;

/**
 * Created by Phupc on 5/3/2017.
 */

public class Lap {
    private int id;
    private String time;
    private String timeSpace;

    public String getTimeSpace() {
        return timeSpace;
    }

    public void setTimeSpace(String timeSpace) {
        this.timeSpace = timeSpace;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return "Lap "+String.valueOf(id)+":";
    }

    public void setId(int id) {
        this.id = id;
    }


    public String toString(){
        return getId()+"\t\t\t\t"+getTime()+"      +"+getTimeSpace();
    }

}
