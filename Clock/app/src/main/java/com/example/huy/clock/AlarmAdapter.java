package com.example.huy.clock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.huy.clock.Data.AlarmClock;
import com.example.huy.clock.Data.AlarmDataBase;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Viethuy_Iris on 4/28/2017.
 */

public class AlarmAdapter extends ArrayAdapter<AlarmClock> {
    Context context;
    int resId;
    ArrayList<AlarmClock> lstAlarmClock;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);

    public AlarmAdapter( Context context, int resId, ArrayList<AlarmClock> lstAlarmClock) {
        super(context, resId,lstAlarmClock);
        this.context = context;
        this.resId = resId;
        this.lstAlarmClock = lstAlarmClock;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(resId,null);
        final AlarmDataBase alarmDataBase =  new AlarmDataBase(getContext());
        TextView time = (TextView) convertView.findViewById(R.id.time);
      //  TextView day =(TextView)convertView.findViewById(R.id.day);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.sunmoon);
        alarmManager = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);

        final ToggleButton checkBtn =(ToggleButton)convertView.findViewById(R.id.checkBtn);


        final AlarmClock clock = lstAlarmClock.get(position);
        if (clock != null){
            time.setText(clock.getHour()+":"+clock.getMinute());
       //     day.setText(clock.getDay());
        }
        if(Integer.parseInt(clock.getHour())< 12) {
            imageView.setImageResource(R.drawable.sun);
        }
        else{
            imageView.setImageResource(R.drawable.moon2);
        }
        if(clock.getOnoff() == 1){
            checkBtn.setChecked(true);
        }
        else{
            checkBtn.setChecked(false);
        }
        if(checkBtn.isChecked()){

//            Calendar c = Calendar.getInstance();
//            c.setTimeInMillis(System.currentTimeMillis());
//            c.set(Calendar.HOUR_OF_DAY,9);
//            c.set(Calendar.MINUTE,19);
 //           Toast.makeText(getContext(),"vao",Toast.LENGTH_LONG).show();
//            Intent myIntent = new Intent(getContext(),AlarmReceiver.class);
//            pendingIntent = PendingIntent.getBroadcast(getContext(),0,myIntent,0);
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),3000,pendingIntent);
            Intent intent = new Intent(getContext(),MainActivity.class);
            ServiceAlamr s = new ServiceAlamr();
            s.onStart(getContext(),null,Integer.parseInt(clock.getHour()),Integer.parseInt(clock.getMinute()));
        }
        else {
        }
        checkBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    if(clock.getOnoff()==0) {
                     //   Toast.makeText(getContext(),"Co doi ",Toast.LENGTH_SHORT).show();
                        clock.setOnoff(1);
                        alarmDataBase.updateOnof(clock);

                  //      checkBtn.setChecked(true);
                    }


                }
                else{

                   // Toast.makeText(getContext(),"ko Co doi ",Toast.LENGTH_SHORT).show();
                    if(clock.getOnoff()==1){
                        clock.setOnoff(0);
                        alarmDataBase.updateOnof(clock);
                //        checkBtn.setChecked(false);
                    }
                    ServiceAlamr s = new ServiceAlamr();

                    s.onStop(getContext());

                }
            }
        });
        return convertView;

    }
}
