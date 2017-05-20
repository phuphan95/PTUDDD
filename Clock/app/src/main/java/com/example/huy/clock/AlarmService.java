package com.example.huy.clock;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Viethuy_Iris on 5/3/2017.
 */

public class AlarmService extends IntentService {
    private NotificationManager alarmNotificationManager;

    public AlarmService() {
        super("Alarm Service");

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
            sendNotification("Wake up ,Wake up");
    }
    private void sendNotification(String msh){
        alarmNotificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class),0);
        NotificationCompat.Builder alarmNotificationBuilder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("Alarm")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(msh))
                        .setContentText(msh);
        alarmNotificationBuilder.setContentIntent(contentIntent);
        Notification notification = alarmNotificationBuilder.build();
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        alarmNotificationManager.notify(1,notification);
        Log.d("Alarm Service", "Notification send: ");
    }
}
