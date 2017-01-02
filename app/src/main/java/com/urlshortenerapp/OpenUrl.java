package com.urlshortenerapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class OpenUrl extends Activity {

    WebView web;
    ProgressDialog pd;
    String shor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_url);

        shor = getIntent().getStringExtra("shortURL");

        web = (WebView) findViewById(R.id.web);
        web = new WebView(this);
        web.getSettings().setJavaScriptEnabled(true);


        web.setWebChromeClient(new WebChromeClient());
        setContentView(web);

        pd = new ProgressDialog(OpenUrl.this);
        pd.setMessage("Please wait Loading...");
        pd.show();

        web.setWebViewClient(new myWebClient());
        web.loadUrl(shor);

    }

    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            if (!pd.isShowing()) {
                pd.show();
            }

            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub

            if (pd.isShowing()) {
                pd.dismiss();
            }

            super.onPageFinished(view, url);
            /*Intent i = new Intent(browse.this,first.class);
            startActivity(i);*/


        }
    }

    //flip screen not loading again
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (web.canGoBack()) {
                        web.goBack();
                    } else {
                        onBackPressed();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }


}