package com.example.corona;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DeviceBootReceiver extends BroadcastReceiver {
    private PendingIntent pendingIntent, pendingIntent1, pendingIntent2;

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent alarmIntent = new Intent(context, AlarmReciever.class);
        Intent alarmIntent1 = new Intent(context, warmwater.class);
        Intent alarmIntent2 = new Intent(context, fruits.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        pendingIntent1 = PendingIntent.getBroadcast(context,0,alarmIntent1,0);
        pendingIntent2 = PendingIntent.getBroadcast(context,0,alarmIntent2,0);


        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, 0, 1000 * 60 * 120, pendingIntent);

        AlarmManager manager1 = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager1.setInexactRepeating(AlarmManager.RTC_WAKEUP,0,1000*60*120,pendingIntent1);

        AlarmManager manager2 = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        manager2.setInexactRepeating(AlarmManager.RTC_WAKEUP,0,1000*60*360,pendingIntent2);
    }
}
