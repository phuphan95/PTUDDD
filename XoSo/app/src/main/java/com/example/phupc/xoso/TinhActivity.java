package com.example.phupc.xoso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.widget.TextView;
import java.io.IOException;
import android.view.View;
import android.widget.AdapterView;
import java.util.List;
import android.util.Log;
import java.util.ArrayList;
public class TinhActivity extends AppCompatActivity {
    JSONObject jsonObj;
    Spinner spinner;
    Spinner spinner1;
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;
    TextView text7;
    TextView text8;
    TextView textdb;
     int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh);
        //gọi addControl trước
        addControls();
        //gọi addevent sau
        addEvents();
    }
    public void addControls(){
        String arr[]={
                "AG",
                "BD",
                "BL",
                "BP","BTH","CM","CT","HCM"
        };
        spinner=(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arr
                );
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spinner.setAdapter(adapter);
        spinner1=(Spinner) findViewById(R.id.spinner1);

        text1=(TextView) findViewById(R.id.text1) ;
        text2=(TextView) findViewById(R.id.text2) ;
        text3=(TextView) findViewById(R.id.text3) ;
        text4=(TextView) findViewById(R.id.text4) ;
        text5=(TextView) findViewById(R.id.text5) ;
        text6=(TextView) findViewById(R.id.text6) ;
        text7=(TextView) findViewById(R.id.text7) ;
        text8=(TextView) findViewById(R.id.text8) ;
        textdb=(TextView) findViewById(R.id.textdb) ;
        super.onResume();


    }
    public void addEvents(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int b, long l) {

                // Your code here
                onResume();
            }


            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

    }
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        new MyJsonTask().execute("http://thanhhungqb.tk:8080/kqxsmn");
    }
    //Lớp xử lý đa tiến trình:
    public class MyJsonTask extends AsyncTask<String, JSONObject, Void>
    {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(String... params) {
            //Lấy URL truyền vào
            String url=params[0];

            try {
                //đọc và chuyển về JSONObject
                jsonObj = MyJsonReader.readJsonFromUrl(url);

                publishProgress(jsonObj);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(JSONObject... values) {
            super.onProgressUpdate(values);
            //ta cập nhật giao diện ở đây:
             jsonObj=values[0];

            try {
                if(spinner.getSelectedItem().toString()=="AG"){
                    JSONObject ag=jsonObj.getJSONObject("AG");
                    int a=ag.length();
                    List<String> list = new ArrayList<String>();
                    for(int i = 0; i < a; i++){
                        list.add(ag.names().getString(i));
                    }
                    String[] arr = list.toArray(new String[list.size()]);
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>
                            (
                                    TinhActivity.this,
                                    android.R.layout.simple_spinner_item,
                                    arr
                            );
                    //phải gọi lệnh này để hiển thị danh sách cho Spinner
                    adapter.setDropDownViewResource
                            (android.R.layout.simple_list_item_single_choice);
                    //Thiết lập adapter cho Spinner
                    spinner1.setAdapter(adapter);
                    if(spinner1.getCount()>flag)
                        spinner1.setSelection(flag);
                    else{
                        flag=0;
                        spinner1.setSelection(flag);
                    }


                }
                else if(spinner.getSelectedItem().toString()=="BD"){
                    JSONObject ag=jsonObj.getJSONObject("BD");
                    int a=ag.length();
                    List<String> list = new ArrayList<String>();
                    for(int i = 0; i < a; i++){
                        list.add(ag.names().getString(i));
                    }
                    String[] arr = list.toArray(new String[list.size()]);
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>
                            (
                                    TinhActivity.this,
                                    android.R.layout.simple_spinner_item,
                                    arr
                            );
                    //phải gọi lệnh này để hiển thị danh sách cho Spinner
                    adapter.setDropDownViewResource
                            (android.R.layout.simple_list_item_single_choice);
                    //Thiết lập adapter cho Spinner
                    spinner1.setAdapter(adapter);
                    if(spinner1.getCount()>flag)
                        spinner1.setSelection(flag);
                    else{
                        flag=0;
                        spinner1.setSelection(flag);
                    }

                }
                else if(spinner.getSelectedItem().toString()=="BL"){
                    JSONObject ag=jsonObj.getJSONObject("BL");
                    int a=ag.length();
                    List<String> list = new ArrayList<String>();
                    for(int i = 0; i < a; i++){
                        list.add(ag.names().getString(i));
                    }
                    String[] arr = list.toArray(new String[list.size()]);
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>
                            (
                                    TinhActivity.this,
                                    android.R.layout.simple_spinner_item,
                                    arr
                            );
                    //phải gọi lệnh này để hiển thị danh sách cho Spinner
                    adapter.setDropDownViewResource
                            (android.R.layout.simple_list_item_single_choice);
                    //Thiết lập adapter cho Spinner
                    spinner1.setAdapter(adapter);
                    if(spinner1.getCount()>flag)
                        spinner1.setSelection(flag);
                    else{
                        flag=0;
                        spinner1.setSelection(flag);
                    }

                }
                else if(spinner.getSelectedItem().toString()=="BP"){
                    JSONObject ag=jsonObj.getJSONObject("BP");
                    int a=ag.length();
                    List<String> list = new ArrayList<String>();
                    for(int i = 0; i < a; i++){
                        list.add(ag.names().getString(i));
                    }
                    String[] arr = list.toArray(new String[list.size()]);
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>
                            (
                                    TinhActivity.this,
                                    android.R.layout.simple_spinner_item,
                                    arr
                            );
                    //phải gọi lệnh này để hiển thị danh sách cho Spinner
                    adapter.setDropDownViewResource
                            (android.R.layout.simple_list_item_single_choice);
                    //Thiết lập adapter cho Spinner
                    spinner1.setAdapter(adapter);

                    if(spinner1.getCount()>flag)
                        spinner1.setSelection(flag);
                    else{
                        flag=0;
                        spinner1.setSelection(flag);
                    }

                }
                else if(spinner.getSelectedItem().toString()=="BTH"){
                    JSONObject ag=jsonObj.getJSONObject("BTH");
                    int a=ag.length();
                    List<String> list = new ArrayList<String>();
                    for(int i = 0; i < a; i++){
                        list.add(ag.names().getString(i));
                    }
                    String[] arr = list.toArray(new String[list.size()]);
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>
                            (
                                    TinhActivity.this,
                                    android.R.layout.simple_spinner_item,
                                    arr
                            );
                    //phải gọi lệnh này để hiển thị danh sách cho Spinner
                    adapter.setDropDownViewResource
                            (android.R.layout.simple_list_item_single_choice);
                    //Thiết lập adapter cho Spinner
                    spinner1.setAdapter(adapter);
                    if(spinner1.getCount()>flag)
                        spinner1.setSelection(flag);
                    else{
                        flag=0;
                        spinner1.setSelection(flag);
                    }


                }
                else if(spinner.getSelectedItem().toString()=="CM"){
                    JSONObject ag=jsonObj.getJSONObject("CM");
                    int a=ag.length();
                    List<String> list = new ArrayList<String>();
                    for(int i = 0; i < a; i++){
                        list.add(ag.names().getString(i));
                    }
                    String[] arr = list.toArray(new String[list.size()]);
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>
                            (
                                    TinhActivity.this,
                                    android.R.layout.simple_spinner_item,
                                    arr
                            );
                    //phải gọi lệnh này để hiển thị danh sách cho Spinner
                    adapter.setDropDownViewResource
                            (android.R.layout.simple_list_item_single_choice);
                    //Thiết lập adapter cho Spinner
                    spinner1.setAdapter(adapter);
                    if(spinner1.getCount()>flag)
                        spinner1.setSelection(flag);
                    else{
                        flag=0;
                        spinner1.setSelection(flag);
                    }

                }
                else if(spinner.getSelectedItem().toString()=="CT"){
                    JSONObject ag=jsonObj.getJSONObject("CT");
                    int a=ag.length();
                    List<String> list = new ArrayList<String>();
                    for(int i = 0; i < a; i++){
                        list.add(ag.names().getString(i));
                    }
                    String[] arr = list.toArray(new String[list.size()]);
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>
                            (
                                    TinhActivity.this,
                                    android.R.layout.simple_spinner_item,
                                    arr
                            );
                    //phải gọi lệnh này để hiển thị danh sách cho Spinner
                    adapter.setDropDownViewResource
                            (android.R.layout.simple_list_item_single_choice);
                    //Thiết lập adapter cho Spinner
                    spinner1.setAdapter(adapter);
                    if(spinner1.getCount()>flag)
                        spinner1.setSelection(flag);
                    else{
                        flag=0;
                        spinner1.setSelection(flag);
                    }

                }
                else if(spinner.getSelectedItem().toString()=="HCM"){
                    JSONObject ag=jsonObj.getJSONObject("HCM");
                    int a=ag.length();
                    List<String> list = new ArrayList<String>();
                    for(int i = 0; i < a; i++){
                        list.add(ag.names().getString(i));
                    }
                    String[] arr = list.toArray(new String[list.size()]);
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>
                            (
                                    TinhActivity.this,
                                    android.R.layout.simple_spinner_item,
                                    arr
                            );
                    //phải gọi lệnh này để hiển thị danh sách cho Spinner
                    adapter.setDropDownViewResource
                            (android.R.layout.simple_list_item_single_choice);
                    //Thiết lập adapter cho Spinner
                    spinner1.setAdapter(adapter);
                    if(spinner1.getCount()>flag)
                        spinner1.setSelection(flag);
                    else{
                        flag=0;

                        spinner1.setSelection(flag);
                    }
                    ///


                    ///

                }
                String giai="";
                for(int i=1;i<=9;i++){
                    giai="";
                    if(i==9){
                        giai = giai + " " + jsonObj.getJSONObject(spinner.getSelectedItem().toString()).getJSONObject(spinner1.getSelectedItem().toString()).getJSONArray("DB").get(0).toString();
                    }
                    else {
                        for (int j = 0; j < jsonObj.getJSONObject(spinner.getSelectedItem().toString()).getJSONObject(spinner1.getSelectedItem().toString()).getJSONArray(String.valueOf(i)).length(); j++) {
                            giai = giai + " " + jsonObj.getJSONObject(spinner.getSelectedItem().toString()).getJSONObject(spinner1.getSelectedItem().toString()).getJSONArray(String.valueOf(i)).get(j).toString();
                        }
                    }
                    if(i==1)
                        text1.setText(giai);
                    else if(i==2)
                        text2.setText(giai);
                    else if(i==3)
                        text3.setText(giai);
                    else if(i==4)
                        text4.setText(giai);
                    else if(i==5)
                        text5.setText(giai);
                    else if(i==6)
                        text6.setText(giai);
                    else if(i==7)
                        text7.setText(giai);
                    else if(i==8)
                        text8.setText(giai);
                    else if(i==9)
                        textdb.setText(giai);

                }


                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int b, long l) {
                        flag=spinner1.getSelectedItemPosition();
                        // Your code here
                        onResume();
                    }


                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });


            } catch (JSONException e) {

                e.printStackTrace();
            }

        }
        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
        }
    }
}
