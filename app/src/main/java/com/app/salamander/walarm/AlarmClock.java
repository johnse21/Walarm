package com.app.salamander.walarm;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ChristianJohn on 8/4/2016.
 */

public class AlarmClock extends Activity{
    private TextView hourTxt;
    private TextView minuteTxt;
    private TextView dateTxt;
    private Calendar calendar;
    private Timer timer;
    private Button addAlarmBtn;

    Handler timerHandler = new Handler();

    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {

            calendar = Calendar.getInstance();
            if(calendar.get(Calendar.HOUR_OF_DAY) < 10){
                hourTxt.setText(String.format("%s","0"+calendar.get(Calendar.HOUR_OF_DAY)));
            }else{
                hourTxt.setText(String.format("%s",""+calendar.get(Calendar.HOUR_OF_DAY)));
            }

            if(calendar.get(Calendar.MINUTE) < 10){
                minuteTxt.setText(String.format("%s","0"+calendar.get(Calendar.MINUTE)));
            }else{
                minuteTxt.setText(String.format("%s",""+calendar.get(Calendar.MINUTE)));
            }

            dateTxt.setText(String.format("%s/%s/%s",""+(calendar.get(Calendar.MONTH)+1),""+calendar.get(Calendar.DAY_OF_MONTH),""+calendar.get(Calendar.YEAR)));

            timerHandler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_clock);

        hourTxt = (TextView)findViewById(R.id.hourTxt);
        minuteTxt = (TextView)findViewById(R.id.minuteTxt);
        dateTxt = (TextView)findViewById(R.id.dateTxt);
        addAlarmBtn = (Button)findViewById(R.id.addAlarmBtn);

        timerHandler.postDelayed(timerRunnable, 1);
    }
}
