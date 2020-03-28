package com.example.corona;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class fruits extends BroadcastReceiver {

    Context mcontext;
    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("here");
        mcontext = context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            makeNotificationChannel("CHANNEL_1", "Example channel", NotificationManager.IMPORTANCE_DEFAULT);
        }

        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(context, "CHANNEL_1");

        notification
                .setSmallIcon(R.mipmap.ic_launcher_round) // can use any other icon
                .setContentTitle("Hey!")
                .setContentText("This is a reminder to have some fresh citric fruits!");

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify(1, notification.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void makeNotificationChannel(String channel_1, String example_channel, int importanceDefault) {
        NotificationChannel channel = new NotificationChannel(channel_1, example_channel, importanceDefault);
        channel.setShowBadge(true);

        NotificationManager notificationManager = (NotificationManager)mcontext.getSystemService(NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.createNotificationChannel(channel);
    }
}