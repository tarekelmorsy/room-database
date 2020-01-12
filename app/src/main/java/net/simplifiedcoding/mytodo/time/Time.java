package net.simplifiedcoding.mytodo.time;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import net.simplifiedcoding.mytodo.AddTaskActivity;
import net.simplifiedcoding.mytodo.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;



public class Time extends AppCompatActivity {

    DatePicker pickerDate;
    TimePicker pickerTime;
    Button buttonSetAlarm;
    TextView info;
    Ringtone ringTone;
    final static int RQS_1 = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

                info = (TextView) findViewById(R.id.info);
                pickerDate = (DatePicker)findViewById(R.id.pickerdate);
                pickerTime = (TimePicker)findViewById(R.id.pickertime);
                Calendar now = Calendar.getInstance();
                pickerDate.init(
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH),
                        null);
                pickerTime.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
                pickerTime.setCurrentMinute(now.get(Calendar.MINUTE));

                buttonSetAlarm = (Button)findViewById(R.id.setalarm);
                buttonSetAlarm.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View arg0) {
                        Calendar current = Calendar.getInstance();
                        Calendar cal = Calendar.getInstance();
                        cal.set(pickerDate.getYear(),
                                pickerDate.getMonth(),
                                pickerDate.getDayOfMonth(),
                                pickerTime.getCurrentHour(),
                                pickerTime.getCurrentMinute(),
                                00);

                        if (cal.compareTo(current) <= 0) {
                            //The set Date/Time already passed
                            Toast.makeText(getApplicationContext(), "Invalid Date/Time", Toast.LENGTH_LONG).show();
                        } else {
                            setAlarm(cal);
                        }
                        Intent intent = new Intent(Time.this, AddTaskActivity.class);
                        startActivity(intent);
                    }

                  //  Intent
                });
            }

            private void setAlarm(Calendar targetCal){
                info.setText("\n\n**\n" + "Alarm is set@ " + targetCal.getTime() + "\n" + "**\n");

                Intent intent = new Intent(getBaseContext(), MyBroadcastReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
                AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
                Uri uriAlarm = RingtoneManager.getDefaultUri(R.raw.brid);
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                if((targetCal.getTime()).equals(currentDateTimeString)){
                    ringTone = RingtoneManager.getRingtone(getApplicationContext(), uriAlarm);
                }


            }
        }






