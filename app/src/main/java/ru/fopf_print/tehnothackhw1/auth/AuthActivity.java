package ru.fopf_print.tehnothackhw1.auth;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import ru.fopf_print.tehnothackhw1.R;


public class AuthActivity extends AppCompatActivity{
        @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_auth_main);
            LoginFragment fragment = new LoginFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment).commit();
    }

}
