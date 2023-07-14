package com.notepad.remainder.receiver;

import static com.notepad.remainder.Utility.Utility.ALARM_CHANNEL_ID;
import static com.notepad.remainder.Utility.Utility.TASK_Description;
import static com.notepad.remainder.Utility.Utility.TASK_TITLE;
import static com.notepad.remainder.Utility.Utility.getTimeFormated;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.bumptech.glide.util.Util;
import com.notepad.remainder.Activity.MainActivity;
import com.notepad.remainder.R;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {
    @SuppressLint("MissingPermission")
    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra(TASK_TITLE);
        String message = intent.getStringExtra(TASK_Description);
        showNotification(title, message, context);
    }

    public static void showNotification(String title, String message, Context context) {
        Calendar calendar = Calendar.getInstance();
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(ALARM_CHANNEL_ID,
                    ALARM_CHANNEL_ID,
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(ALARM_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, ALARM_CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_24) // notification icon
                .setContentTitle(title) // title for notification
                .setContentText(message + " " + "Today at " + getTimeFormated(calendar, context))
                .setAutoCancel(true); // clear notification after click
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }
}
