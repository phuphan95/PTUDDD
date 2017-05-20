package com.example.huy.clock;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.os.SystemClock;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.os.Message;
import android.app.TimePickerDialog;
import android.media.RingtoneManager;
import android.media.Ringtone;
import android.net.Uri;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerActivity extends Fragment {
    Ringtone r;
    long hCountDown,mCountDown,sCountDown,milisCountDown=0L;
    long SecondBreak=0L;
    TextView timerHour,timerMinute,timerSecond,timerMili;
    TextView minutes,seconds,milliseconds;     // after finish
    Button addTimer;
    Button resetTimer;
    LinearLayout timerE,timerM;
    public static long militime=0;
    public static int flag=0;
    public static int first=0;
    Handler handler;
    static CountDownTimer time;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    int Seconds, Minutes, MilliSeconds ;
    Calendar calendar = Calendar.getInstance();
    ServiceAlamr s;
    int hour1 = calendar.get(Calendar.HOUR_OF_DAY);
    int minute1 = calendar.get(Calendar.MINUTE);
//    TimePicker timePicker;
    int hour,minute;
    public TimerActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       final View view = inflater.inflate(R.layout.fragment_timer, container, false);



        addTimer = (Button) view.findViewById(R.id.button);
        resetTimer=(Button) view.findViewById(R.id.button2);
        timerHour = (TextView)view.findViewById(R.id.timerHour);
        timerMinute = (TextView)view.findViewById(R.id.timerMinute);
        timerSecond = (TextView)view.findViewById(R.id.timerSecond);
        timerMili = (TextView) view.findViewById(R.id.timerMilli);
        timerM = (LinearLayout)view.findViewById(R.id.timerM);
        timerE = (LinearLayout)view.findViewById(R.id.timerE);
        timerE.setVisibility(view.INVISIBLE);

        // use for after finishing countdown
        minutes = (TextView)view.findViewById(R.id.timerAfterMinute);
        seconds = (TextView)view.findViewById(R.id.timerAfterSecond);
        milliseconds = (TextView)view.findViewById(R.id.timerAfterMilliSecond);

        handler = new Handler() ;
        handler.postDelayed(runnable, 0);


        getActivity().setTitle("Timer");
        return view;
    }
    public Runnable runnable = new Runnable() {

        public void run() {
            addTimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(addTimer.getText().equals("NEW")) {
                        DialogFragment dialog = new TimePickerFragment1();
//
                        dialog.show(getFragmentManager(), "TimePicker");
//                       addTimer.setText("start");



                    }
                    else if(addTimer.getText().equals("STOP")){
//                        if(flag==1) {
//                            TimePickerFragment1.timer.cancel();
//                            flag=0;
//
//                        }
//                        else {

                            time.cancel();
//                        }
                        addTimer.setText("START");
                        addTimer.setBackgroundResource(R.drawable.roundshapebtn);
                    }
                    else if(addTimer.getText().equals("START")){


                            time = new CountDownTimer(militime, 100) {

                                public void onTick(long millisUntilFinished) {
                                    first = 1;
                                    militime = millisUntilFinished;
                                    milisCountDown = millisUntilFinished % 3600000 % 60000 % 1000 / 100;
                                    millisUntilFinished = millisUntilFinished / 1000;
                                    hCountDown = millisUntilFinished / 3600;
                                    sCountDown = millisUntilFinished % 3600;
                                    mCountDown = sCountDown / 60;
                                    mCountDown = mCountDown % 60;
                                    sCountDown = sCountDown % 60;

                                    timerHour.setText("" + String.format("%02d", hCountDown));
                                    timerMinute.setText("" + String.format("%02d", mCountDown));
                                    timerSecond.setText("" + String.format("%02d", sCountDown));
                                    timerMili.setText("" + String.format("%01d", milisCountDown));
//                                    countDown.setText("" + String.format("%02d", hCountDown) + ":"
//                                            + String.format("%02d", mCountDown) + ":"
//                                            + String.format("%02d", sCountDown) + "." + String.format("%01d", milisCountDown));


                                }

                                public void onFinish() {
                                    timerHour.setText("00");
                                    timerMinute.setText("00");
                                    timerSecond.setText("00");
                                    timerMili.setText("0");
                                    first = 0;
                                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                                    if(notification == null){
                                        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                    }
                                    r = RingtoneManager.getRingtone(getActivity().getApplicationContext(), notification);

                                    r.play();
                                    resetTimer.setText("CANCEL");
//                                    addTimer.setClickable(false);
//                                    s= new ServiceAlamr();
//                                    s.onStart(getContext(),null,hour1,minute1);
                                    addTimer.setText("NEW");
                                    addTimer.setClickable(false);
                                    addTimer.setEnabled(false);
                                    addTimer.setBackgroundResource(R.drawable.roundshapebtn2);
                                    timerE.setVisibility(View.VISIBLE);
                                    timerM.setVisibility(View.INVISIBLE);

                                    StartTime = SystemClock.uptimeMillis();
                                    handler.postDelayed(runnableAfter, 0);


                                }
                            }.start();
                            addTimer.setText("STOP");
                            addTimer.setBackgroundResource(R.drawable.roundshapebtn3);


                    }




                }
            });
            resetTimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (first == 1) {
                        time.cancel();
                        first=0;
                        timerHour.setText("00");
                        timerMinute.setText("00");
                        timerSecond.setText("00");
                        timerMili.setText("0");
                           addTimer.setText("NEW");
                        addTimer.setBackgroundResource(R.drawable.roundshapebtn);


                    }
                    else {
                        if(resetTimer.getText().equals("CANCEL")) {
//                        s.onStop(null);
                            r.stop();
                            resetTimer.setText("RESET");
                            addTimer.setClickable(true);
                            addTimer.setEnabled(true);
                            handler.removeCallbacks(runnableAfter);

                            minutes.setText("00");
                            seconds.setText("0");
                            milliseconds.setText("00");
                            timerE.setVisibility(View.INVISIBLE);
                            timerM.setVisibility(View.VISIBLE);

//                            addTimer.setText("new");
                        }
                    }
                }
            });



            handler.postDelayed(this, 0);
        }

    };

    public Runnable runnableAfter = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            SecondBreak=UpdateTime;


            Seconds = (int) (SecondBreak / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (SecondBreak % 1000)/10;

            minutes.setText(String.format("%02d", Minutes));
            seconds.setText(String.format("%02d", Seconds));
            milliseconds.setText(String.format("%02d", MilliSeconds));
//            time.setText("" + String.format("%02d", Minutes) + ":"
//                    + String.format("%02d", Seconds) + ":"
//                    + String.format("%02d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };

}
