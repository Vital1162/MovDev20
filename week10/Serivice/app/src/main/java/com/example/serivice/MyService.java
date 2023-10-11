package com.example.serivice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.security.Provider;

public class MyService extends Service {

    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "CountingServiceChannel";
    private int count = 0;
    private Handler handler;
    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(Looper.getMainLooper());
        startCounting();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //do thing
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,"Counting",
                    NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //stop doing
        handler.removeCallbacksAndMessages(null);
        stopSelf(); // Stop the service
    }



    private void startCounting() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                count++;
                updateNotification(count);
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);

    }

    private void updateNotification(int count) {
        Notification notification = createNotification(count);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private Notification createNotification(int count) {
        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this, CHANNEL_ID)
                    .setContentTitle("Counting Service")
                    .setContentText("Count: " + count)
                    .setSmallIcon(R.drawable.ic_launcher_foreground);
        }
        return builder.build();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
