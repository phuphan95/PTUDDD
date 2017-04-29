package com.example.phupc.xoso;

/**
 * Created by Phupc on 4/28/2017.
 */

import com.example.phupc.xoso.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PM_Fragment extends Fragment{
    Button button3;
    Button button4;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE1";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_pm, container, false);
        //
        button3 =(Button) view.findViewById(R.id.button3);
        button4=(Button) view.findViewById(R.id.button4);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMessage1(v);

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                System.exit(0);

            }
        });

        //
        return view;
    }
    public void sendMessage1(View view) {
        Intent intent = new Intent(getActivity(), TinhActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "");
        getActivity().startActivity(intent);
    }
}
