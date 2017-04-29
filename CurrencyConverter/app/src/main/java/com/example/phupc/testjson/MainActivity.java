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

public class MainActivity extends AppCompatActivity {

    //    EditText txtLink, txtTemp, txtCloud, txtMaxtemp,txtMintemp, txtWind;
//    Button btnDownloadInfor;
    ListView lv;
     /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //gọi addControl trước
        addControls();
        //gọi addevent sau
        addEvents();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, RatesExchangeActivity.class);
             intent.putExtra(EXTRA_MESSAGE, "");
        startActivity(intent);
    }


    public void addControls() {

        lv = (ListView) findViewById(R.id.listview);
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMessage(v);
            }
        });
    }


    public void addEvents() {
        processDownload();

    }


    private void processDownload() {
        DownloadTask task = new DownloadTask();
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
            double USDVND = listRate.get(34).getRate();
            //
            double USDEUR = listRate.get(0).getRate();
            double USDJPY = listRate.get(1).getRate();
            double USDGBP = listRate.get(5).getRate();
            //
            double EURVND = BigDecimal.valueOf(USDVND / USDEUR).setScale(1, RoundingMode.HALF_UP).doubleValue();
            double GBPVND = BigDecimal.valueOf(USDVND / USDGBP).setScale(1, RoundingMode.HALF_UP).doubleValue();
            double JPYVND = BigDecimal.valueOf(USDVND / USDJPY).setScale(1, RoundingMode.HALF_UP).doubleValue();
            final String arr[] = {"USD\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + String.valueOf(USDVND),
                    "EUR\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + String.valueOf(EURVND),
                    "GBP\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + String.valueOf(GBPVND),
                    "JPY\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + String.valueOf(JPYVND)};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (MainActivity.this, android.R.layout.simple_list_item_1, arr);
            lv.setAdapter(adapter);


        }

//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_LONG).show();
//        }
    }
}
//    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.menu_main, menu);
////        return true;
////    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}