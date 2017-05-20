package com.example.huy.clock;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.huy.clock.Data.Clock;

import java.util.ArrayList;

/**
 * Created by Viethuy_Iris on 5/1/2017.
 */

public class AddingWorldClockAdapter extends ArrayAdapter<Clock> {
    Context context;
    int resId;
    ArrayList<Clock> lstWorldClock;

    public AddingWorldClockAdapter( Context context, int resId, ArrayList<Clock> lstWorldClock) {
        super(context, resId,lstWorldClock);
        this.context = context;
        this.resId = resId;
        this.lstWorldClock = lstWorldClock;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(resId,null);
        TextView addName = (TextView)convertView.findViewById(R.id.addName);
        TextView addTime = (TextView)convertView.findViewById(R.id.addTime);

        Clock clock = lstWorldClock.get(position);
        addName.setText(clock.getName());
        addTime.setText(clock.getTime());

        return convertView;
    }
}
