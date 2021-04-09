package com.groupeleven.studentlife.logic;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.groupeleven.studentlife.domainSpecificObjects.PriorityChannel;

import java.util.Date;
import java.util.Random;

public class AlarmReceiver extends BroadcastReceiver {

    PriorityChannel myPriorityChannel;

    @Override
    public void onReceive(Context context, Intent intent) {
        myPriorityChannel = new PriorityChannel(context);
        createNotification(context, intent,
                intent.getStringExtra("TaskName"),
                intent.getStringExtra("Hint"));
    }

    public void createNotification(Context context, Intent intent, String title, String message){
        NotificationCompat.Builder bd = myPriorityChannel.getChannelNotification(title, message);

//have a random number for notification id so we can have multiple notification showed up
        int r = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        r += new Random().nextInt(100) + 1;

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(r, bd.build());
    }
}
