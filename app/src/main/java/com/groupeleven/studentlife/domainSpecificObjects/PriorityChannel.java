package com.groupeleven.studentlife.domainSpecificObjects;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.groupeleven.studentlife.R;

public class PriorityChannel extends ContextWrapper {
    public static final String CHANNEL_HIGH_ID = "channel_high";
    public static final String CHANNEL_MED_ID = "channel_med";
    public static final String CHANNEL_LOW_ID = "channel_low";

    private NotificationManager myManager;

    public PriorityChannel(Context base) {
        super(base);
        createNotificationChannels();
    }


    private void createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_HIGH_ID, "Channel High", NotificationManager.IMPORTANCE_HIGH);

            channel1.setDescription("This is High priority Channel");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_MED_ID, "Channel Med", NotificationManager.IMPORTANCE_DEFAULT);

            channel2.setDescription("This is Medium priority Channel");

            NotificationChannel channel3 = new NotificationChannel(
                    CHANNEL_LOW_ID, "Channel Low", NotificationManager.IMPORTANCE_LOW);

            channel3.setDescription("This is Low priority Channel");

            getManager().createNotificationChannel(channel1);
            getManager().createNotificationChannel(channel2);
            getManager().createNotificationChannel(channel3);
        }
    }

    public NotificationManager getManager(){
        if (myManager == null){
            myManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return myManager;
    }

    public NotificationCompat.Builder getChannel1Notification(String title, String message){
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_HIGH_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setAutoCancel(true);
    }

    public NotificationCompat.Builder getChannel2Notification(String title, String message){
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_MED_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setAutoCancel(true);
    }

    public NotificationCompat.Builder getChannel3Notification(String title, String message){
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_LOW_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setAutoCancel(true);
    }
}
