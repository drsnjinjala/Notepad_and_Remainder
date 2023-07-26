package com.notepad.remainder.Utility;

import static com.notepad.remainder.App.ALARM_CHANNEL_ID;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.util.Util;
import com.notepad.remainder.Activity.MainActivity;
import com.notepad.remainder.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Utility {
    public static final String USERNAME = "username";
    //    public static final String DATE_FORMAT = "d MMM yyyy";
    public static final int DATE_FORMAT = DateFormat.DEFAULT;
    public static final String TASK_ID = "TASKID";
    public static final String TASK_TITLE = "TASKNAME";
    public static final String TASK_Description = "Description";
    public static final String TASK_TIME = "TASKTIME";
    public static final String ALARM_CHANNEL_ID = "channel alarm";
    public static final String TIME_12_FORMAT = "h:mm a";
    public static final String TIME_24_FORMAT = "HH:mm";
    public static final int DEFUALT_CATEOGRY = 0;

    public static void GotoNext(Activity activity, Class<?> aClass) {
        // APIManager.showInter(activity, false, isfail -> {
        activity.startActivity(new Intent(activity, aClass));
        activity.overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_left);
        //  });
    }

    public static void GotoBack(Activity activity) {
        //   APIManager.showInter(activity, true, isfail -> {
        activity.finish();
        activity.overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
        //  });
    }

    public static void StutasBar(Activity activity) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(activity, R.color.background_color));
    }

    public static String getTime(String format, Calendar calendar) {
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    public static String getWelcomeMessage() {
        @SuppressLint("SimpleDateFormat") String time = new SimpleDateFormat("H").format(Calendar.getInstance().getTime());
        int hour = Integer.parseInt(time);
        if (hour >= 5 && hour <= 11)
            return "Good Morning";
        else if (hour >= 12 && hour <= 16)
            return "Good Afternoon";
        else if (hour >= 17 && hour <= 18)
            return "Good Evening";
        else
            return "Good Night";
    }

    public static String getTimeFormated(Calendar calendar, Context context) {
        return (android.text.format.DateFormat.is24HourFormat(context)) ? getTime(TIME_24_FORMAT, calendar) :
                getTime(TIME_12_FORMAT, calendar);
    }

    public static String getTimeFormated(String[] time, Context context) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
        return getTimeFormated(calendar, context);
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
                .setContentText(message + "\n" + "Today at " + getTimeFormated(calendar, context))
                .setAutoCancel(true); // clear notification after click
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }


}
