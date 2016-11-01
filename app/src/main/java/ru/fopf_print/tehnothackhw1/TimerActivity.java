package ru.fopf_print.tehnothackhw1;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;


public class TimerActivity extends Activity {

    Timer timer = null;
    Button button;
    static TextView textView;
    static Integer count = 0;
    static AtomicBoolean isStart = new AtomicBoolean(true);
    private static Logger log = Logger.getLogger(TimerActivity.class.getName());
    Resources resources = null;

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("Start", isStart.get());
        log.info(textView.getText().toString());
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        timer.unLink();
        return timer;
    }


    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isStart.set(savedInstanceState.getBoolean("Start"));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = this.getResources();
        setContentView(R.layout.activity_timer);
        log.info("activity_timer created");
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(new MyAwesomeClickListener());
        timer = (Timer) getLastNonConfigurationInstance();
        if (timer != null) {
            if (!isStart.get()){
                button.setText(R.string.button_text_2);
            }
            timer.link(this);
        }
    }


    class MyAwesomeClickListener implements View.OnClickListener {
        public void onClick(View v) {
            log.info("Button is clicked");
            // expect set
            if (isStart.compareAndSet(true, false)) {
                button.setText(R.string.button_text_2);
                timer = new Timer();
                timer.execute();
            } else {
                button.setText(R.string.button_text);
                isStart.set(true);
            }
        }
    }

    class Timer extends AsyncTask <Void, Integer, Void> {
        TimerActivity activity;

        void link(TimerActivity act) {
            activity = act;
        }

        void unLink() {
            activity = null;
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 1; i <= 1000; i++) {
                if (isStart.get()){
                    count = 0;
                    publishProgress(count);
                    return null;
                }
                count ++;
                publishProgress(count);
                SystemClock.sleep(1000);
            }
            isStart.set(true);
            textView.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText(R.string.text_finished);
                }
            });
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... count) {
            if (count[0] == 0) {
                textView.setText("");
            } else {

                textView.setText(count[0]);
            }
        }


    }
}
