package martin.searchengines;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class chooseMenu extends ActionBarActivity {

    ListView engineList;

    private String[] engines = {"Google", "Bing", "Yahoo!", "MSN", "ask.com"};
//    String[] url = getResources().getStringArray(R.array.theUrls);

//    WebView webview = (WebView)findViewById(R.id.webid);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);

        //weird, request window feature doens't work
//        supportRequestWindowFeature(Window.FEATURE_PROGRESS);

        setContentView(R.layout.activity_choose_menu);


        engineList = (ListView)findViewById(R.id.searchEngineList);
        engineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = getApplicationContext().getResources().getStringArray(R.array.theUrls)[position];
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setData(Uri.parse(url));
                getApplicationContext().startActivity(i);

            }
        });
        engineList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        new MyTask().execute();





    }
    class MyTask extends AsyncTask<Void, String, Void>{

        ArrayAdapter<String> adapter;
        private int count = 0;
        @Override
        protected Void doInBackground(Void... params) {
            for(String item: engines)
            {
                publishProgress(item);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }


        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            count++;
            setProgress((int)(((double)count/engines.length)*10000));

        }

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) engineList.getAdapter();
            setProgressBarIndeterminate(false);
            setProgressBarVisibility(true);

        }
        @Override
        protected void onPostExecute(Void result) {

            setProgressBarVisibility(false);
            //make a message whendone
            Toast.makeText(chooseMenu.this, "Done", Toast.LENGTH_SHORT).show();
        }
    }



}
