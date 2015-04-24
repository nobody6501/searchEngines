package martin.searchengines;

//import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;


public class MainActivity extends ActionBarActivity {



    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        long seconds = 3;


        Button clickMeButton = (Button) findViewById(R.id.welcomeButton);
        final TextView welcomeText = (TextView) findViewById(R.id.welcomeText);


        clickMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(MainActivity.this,chooseMenu.class);
                        startActivity(i);

                    }
                };
                worker.schedule(runnable,1, TimeUnit.SECONDS);


                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/coolFont.TTF");
                welcomeText.setText("Welcome !");
                welcomeText.setTextSize(60);
                welcomeText.setTypeface(font);
                welcomeText.setGravity(Gravity.CENTER);
                welcomeText.startAnimation(AnimationUtils.loadAnimation(MainActivity.this
                        , android.R.anim.fade_in));


            }


        });
//        Thread.sleep(seconds);


    }


}
