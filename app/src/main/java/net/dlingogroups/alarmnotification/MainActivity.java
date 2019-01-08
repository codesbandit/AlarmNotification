 package net.dlingogroups.alarmnotification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

 public class MainActivity extends AppCompatActivity {

     Button btn;
     TimePicker timePicker;
     TextView textView;

     int mHour,mMin;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         btn = (Button) findViewById(R.id.button);
         timePicker = (TimePicker) findViewById(R.id.timePicker);
         textView = (TextView) findViewById(R.id.textView);

         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //We need a calendar object to get the specified time in millis
                 //as the alarm manager method takes time in millis to setup the alarm
                 Calendar calendar = Calendar.getInstance();
                 if (android.os.Build.VERSION.SDK_INT >= 23) {
                     calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                             timePicker.getHour(), timePicker.getMinute(), 0);
                 } else {
                     calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                             timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
                 }


                 setAlarm(calendar.getTimeInMillis());
             }
         });
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//
//                calendar.set(Calendar.HOUR_OF_DAY,23);
//                calendar.set(Calendar.MINUTE,31);
//                calendar.set(Calendar.SECOND,30);
//
//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//
//                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
//            }
//        });

//         timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
//             @Override
//             public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//                 mHour = hourOfDay;
//                 mMin = minute;
//                 textView.setText(textView.getText().toString()+ " "+mHour + " " + mMin);
//             }
//         });
//    }

//         public void setTimePicker (View v){
//             AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//             Date date = new Date();
//
//             Calendar cal_alarm = Calendar.getInstance();
//             Calendar cal_now = Calendar.getInstance();
//
//             cal_now.setTime(date);
//             cal_alarm.setTime(date);
//
//             cal_alarm.set(Calendar.HOUR_OF_DAY, mHour);
//             cal_alarm.set(Calendar.MINUTE, mMin);
//             cal_alarm.set(Calendar.SECOND, 0);
//
//             if (cal_alarm.before(cal_now)) {
//                 cal_alarm.add(Calendar.DATE, 1);
//             }
//
//             Intent i = new Intent(MainActivity.this, NotificationReceiver.class);
//             PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 24444, i, 0);
//             alarmManager.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent);
//
//
//         }
     }

     private void setAlarm(long timeInMillis) {
         //getting the alarm manager
         AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

         //creating a new intent specifying the broadcast receiver
         Intent i = new Intent(this, NotificationReceiver.class);

         //creating a pending intent using the intent
         PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);

         //setting the repeating alarm that will be fired every day
         am.setRepeating(AlarmManager.RTC, timeInMillis, AlarmManager.INTERVAL_DAY, pi);
         Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();
     }
 }
