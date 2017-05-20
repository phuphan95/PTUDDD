package com.example.huy.clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Viethuy_Iris on 5/4/2017.
 */

public class AutoStart extends BroadcastReceiver {

    AlarmReceiver alarmReceiver = new AlarmReceiver();
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            alarmReceiver.SetAlarm(context);
        }
    }
}
