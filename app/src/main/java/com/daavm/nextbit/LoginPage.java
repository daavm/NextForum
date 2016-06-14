package com.daavm.nextbit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.pushbots.push.Pushbots;

/**
 * Created by David on 14/06/2016.
 */

public class LoginPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranOnce = preferences.getBoolean("RanBefore", true);


        super.onCreate(savedInstanceState);
        Pushbots.sharedInstance().init(this);
        setContentView(R.layout.activity_main);

        final WebView myWebView = (WebView) this.findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        myWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setAllowFileAccess(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1");
        if (ranOnce) {
            String user = "david";
            String pass = "ruben22798";
            myWebView.loadUrl("https://community.nextbit.com/t5/user/userloginpage?dest_url=https%3A%2F%2Fcommunity.nextbit.com%2F");
            final String js = "javascript:document.getElementById('lia-login').value = '" + user + "';document.getElementById('lia-password').value='" + pass + "';";
            myWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                    myWebView.loadUrl("javascript:document.getElementById('form').submit();");
                    Intent in = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(in);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("RanBefore", false);
                    editor.commit();
                }
            });
        }
    }
}
