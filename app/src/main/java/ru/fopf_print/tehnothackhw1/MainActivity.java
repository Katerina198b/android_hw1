package ru.fopf_print.tehnothackhw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(MainActivity.this, TimerActivity.class);
        startActivity(intent);

    }
}