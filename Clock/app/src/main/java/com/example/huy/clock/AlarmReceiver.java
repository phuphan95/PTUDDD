package com.example.huy.clock;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Viethuy_Iris on 4/30/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    public int hour;
    public int minute;
    Calendar c = Calendar.getInstance();
    Ringtone ringtone;
    public AlarmReceiver(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public AlarmReceiver() {
        this.hour = c.get(Calendar.HOUR_OF_DAY);
        this.minute = c.get(Calendar.MINUTE);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        // Put here YOUR code.
        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show(); // For example



        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ringtone.stop();
                CancelAlarm(context);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,60000);



        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Alarm active")
                .setContentText("This is my alrm")
                .setDefaults(Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE)
                .setContentInfo("info")
                .setOngoing(true);
        Intent intent1 = new Intent(context.getApplicationContext(),AlarmActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent1, 0);
        builder.setContentIntent(pi);
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());


        wl.release();
    }
    public void SetAlarm(Context context)
    {
        Calendar c = Calendar.getInstance();
        int currentHour = c.get(Calendar.HOUR_OF_DAY);
        int currentMinute = c.get(Calendar.MINUTE);
        int currentSecond = c.get(Calendar.SECOND);
//        c.set(Calendar.HOUR_OF_DAY,hour);
//        c.set(Calendar.MINUTE,minute);
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        int time=0;
        int realHour =0 ;
        int realMinute = 0;
        if(hour >  currentHour){
            realHour = hour - currentHour;
        }
        else{
            if(hour == currentHour && minute >= currentMinute){
           //     Toast.makeText(context,"thanh cong",Toast.LENGTH_SHORT).show();
                realHour = 0;
            }
            else{
            //    Toast.makeText(context,"k thanh cong",Toast.LENGTH_SHORT).show();
            realHour = 24 - (currentHour - hour);
            }
        }

        if(minute >= currentMinute){
            realMinute = minute - currentMinute;
        }
        else{
            realMinute = 60 - (currentMinute - minute);
            realHour = realHour - 1;
        }
        time = realHour*60*60 + realMinute*60-currentSecond;
//        Toast.makeText(context,time+"",Toast.LENGTH_LONG).show();
        //c.get(Calendar.HOUR_OF_DAY)

        am.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+time*1000, 1000 * 10, pi); // Millisec * Second * Minute

    }
    public void CancelAlarm(Context context)
    {
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        if(ringtone!=null) {
            ringtone.stop();
        }
        alarmManager.cancel(sender);
    }
}
