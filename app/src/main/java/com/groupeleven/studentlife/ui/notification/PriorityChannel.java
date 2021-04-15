package com.groupeleven.studentlife.ui.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.groupeleven.studentlife.R;

public class PriorityChannel extends ContextWrapper {
    public static final String CHANNEL_ID = "channel_med";

    private NotificationManager myManager;

    public PriorityChannel(Context base) {
        super(base);
        createNotificationChannels();
    }

//create 3 channels which have different priority
    private void createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, "Channel Med", NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("This is Medium priority Channel");

            getManager().createNotificationChannel(channel);
        }
    }

    public NotificationManager getManager(){
        if (myManager == null){
            myManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return myManager;
    }

    public NotificationCompat.Builder getChannelNotification(String title, String message){
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setAutoCancel(true);
    }
}
