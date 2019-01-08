package net.dlingogroups.alarmnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {
        Vibrator vibration = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibration.vibrate(2000);

//        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        Intent repeating_intent = new Intent(context,MainActivity.class);
//        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(context,100, repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);
//
//         NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
//                 .setContentIntent(pendingIntent)
//                 .setSmallIcon(R.drawable.ic_launcher_background)
//                 .setContentTitle("Dzikir Sunnah")
//                 .setContentText("Dzikir Time ")
//                 .setAutoCancel(true);
//
//         notificationManager.notify(100,builder.build());

        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent resultPenfingIntent = PendingIntent.getActivity(context,1,resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification noti = new Notification.Builder(context)
                .setContentTitle("Jangan lupa dzikr")
                .setContentText("Waktu dzikir")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true)
                .setContentIntent(resultPenfingIntent).build();
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        noti.flags|=Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0,noti);


        Uri path = Uri.parse("android.resource://"+context.getPackageName()+"/raw/bismillah.mp3");
        // The line below will set it as a default ring tone replace
        // RingtoneManager.TYPE_RINGTONE with RingtoneManager.TYPE_NOTIFICATION
        // to set it as a notification tone
        RingtoneManager.setActualDefaultRingtoneUri(context.getApplicationContext(), RingtoneManager.TYPE_NOTIFICATION, path);
        Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), path);
        r.play();

        // THIS IS ADDED TO SEE REVERT

//        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//        Ringtone r = RingtoneManager.getRingtone(context,notification);
//        r.play();

    }

}
