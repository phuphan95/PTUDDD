package com.example.huy.clock;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.huy.clock.Data.Lap;


import java.util.ArrayList;

/**
 * Created by Viethuy_Iris on 5/13/2017.
 */

public class StopWatchAdapter extends ArrayAdapter<Lap> {
    int resource;
    ArrayList<Lap> lstLap;
    Context context;

    public StopWatchAdapter(int resource1, ArrayList<Lap> lstLap, Context context1) {
        super(context1, resource1,lstLap);
        this.resource = resource1;
        this.lstLap = lstLap;
        this.context = context1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(resource,null);
        TextView totalTime = (TextView)convertView.findViewById(R.id.totalId);
        TextView currentTime = (TextView)convertView.findViewById(R.id.currentTime);
        TextView noId = (TextView)convertView.findViewById(R.id.noId);

        Lap lap = lstLap.get(position);
        totalTime.setText(lap.getTime());
        currentTime.setText(lap.getTimeSpace());
        noId.setText(lap.getId());

        return convertView;

    }
}
