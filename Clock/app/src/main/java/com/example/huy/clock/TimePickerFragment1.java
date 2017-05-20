package com.example.huy.clock;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.graphics.Color;
import com.example.huy.clock.Data.AlarmClock;
import com.example.huy.clock.Data.AlarmDataBase;
import java.util.Calendar;
import android.app.AlertDialog;
import java.util.ArrayList;
import android.view.Gravity;
import android.os.CountDownTimer;
import android.util.Log;
import android.os.Handler;
import android.app.Activity;

/**
 * Created by Viethuy_Iris on 4/27/2017.
 */

public class TimePickerFragment1 extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    long hCountDown,mCountDown,sCountDown,milisCountDown=0L;
    Button start,reset;
    Handler h;
    static CountDownTimer timer;

    public TimePickerFragment1() {

    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = 0;
        int minute = 0;

        TimePickerDialog tpd = new TimePickerDialog(getActivity(),this, hour, minute, true);

        //You can set a simple text title for TimePickerDialog
        //tpd.setTitle("Title Of Time Picker Dialog");

        /*.........Set a custom title for picker........*/

        /*.........End custom title section........*/

        return tpd;
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (view.isShown()) {
            final TextView timerHour = (TextView) getActivity().findViewById(R.id.timerHour);
            final TextView timerMinute = (TextView) getActivity().findViewById(R.id.timerMinute);
            final TextView timerSecond = (TextView) getActivity().findViewById(R.id.timerSecond);
            final TextView timerMili = (TextView) getActivity().findViewById(R.id.timerMilli);
            final Button start = (Button) getActivity().findViewById(R.id.button);
            Button reset = (Button) getActivity().findViewById(R.id.button2);


            int totalSeconds = (hourOfDay * 60 + minute) * 60;
            if (totalSeconds != 0) {
                TimerActivity.flag = 1;
//            TimerActivity.first=1;
                start.setText("START");
            }
            TimerActivity.militime = totalSeconds * 1000;
            hCountDown = totalSeconds / 3600;
            totalSeconds = totalSeconds % 3600;
            mCountDown = totalSeconds / 60;
            sCountDown = totalSeconds % 60;
            timerHour.setText("" + String.format("%02d", hCountDown));
            timerMinute.setText("" + String.format("%02d", mCountDown));
            timerSecond.setText("" + String.format("%02d", sCountDown));
            timerMili.setText("" + String.format("%01d", milisCountDown));

        }
    }



}
