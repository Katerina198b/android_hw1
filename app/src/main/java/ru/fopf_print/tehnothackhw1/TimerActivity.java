package ru.fopf_print.tehnothackhw1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;


public class TimerActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    Integer count = 0;
    AtomicBoolean isStart = new AtomicBoolean(true);
    private static Logger log = Logger.getLogger(TimerActivity.class.getName());

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
    }


    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("count");

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        log.info("activity_timer created");
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(new MyAwesomeClickListener());
    }


    class MyAwesomeClickListener implements View.OnClickListener {
        public void onClick(View v) {
            log.info("Button is clicked");
            if (isStart.compareAndSet(true, false)) {
                Timer timer = new Timer();
                timer.execute();
            };
        }
    }

    private class Timer extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 1; i <= 1000; i++) {
                final int finalI = i;
                Runnable updateProgress = new Runnable() {
                    public void run() {
                        onProgressUpdate(finalI);
                    }
                };
                updateProgress.run();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... count) {
            textView.setText(count[0].toString());
        }

    }
}
