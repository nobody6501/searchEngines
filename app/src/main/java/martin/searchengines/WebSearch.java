package martin.searchengines;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class WebSearch extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_search);

        Bundle bundle = getIntent().getExtras();
        String link = bundle.getString("Link",null);

        WebView webview = (WebView)findViewById(R.id.webView);
        webview.setClickable(false);
        webview.loadUrl(link);
    }


}
