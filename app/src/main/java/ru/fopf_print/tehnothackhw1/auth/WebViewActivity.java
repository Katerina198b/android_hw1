package ru.fopf_print.tehnothackhw1.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import ru.fopf_print.tehnothackhw1.R;

import static ru.fopf_print.tehnothackhw1.util.StreamReader.getResponseString;


public class WebViewActivity extends AppCompatActivity implements AuthorizationListener {
    private static Logger logger = Logger.getLogger(WebViewActivity.class.getName());
    public static String ACCESS_TOKEN = "access_token";
    public static final String AUTH_ERROR = "error";
    public static String token;

    private String authUrlTemplate;
    private String tokenUrlTemplate;
    private String redirectUrl;

    private WebView webView;

    public static Intent createAuthActivityIntent(Context context, String clientId, String secret) {
        Intent intent = new Intent(context, WebViewActivity.class);
        //intent.putExtra(EXTRA_CLIENT_ID, clientId);
        //intent.putExtra(EXTRA_CLIENT_SECRET, secret);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("WEB VIEW onCreate");

        setContentView(R.layout.web_view_activity);
        webView = (WebView) findViewById(R.id.web_view);

        //clientId = getIntent().getStringExtra(EXTRA_CLIENT_ID);
        //clientSecret = getIntent().getStringExtra(EXTRA_CLIENT_SECRET);

        authUrlTemplate = getString(R.string.implicit_auth_url);
        //tokenUrlTemplate = getString(R.string.implicit_token_url);
        redirectUrl = getString(R.string.callback_implicit_url);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String url = String.format(authUrlTemplate, "&", redirectUrl, "&", getString(R.string.client_id), "&");
        URI uri = URI.create(url);
        webView.setWebViewClient(new OAuthWebClient(this));
        webView.loadUrl(uri.toString());
    }


    @Override
    public void onComplete(String token) {
        Intent intent = new Intent();
        intent.putExtra(ACCESS_TOKEN, token);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onError(String error) {
        Intent intent = new Intent();
        intent.putExtra(AUTH_ERROR, error);
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }


    private class OAuthWebClient extends WebViewClient {
        private AuthorizationListener listener;

        OAuthWebClient(AuthorizationListener listener) {
            this.listener = listener;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (url.startsWith(view.getResources().getString(R.string.callback_implicit_url))) {
                String[] urls = url.split("#");

                String token = null;
                if (urls.length > 1) {
                    try {
                        token = makeTokenFromUrl(urls[1]);
                    } catch (IOException e) {
                        listener.onError(e.getMessage());
                    }
                } else {
                    listener.onError("Canceled!");
                }

                //new AccessTokenGetter(listener).execute(token);
                onComplete(token);
            }
            super.onPageStarted(view, url, favicon);
        }


        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            listener.onError(error.toString());
        }
    }

    protected static String makeTokenFromUrl(String str) throws IOException {
        try {
            for (String param : str.split("&")) {
                String[] valuePair = param.split("=");
                if (valuePair[0].equals("access_token")) {
                    if (valuePair.length > 1) {

                        return valuePair[1];
                        //onComplete(valuePair[1]);
                    }
                }
            }
            throw new IOException("Not found");
        } catch (Exception error) {
            throw new IOException(
                    String.format("Couldn't make token from that URL! %s" +
                            " with error %s", str, error.getMessage()));
        }
    }
//
//    private class AccessTokenGetter extends AsyncTask<String, Void, String> {
//        private AuthorizationListener listener;
//
//        AccessTokenGetter(AuthorizationListener listener) {
//            this.listener = listener;
//        }
//
//        private String getAccessToken(String response) {
//            String[] params = response.split("&");
//            return params[0].split("=")[1];
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            String url = String.format(tokenUrlTemplate,
//                    "&", redirectUrl, "&", getString(R.string.client_id), "&", "&", params[0]);
//            try {
//                URL u = new URL(url);
//                logger.log(Level.WARNING, url);
//                System.out.println(url);
//                HttpURLConnection connection = (HttpURLConnection) u.openConnection();
//                connection.setRequestMethod("POST");
//                connection.setDoInput(true);
//                connection.setDoOutput(true);
//                connection.connect();
//                int status = connection.getResponseCode();
//                if (status != 200) {
//                    return "Error with status " + status;
//                } else {
//                    String response = getResponseString(connection.getInputStream());
//                    System.out.println(response);
//                    return getAccessToken(response);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (params.length == 0) {
//                return null;
//            }
//
////            for (String param : params[0].split("&")) {
////                String[] valuePair = param.split("=");
////                if (valuePair[0].equals("access_token")) {
////                    if (valuePair.length > 1) {
////                        return valuePair[1];
////                    } else {
////                        return "";
////                    }
////                }
////            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String token) {
//            if (token.contains("Error")) {
//                listener.onError(token);
//            } else {
//                listener.onComplete(token);
//            }
//        }
//    }

}