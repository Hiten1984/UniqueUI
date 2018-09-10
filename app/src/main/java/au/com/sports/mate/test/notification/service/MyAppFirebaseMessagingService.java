package au.com.sports.mate.test.notification.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import au.com.sports.mate.test.R;
import au.com.sports.mate.test.notification.ui.NotificationActivity;

public class MyAppFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("Hiten", "********** onMessageReceived **************");
        Log.d("Hiten", "********** Notification Data  **************"+remoteMessage.getNotification());
        Log.d("Hiten", "********** Data Payload **************"+remoteMessage.getData());

/**
 * This part of code will run if the payload body contains only the notification payload
 */

//      if (null != remoteMessage.getNotification().getClickAction()) {

        if (null != remoteMessage.getNotification()) {
            startActivity(remoteMessage.getNotification().getClickAction(), null, this);
        }

        /**
         * This part of code will run if the payload body contains only the Data payload
         */

        Map<String, String> data = remoteMessage.getData();
        if (null != data && 0 < data.size()) {
            if (data.containsKey("custom_key_1")) {
                sendNotification(remoteMessage);
            }
        }
    }

    private void sendNotification(RemoteMessage remoteMessage) {
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra("text", remoteMessage.getData().get("custom_key_2"));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent =  PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder  notificationBuilder = new NotificationCompat.Builder(this, createChannel())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage.getData().get("custom_key_1"))
                .setContentText(remoteMessage.getData().get("custom_key_2"))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notificationBuilder.build());

    }

    private void startActivity(String className, Bundle extras, Context context) {
        Class cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            //means you made a wrong input in firebase console
        }
        Intent intent = new Intent(context, cls);
        if (null != extras) {
            intent.putExtras(extras);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    private String createChannel() {
        String channelId = "smTestChannel";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(channelId, "smTest", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        return channelId;
    }
}
