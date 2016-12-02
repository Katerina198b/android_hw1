package ru.fopf_print.tehnothackhw1.auth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.fopf_print.tehnothackhw1.R;


public class AuthActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_auth_main);
            LoginFragment fragment = (LoginFragment) getSupportFragmentManager()
               .findFragmentById(R.id.container);
            if (fragment == null) {
                fragment = new LoginFragment();
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment).commit();
    }

}
