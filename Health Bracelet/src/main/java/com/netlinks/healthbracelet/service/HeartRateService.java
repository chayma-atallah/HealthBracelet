/*
 * Copyright (C) 2014 Health Bracelet Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.netlinks.healthbracelet.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import com.netlinks.healthbracelet.MainActivity;
import com.netlinks.healthbracelet.R;

/**
 * Created by Saif Chaouachi on 1/28/14.
 */
public class HeartRateService extends Service {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void startPlayback(String album, String artist) {
        int NOTIFICATION_ID = 1;
// Create an Intent that will open the main Activity
// if the notification is clicked.

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 1, intent, 0);
// Set the Notification UI parameters
        Notification notification =
                new Notification.Builder(getApplicationContext()).setSmallIcon(R.drawable.ic_launcher)
                        .setTicker("Starting DrmStore.Playback").setWhen(System.currentTimeMillis())
                        .setContentIntent(pi)
                        .build();

        // notification.setLatestEventInfo(this, album, artist, pi);
// Set the Notification as ongoing
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
// Move the Service to the Foreground
        startForeground(NOTIFICATION_ID, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    public class MyBinder extends Binder {
        public HeartRateService getService() {
            return HeartRateService.this;
        }
    }
    private final IBinder binder = new MyBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        startPlayback("Album", "Artist");
        return START_STICKY;
    }
}
