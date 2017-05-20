package com.example.huy.clock;




import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.TextView;


import com.example.huy.clock.Data.AlarmClock;
import com.example.huy.clock.Data.AlarmDataBase;


import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmActivity extends Fragment {
    ArrayList<AlarmClock> lst;
    FloatingActionButton btn;
    ListView lstAlarm;
    TextView hourTemp,minuteTemp,tempHour,tempMinute,locationDate;
    Calendar c = Calendar.getInstance();
    int date = c.get(Calendar.DAY_OF_MONTH);
    int month = c.get(Calendar.MONTH);
    int year = c.get(Calendar.YEAR);
    public static AlarmDataBase alarmDataBase;
    private static AlarmActivity inst;
    private static final int MENU_ITEM_VIEW = 111;
    private static final int MENU_ITEM_EDIT = 222;
    private static final int MENU_ITEM_CREATE = 333;
    private static final int MENU_ITEM_DELETE = 444;
    public AlarmActivity() {
        // Required empty public constructor

    }
    public static AlarmActivity instance() {
        return inst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);

        alarmDataBase =  new AlarmDataBase(getActivity());


        lst = new ArrayList<AlarmClock>();
        lstAlarm = (ListView)view.findViewById(R.id.tv);
        btn = (FloatingActionButton) view.findViewById(R.id.addAlarm);
        hourTemp = (TextView)view.findViewById(R.id.hourTemp);
        minuteTemp = (TextView)view.findViewById(R.id.minuteTemp);
        tempHour = (TextView)view.findViewById(R.id.tempHour);
        tempMinute = (TextView)view.findViewById(R.id.tempMinute);
        locationDate = (TextView)view.findViewById(R.id.locationDate);
        locationDate.setText(""+date+"/"+month+"/"+year);

       // final AlarmClock alarmClock = new AlarmClock();
        hourTemp.setText("1111111");
        lst = alarmDataBase.getAll();
        final AlarmAdapter alarmAdapter = new AlarmAdapter(getActivity(),R.layout.itemalarmclock,lst);
        lstAlarm.setAdapter(alarmAdapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  new DoingGetDate().execute("0");
                DialogFragment dialog = new TimePickerFragment();
//
               dialog.show(getFragmentManager(),"TimePicker");

            }


        });
        registerForContextMenu(lstAlarm);
     //   getActivity().openContextMenu(view);
        getActivity().setTitle("Alarm Clock");
        return view;


    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        getActivity().getMenuInflater().inflate(R.menu.contextmenu,menu);

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final AlarmClock selectedNote = (AlarmClock) this.lstAlarm.getItemAtPosition(info.position);
        switch (item.getItemId()){
            case R.id.delete :
                deleteAlarm(selectedNote);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    public void deleteAlarm(AlarmClock alarmClock){
        AlarmDataBase alarm = new AlarmDataBase(getActivity());
        alarm.deletelast(alarmClock);
        AlarmAdapter adapter = new AlarmAdapter(getActivity(),R.layout.itemalarmclock,alarm.getAll());
        this.lstAlarm.setAdapter(adapter);
    }
}
