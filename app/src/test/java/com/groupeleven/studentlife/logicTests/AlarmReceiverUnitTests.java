package com.groupeleven.studentlife.logicTests;

import android.content.Intent;
import android.content.IntentFilter;



import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.test.platform.app.InstrumentationRegistry;

import com.groupeleven.studentlife.logic.AlarmReceiver;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class AlarmReceiverUnitTests {

    private AlarmReceiver myAlarmReceiver;


    //test if onReceive called properly
    @Test
    public void test_receiver_call() throws InterruptedException {

        myAlarmReceiver = new AlarmReceiver();

        //create the receiver, don't call it
        LocalBroadcastManager.getInstance(InstrumentationRegistry.getInstrumentation().getContext()).registerReceiver((myAlarmReceiver), new IntentFilter("test"));
        //check if it is created
        assertFalse(myAlarmReceiver.getFlag());


        //call it, give some time to process
        LocalBroadcastManager.getInstance(InstrumentationRegistry.getInstrumentation().getContext()).sendBroadcast(new Intent("test"));
        Thread.sleep(5000);
        //check if it receive the call
        assertTrue(myAlarmReceiver.getFlag());


        //delete the test receiver
        LocalBroadcastManager.getInstance(InstrumentationRegistry.getInstrumentation().getContext()).unregisterReceiver(myAlarmReceiver);
    }
}
