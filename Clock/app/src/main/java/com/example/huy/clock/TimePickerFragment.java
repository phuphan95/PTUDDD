package com.example.huy.clock;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.huy.clock.Data.AlarmClock;
import com.example.huy.clock.Data.AlarmDataBase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Viethuy_Iris on 4/27/2017.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public TimePickerFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(),this,hour,minute, android.text.format.DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(view.isShown()) {
            AlarmDataBase alarmDataBase = new AlarmDataBase(getActivity());
            TextView hourTemp = (TextView) getActivity().findViewById(R.id.tempHour);
            TextView minuteTemp = (TextView) getActivity().findViewById(R.id.minuteTemp);
            ListView lst = (ListView) getActivity().findViewById(R.id.tv);
//        TextView time = (TextView)getActivity().findViewById(R.id.day);
            //   hourTemp.setText(""+hourOfDay+"");
            //    minuteTemp.setText(""+minute+"");
            AlarmClock alarmClock = new AlarmClock();
            alarmClock.setHour("" + hourOfDay);
            alarmClock.setMinute("" + minute);
            alarmClock.setStatus("AM");
            alarmClock.setDay("MonDay");
            alarmClock.setOnoff(1);

            alarmDataBase.AddAlarm(alarmClock);
            //  alarmDataBase.reopen();
            ArrayList<AlarmClock> lstAlarm = new ArrayList<>();
            lstAlarm = alarmDataBase.getAll();
            //   Toast.makeText(getActivity(), "-" + lstAlarm.size(), Toast.LENGTH_SHORT).show();
            AlarmAdapter adapter = new AlarmAdapter(getActivity(), R.layout.itemalarmclock, lstAlarm);
            lst.setAdapter(adapter);
        }
    //    Toast.makeText(getActivity(),hourTemp.getText().toString(),Toast.LENGTH_SHORT).show();
    }

}
