package com.example.huy.clock;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AnalogClock;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huy.clock.Data.AlarmClock;
import com.example.huy.clock.Data.AlarmDataBase;
import com.example.huy.clock.Data.Clock;
import com.example.huy.clock.Data.WorldClockDatabase;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorldClock extends Fragment {
    FloatingActionButton btn ;
    ListView lstWorldClock;
    ArrayList<Clock> lstClock ;
    TextView nameCurent,timeCurrent;
    public static WorldClockDatabase worldClockDatabase;
    public WorldClock() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_world_clock,container,false);
        worldClockDatabase = new WorldClockDatabase(getActivity());
        lstWorldClock = (ListView) view.findViewById(R.id.lstWorldClock);
        btn = (FloatingActionButton)view.findViewById(R.id.addWorld);
        WorldClockAdapter adapter = new WorldClockAdapter(getActivity(),R.layout.itemworldclock,worldClockDatabase.getAll());
        lstWorldClock.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddingWorldClock add = new AddingWorldClock();
                add.show(getFragmentManager(),"vi du");
            }
        });
        registerForContextMenu(lstWorldClock);
        getActivity().setTitle("World Clock");
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
        final Clock selectedNote = (Clock) this.lstWorldClock.getItemAtPosition(info.position);
        switch (item.getItemId()){
            case R.id.delete :
                delete(selectedNote);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    public void delete(Clock clock){
        WorldClockDatabase alarm = new WorldClockDatabase(getActivity());
        alarm.deletelast(clock);
        WorldClockAdapter adapter = new WorldClockAdapter(getActivity(),R.layout.itemworldclock,alarm.getAll());
        this.lstWorldClock.setAdapter(adapter);
    }
}
