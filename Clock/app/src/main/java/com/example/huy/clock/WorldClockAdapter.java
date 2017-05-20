package com.example.huy.clock;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AnalogClock;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huy.clock.Data.Clock;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by Viethuy_Iris on 4/26/2017.
 */

public class WorldClockAdapter extends ArrayAdapter<Clock> {
    Context context;
    int resId;
    ArrayList<Clock> lstWorldClock;

    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);

    Calendar mCalendar = new GregorianCalendar();
    TimeZone mTimeZone = mCalendar.getTimeZone();
    int mGMTOffset = mTimeZone.getRawOffset();
    long GMT = TimeUnit.HOURS.convert(mGMTOffset, TimeUnit.MILLISECONDS);




    public WorldClockAdapter( Context context, int resId, ArrayList<Clock> lstWorldClock) {
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

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView time = (TextView) convertView.findViewById(R.id.timeWorld);
        TextView date = (TextView) convertView.findViewById(R.id.dateWorld);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.sunmoonimage);
        Date dt = new Date();
        calendar.setTime(dt);

        Clock c = lstWorldClock.get(position);
        String setTime;
        int currentGMT = (int)GMT;
        int newHour = hour + c.getDolech()-currentGMT;
        if( newHour > 24){
            newHour = newHour - 24;
            calendar.add(Calendar.DATE,1);
            dt = calendar.getTime();
        }
        else{
            if(newHour < 0){

                newHour = newHour + 24 ;
                calendar.add(Calendar.DATE,-1);
                dt = calendar.getTime();

            }
        }
        if(newHour>12 && newHour < 24 )
        {
            setTime = newHour+":"+minute+" PM";
            imageView.setImageResource(R.drawable.moon2);

        }
        else {
            setTime = newHour+":"+minute+" AM";
            imageView.setImageResource(R.drawable.sun);
        }

        if(c!= null){
            name.setText(c.getName());
            time.setText(setTime);
            calendar.setTime(dt);
            date.setText((calendar.get(Calendar.DAY_OF_MONTH))+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR));

        }

        return convertView;

    }
}
