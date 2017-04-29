package com.example.phupc.xoso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.phupc.xoso.LM_Fragment;
import com.example.phupc.xoso.PM_Fragment;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration config = getResources().getConfiguration();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(config.orientation != Configuration.ORIENTATION_LANDSCAPE){
            PM_Fragment pm_Fragment = new PM_Fragment();
            fragmentTransaction.replace(android.R.id.content, pm_Fragment);

        }else{
            LM_Fragment lm_Fragment = new LM_Fragment();
            fragmentTransaction.replace(android.R.id.content, lm_Fragment);
        }

        fragmentTransaction.commit();

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

}
