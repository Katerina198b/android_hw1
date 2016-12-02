package ru.fopf_print.tehnothackhw1.auth;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import ru.fopf_print.tehnothackhw1.R;

public class LoginFragment extends Fragment {
    private static final int REQUEST_CODE = 100;
    private String accessToken;
    private static final String TOKEN = "token";

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TOKEN, accessToken);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment, container, false);

        if (savedInstanceState != null) {
            accessToken = savedInstanceState.getString(TOKEN);
        }

        Button login = (Button) v.findViewById(R.id.login_button);
        Button showToken = (Button) v.findViewById(R.id.show_token);

        LoginClickListener loginClickListener = new LoginClickListener();
        login.setOnClickListener(loginClickListener);

        ShowTokenListener listener = new ShowTokenListener();
        showToken.setOnClickListener(listener);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String token = data.getStringExtra(WebViewActivity.ACCESS_TOKEN);
                Toast.makeText(getContext(), token, Toast.LENGTH_SHORT).show();
                accessToken = token;
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                if (data != null)
                    if (data.hasExtra(WebViewActivity.AUTH_ERROR)) {
                        String error = data.getStringExtra(WebViewActivity.AUTH_ERROR);
                        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                    }
            }
        }
    }

    private class LoginClickListener implements View.OnClickListener {
        public void onClick(View v) {
            Resources resources = v.getResources();
            String clientId = resources.getString(R.string.client_id);
            String secret = resources.getString(R.string.client_secret);
            Intent intent = WebViewActivity.createAuthActivityIntent(v.getContext(), clientId, secret);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    private class ShowTokenListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), String.format("AccessToken is %s", accessToken), Toast.LENGTH_SHORT).show();
        }
    }
}