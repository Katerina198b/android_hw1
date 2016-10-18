package ru.fopf_print.tehnotrack_hw1;

import android.os.AsyncTask;
import android.os.Handler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TimerActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn = (Button)findViewById(R.id.button);
    TextView textView = (TextView)findViewById(R.id.textView);
    final Integer count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
                btn.setText(R.string.button_text_2);

                Thread(() -> {
                        for (int i = 0; i < 1000;  i++) {
                            try {
                                Thread.sleep(1000);
                                runOnUiThread(() -> {
                                    textView.setText(count.toString());
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        }).start();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    void updateOutText() {
        _tv.setText(_outBuffer.toString());
        _scroller.fullScroll(View.FOCUS_DOWN);
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            _asyncTaskCount++;
            _outBuffer.append("Task onPreExecute ");
            _outBuffer.append(_asyncTaskCount);
            _outBuffer.append("\n");
        }


        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            _outBuffer.append("Task doInBackground ");
            _outBuffer.append(_asyncTaskCount);
            _outBuffer.append("\n");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            _outBuffer.append("Task onPostExecute ");
            _outBuffer.append(_asyncTaskCount);
            _outBuffer.append("\n");
        }
    }
}

}
