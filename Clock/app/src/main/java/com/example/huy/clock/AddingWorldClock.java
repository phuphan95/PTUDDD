package com.example.huy.clock;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.huy.clock.Data.AlarmClock;
import com.example.huy.clock.Data.Clock;
import com.example.huy.clock.Data.WorldClockDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddingWorldClock extends DialogFragment {

    ListView lstWorldClock;
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);

    Calendar mCalendar = new GregorianCalendar();
    TimeZone mTimeZone = mCalendar.getTimeZone();
    int mGMTOffset = mTimeZone.getRawOffset();
    long GMT = TimeUnit.HOURS.convert(mGMTOffset, TimeUnit.MILLISECONDS);

    public AddingWorldClock() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adding_world_clock, container, false);
        lstWorldClock = (ListView)view.findViewById(R.id.lstAddWorld);
        final WorldClockDatabase worldClockDatabase = new WorldClockDatabase(getActivity());
        String[] lstName = new String[]{"New York","London","Beijing","Sydney","Mexico City","New Delhi","Tokyo","Moscow","Stockholm"};
        String[] lstTime = new String[]{hour+":"+minute,"2:20"};
        int[] dolech = {-5,0,8,10,-7,5,9,3,1};
        ArrayList<Clock> lstClock = new ArrayList<>();
        for(int i = 0 ; i<lstName.length; i++){
            Clock clock = new Clock();
            clock.setName(lstName[i]);
            int muigio = (int)GMT;
            int time = hour + dolech[i]-muigio;
            if(time > 24){
                time = time - 24;
            }
            else{
                if(time < 0){
                    time = time + 24 ;
                }
            }
            if(time>12 && time < 24 )
            {
                clock.setTime(time+":"+minute+" PM");
            }
            else {
                clock.setTime(time+":"+minute+" AM" );
            }
            //clock.setTime(time+":"+minute);
            clock.setDolech(dolech[i]);
            lstClock.add(clock);

        }
  //      Toast.makeText(getActivity(),""+GMT,Toast.LENGTH_LONG).show();
        AddingWorldClockAdapter adapter = new AddingWorldClockAdapter(getActivity(),R.layout.itemaddingworldclock,lstClock);
        lstWorldClock.setAdapter(adapter);
        lstWorldClock.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Clock c = (Clock)lstWorldClock.getItemAtPosition(position);
               // Toast.makeText(getActivity(),c.getName(),Toast.LENGTH_LONG).show();
                worldClockDatabase.add(c);
                WorldClockAdapter adapter1 = new WorldClockAdapter(getActivity(),R.layout.itemworldclock,worldClockDatabase.getAll());
                ListView lst = (ListView)getActivity().findViewById(R.id.lstWorldClock);
                lst.setAdapter(adapter1);
                dismiss();
            }
        });

        return view;

    }


}
