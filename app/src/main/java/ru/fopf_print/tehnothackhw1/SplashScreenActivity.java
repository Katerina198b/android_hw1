/**package ru.fopf_print.tehnothackhw1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends Activity {


    @Override
    public Object onRetainNonConfigurationInstance() {
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = null;
                //intent = (Intent) getLastNonConfigurationInstance();
                if (intent == null) {
                    intent = new Intent(SplashScreenActivity.this, TimerActivity.class);
                }
                startActivity(intent);

            }
        }).start();
    }

}
*/

package ru.fopf_print.tehnothackhw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.fopf_print.tehnothackhw1.auth.AuthActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_activity_main);
        System.out.println("Hello World!");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SplashScreenActivity.this, AuthActivity.class);
                startActivity(intent);
                //finish();
            }
        }).start();

    }
}

