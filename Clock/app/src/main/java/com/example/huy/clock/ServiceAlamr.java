package com.example.huy.clock;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.app.Service;

import android.support.annotation.Nullable;

/**
 * Created by Viethuy_Iris on 5/4/2017.
 */

public class ServiceAlamr extends Service{
    AlarmReceiver alarmReceiver = new AlarmReceiver();
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public void onStart(Context context,Intent intent, int hour,int minute) {
        alarmReceiver.setHour(hour);
        alarmReceiver.setMinute(minute);
        alarmReceiver.SetAlarm(context);
    }
    public void onStop(Context context){
        alarmReceiver.CancelAlarm(context);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
