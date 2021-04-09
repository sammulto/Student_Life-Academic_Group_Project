package com.groupeleven.studentlife.logic;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.groupeleven.studentlife.domainSpecificObjects.PriorityChannel;

public class AlarmReceiver extends BroadcastReceiver {

    PriorityChannel myPriorityChannel;

    @Override
    public void onReceive(Context context, Intent intent) {
        myPriorityChannel = new PriorityChannel(context);
        createNotification(context, intent,
                intent.getStringExtra("TaskName"),
                intent.getStringExtra("Hint"),
                intent.getStringExtra("Priority"));
    }

    public void createNotification(Context context, Intent intent, String title, String message, String priority){
        NotificationCompat.Builder bd = null;

        if(priority.equals("High")) {
            bd = myPriorityChannel.getChannel1Notification(title, message);
        }else if (priority.equals("Medium")){
            bd = myPriorityChannel.getChannel2Notification(title, message);
        }else if (priority.equals("Low")){
            bd = myPriorityChannel.getChannel3Notification(title, message);
        }

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        if(bd != null) {
            notificationManagerCompat.notify(7, bd.build());
        }
    }
}
