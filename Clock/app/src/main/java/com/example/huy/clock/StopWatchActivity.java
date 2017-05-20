package com.example.huy.clock;


import android.nfc.TagLostException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Handler;
import android.widget.ArrayAdapter;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import android.os.SystemClock;
import android.util.Log;

import com.example.huy.clock.Data.Lap;

/**
 * A simple {@link Fragment} subclass.
 */
public class StopWatchActivity extends Fragment {
    long SecondBreak=0L,flag;
    int count=0;
    ListView lstlap;
    Button start ;
    Button savelap;
    TextView minutes,seconds,milliseconds;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;

    Handler handler;

    int Seconds, Minutes, MilliSeconds ;




    List<String> ListElementsArrayList ;
    ArrayList<Lap>arrLap=new ArrayList<Lap>();
    Lap lap = null;
   // ArrayAdapter<Lap>adapter=null;
    StopWatchAdapter adapter ;
    public StopWatchActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stop_watch, container, false);

        start = (Button) view.findViewById(R.id.btnstart);
        lstlap = (ListView) view.findViewById(R.id.lstLap);
        savelap =(Button) view.findViewById(R.id.lap);
        //time = (TextView) view.findViewById(R.id.timeStopId);
        minutes = (TextView)view.findViewById(R.id.stopwatchMinute);
        seconds = (TextView)view.findViewById(R.id.stopwatchSecond);
        milliseconds = (TextView)view.findViewById(R.id.stopwatchMilliSecond);
        handler = new Handler() ;

//        adapter = new ArrayAdapter<Lap >(getActivity(),
//                R.layout.itemstopwatch,
//                arrLap
//        );
        adapter = new StopWatchAdapter(R.layout.itemstopwatch,arrLap,getActivity());

        lstlap.setAdapter(adapter);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(start.getText().equals("Start")) {

                    StartTime = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);
                    start.setText("pause");
                    start.setBackgroundResource(R.drawable.roundshapebtn3);
                    savelap.setText("lap");
//                reset.setEnabled(false);
                }
                else {

                    TimeBuff += MillisecondTime;

                    handler.removeCallbacks(runnable);
                    start.setText("Start");
                    start.setBackgroundResource(R.drawable.roundshapebtn);
                    savelap.setText("reset");

//                    reset.setEnabled(true);
                }

            }
        });
        savelap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(savelap.getText().equals("lap")) {
                    long TimeSpace=UpdateTime-flag;

                    lap = new Lap();
                    count++;
                    lap.setId(count);
                    String time = minutes.getText().toString()+":"+seconds.getText().toString()+":"+milliseconds.getText().toString();
                    lap.setTime(time);
                    long sec,mi,mili;
                    sec = (int) (TimeSpace / 1000);
                    mi = sec / 60;
                    sec = sec % 60;
                    mili = (int) (TimeSpace % 1000)/10;
                    lap.setTimeSpace("" + String.format("%02d", mi) + ":"
                            + String.format("%02d", sec) + ":"
                            + String.format("%02d", mili));
                    arrLap.add(0,lap);

//                    ListElementsArrayList.add(time.getText().toString());

                    adapter.notifyDataSetChanged();
                    flag=UpdateTime;

                }
                else {
                    MillisecondTime = 0L ;
                    StartTime = 0L ;
                    TimeBuff = 0L ;
                    UpdateTime = 0L ;
                    Seconds = 0 ;
                    Minutes = 0 ;
                    MilliSeconds = 0 ;

                    //time.setText("00:00:00");
                    minutes.setText("00");
                    seconds.setText("00");
                    milliseconds.setText("00");

                    arrLap.clear();
                    count=0;
                    adapter.notifyDataSetChanged();
                    start.setText("Start");
                    flag=0L;

                }

            }
        });
        getActivity().setTitle("Stopwatch");

        return view ;
    }
    public Runnable runnable = new Runnable() {

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
