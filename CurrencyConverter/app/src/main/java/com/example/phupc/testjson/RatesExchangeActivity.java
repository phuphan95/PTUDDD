package com.example.phupc.testjson;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import pac1.jsonclass.Main;
import pac1.jsonclass.Rate;
import java.math.RoundingMode;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RatesExchangeActivity extends AppCompatActivity {
    Spinner spin;
    Spinner spin2;
    EditText edittext;
    TextView textview;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates_exchange);
        //gọi addControl trước
        addControls();
        //gọi addevent sau
        addEvents();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }




    public void addControls() {
        String arr[]={
                "VND",
                "USD",
                "EUR",
        "GBP","JPY"
        };
         textview=(TextView) findViewById(R.id.textView4) ;
       edittext=(EditText)findViewById(R.id.editText);
         spin=(Spinner) findViewById(R.id.spinner);
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
        spin.setAdapter(adapter);
        //
        //
        spin2=(Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arr
                );
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter2.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spin2.setAdapter(adapter2);



    }


    public void addEvents() {
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                processDownload();
            }
        });

    }


    private void processDownload() {
        RatesExchangeActivity.DownloadTask task = new RatesExchangeActivity.DownloadTask();
        task.execute("http://query.yahooapis.com/v1/public/yql?format=json&q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22USDEUR%22%2C%20%22USDJPY%22%2C%20%22USDBGN%22%2C%20%22USDCZK%22%2C%20%22USDDKK%22%2C%20%22USDGBP%22%2C%20%22USDHUF%22%2C%20%22USDLTL%22%2C%20%22USDLVL%22%2C%20%22USDPLN%22%2C%20%22USDRON%22%2C%20%22USDSEK%22%2C%20%22USDCHF%22%2C%20%22USDNOK%22%2C%20%22USDHRK%22%2C%20%22USDRUB%22%2C%20%22USDTRY%22%2C%20%22USDAUD%22%2C%20%22USDBRL%22%2C%20%22USDCAD%22%2C%20%22USDCNY%22%2C%20%22USDHKD%22%2C%20%22USDIDR%22%2C%20%22USDILS%22%2C%20%22USDINR%22%2C%20%22USDKRW%22%2C%20%22USDMXN%22%2C%20%22USDMYR%22%2C%20%22USDNZD%22%2C%20%22USDPHP%22%2C%20%22USDSGD%22%2C%20%22USDTHB%22%2C%20%22USDZAR%22%2C%20%22USDISK%22%2C%20%22USDVND%22)&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private class DownloadTask extends AsyncTask<String, Main, Void> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_LONG).show();
//        }

        @Override
        protected Void doInBackground(String... params) {
            //Lấy data Json từ hàm processDownload truyền vào
            String link = params[0];
            try {
                URL url = new URL(link);
//              //đọc stream Json từ internet có đọc UTF8
                InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");
                //chuyển định dạng JSon về java class
                Main main = new Gson().fromJson(reader, Main.class);
                //gửi qua onProgressUpdate để cập nhật giao diện
                publishProgress(main);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Main... values) {
            super.onProgressUpdate(values);
            //lấy Weather được truyền từ doInBackground
            Main main = values[0];
            List<Rate> listRate = main.getQuery().getResults().getRate();
            String currentmn=edittext.getText().toString();
            double currentmoney=Double.parseDouble(currentmn);
            double USDVND = listRate.get(34).getRate();//1
            //
            double USDEUR = listRate.get(0).getRate();//2
            double USDJPY = listRate.get(1).getRate();//3
            double USDGBP = listRate.get(5).getRate();//4
            String money1 = spin.getSelectedItem().toString();
            String money2=spin2.getSelectedItem().toString();
            if(money2.equals(money1)) {
                textview.setText(currentmn);
            }
            else if(money1.equals("USD")){
                if(money2.equals("USD"))
                    textview.setText(currentmn);
                else if(money2.equals("VND"))
                    textview.setText(String.valueOf(Cal1(currentmoney,USDVND)));
                else if(money2.equals("EUR"))
                    textview.setText(String.valueOf(Cal1(currentmoney,USDEUR)));
                else if(money2.equals("JPY"))
                    textview.setText(String.valueOf(Cal1(currentmoney,USDJPY)));
                else if(money2.equals("GBP"))
                    textview.setText(String.valueOf(Cal1(currentmoney,USDGBP)));

            }
            else if(money2.equals("USD")){
                if(money1.equals("USD"))
                    textview.setText(currentmn);
                else if(money1.equals("VND"))
                    textview.setText(String.valueOf(Cal2(currentmoney,USDVND)));
                else if(money1.equals("EUR"))
                    textview.setText(String.valueOf(Cal2(currentmoney,USDEUR)));
                else if(money1.equals("JPY"))
                    textview.setText(String.valueOf(Cal2(currentmoney,USDJPY)));
                else if(money1.equals("GBP"))
                    textview.setText(String.valueOf(Cal2(currentmoney,USDGBP)));
            }
            else {
                if(money1.equals("VND")){
                    double rate=0;
                    if(money2.equals("EUR")){
                         rate=USDEUR/USDVND;
                    }
                    else if(money2.equals("JPY")){
                         rate=USDJPY/USDVND;
                    }
                    else if(money2.equals("GBP")){
                         rate=USDGBP/USDVND;
                    }
                    textview.setText(String.valueOf(Cal1(currentmoney,rate)));
                }
                else if(money1.equals("EUR")){
                    double rate=0;
                    if(money2.equals("VND")){
                        rate=USDVND/USDEUR;
                    }
                    else if(money2.equals("JPY")){
                        rate=USDJPY/USDEUR;
                    }
                    else if(money2.equals("GBP")){
                        rate=USDGBP/USDEUR;
                    }
                    textview.setText(String.valueOf(Cal1(currentmoney,rate)));
                }
                else if(money1.equals("JPY")){
                    double rate=0;
                    if(money2.equals("VND")){
                        rate=USDVND/USDJPY;
                    }
                    else if(money2.equals("EUR")){
                        rate=USDEUR/USDJPY;
                    }
                    else if(money2.equals("GBP")){
                        rate=USDGBP/USDJPY;
                    }
                    textview.setText(String.valueOf(Cal1(currentmoney,rate)));
                }
                else if(money1.equals("GBP")){
                    double rate=0;
                    if(money2.equals("VND")){
                        rate=USDVND/USDGBP;
                    }
                    else if(money2.equals("EUR")){
                        rate=USDEUR/USDGBP;
                    }
                    else if(money2.equals("JPY")){
                        rate=USDJPY/USDGBP;
                    }
                    textview.setText(String.valueOf(Cal1(currentmoney,rate)));
                }


            }
         }
        protected double Cal1(double money,double rate){
            double JPYVND = BigDecimal.valueOf(money*rate).setScale(4, RoundingMode.HALF_UP).doubleValue();
            return JPYVND;
        }
        protected double Cal2(double money,double rate){
            double JPYVND = BigDecimal.valueOf(money/rate).setScale(4, RoundingMode.HALF_UP).doubleValue();
            return JPYVND;
        }
    }
}
