package com.app.salamander.walarm;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ChristianJohn on 8/4/2016.
 */

public class AlarmClock extends Activity{
    private Calendar calendar;
    private Timer timer;
    private ImageView digitOneImg;
    private ImageView digitTwoImg;
    private ImageView digitThreeImg;
    private ImageView digitFourImg;
    private ImageView colonImg;
    private int image[];
    private boolean isColonVisible;

    Handler timerHandler = new Handler();

    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {

            calendar = Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("HH");
            String strDate = mdformat.format(calendar.getTime());
            int time = Integer.parseInt(strDate);
            if(time < 10){
                digitOneImg.setImageResource(image[0]);
                digitTwoImg.setImageResource(image[time]);
            }else{
                int tenths = time/10;
                digitOneImg.setImageResource(image[tenths]);
                digitTwoImg.setImageResource(image[(time-(tenths*10))]);
            }
            mdformat = new SimpleDateFormat("mm");
            strDate = mdformat.format(calendar.getTime());
            time = Integer.parseInt(strDate);
            if(time < 10){
                digitThreeImg.setImageResource(image[0]);
                digitFourImg.setImageResource(image[time]);
            }else{
                int tenths = time/10;
                digitThreeImg.setImageResource(image[tenths]);
                digitFourImg.setImageResource(image[(time-(tenths*10))]);
            }

            colonImg.setWillNotDraw(isColonVisible);
            isColonVisible = !isColonVisible;
           // dateTxt.setText(String.format("%s/%s/%s",""+(calendar.get(Calendar.MONTH)+1),""+calendar.get(Calendar.DAY_OF_MONTH),""+calendar.get(Calendar.YEAR)));

            timerHandler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_clock);

        image = new int[]{R.drawable.zero, R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four,
                            R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine};

        digitOneImg = (ImageView)findViewById(R.id.digitOneImg);
        digitTwoImg = (ImageView)findViewById(R.id.digitTwoImg);
        digitThreeImg = (ImageView)findViewById(R.id.digitThreeImg);
        digitFourImg = (ImageView)findViewById(R.id.digitFourImg);
        colonImg = (ImageView)findViewById(R.id.colonImg);
        isColonVisible = false;

        timerHandler.postDelayed(timerRunnable, 1);
    }
}
