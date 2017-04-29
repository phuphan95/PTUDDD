package com.example.phupc.xoso;

/**
 * Created by Phupc on 4/28/2017.
 */

import com.example.phupc.xoso.R;
import android.widget.Toast;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LM_Fragment extends Fragment{
    Button button1;
    Button button2;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_lm, container, false);
        //
        button1 =(Button) view.findViewById(R.id.button1);
        button2=(Button) view.findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMessage(v);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.exit(0);

            }
        });

        //
        return view;
    }
    public void sendMessage(View view) {
        Intent intent = new Intent(getActivity(), TinhActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "");
        getActivity().startActivity(intent);
    }
}
