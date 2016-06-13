package com.daavm.nextbit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebView.FindListener;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.pushbots.push.Pushbots;

public class donations extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public void amazon(View view)
    {
        Uri uri = Uri.parse("http://www.amazon.com/Nextbit-Robin-Factory-Unlocked-Phone/dp/B01D9LVCAI/ref=sr_1_2?ie=UTF8&qid=1460665574&sr=8-2&keywords=nextbit+robin"); // missing 'http://' will cause crash
        Intent intent5 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent5);
    }
    public void official(View view)
    {
        Uri uri = Uri.parse("https://www.nextbit.com/collections/all"); // missing 'http://' will cause crash
        Intent intent5 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent5);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean midnightTheme = preferences.getBoolean("midnightTheme", false);
        boolean mintTheme = preferences.getBoolean("mintTheme", false);
        boolean electricTheme = preferences.getBoolean("electricTheme", false);

        if(midnightTheme) {
            setTheme(R.style.Midnight);
        }
        else if(mintTheme) {
            setTheme(R.style.Mint);
        }
        else if(electricTheme) {
            setTheme(R.style.Electric);
        }else {
            setTheme(R.style.Electric);
        }

        super.onCreate(savedInstanceState);
        Pushbots.sharedInstance().init(this);
        setContentView(R.layout.activity_donations);
        String summary = "<center><form action=\"https://www.paypal.com/cgi-bin/webscr\" method=\"post\" target=\"_top\">\n" +
                "<input type=\"hidden\" name=\"cmd\" value=\"_s-xclick\">\n" +
                "<input type=\"hidden\" name=\"hosted_button_id\" value=\"5A8YR2DYA79AS\">\n" +
                "<input type=\"image\" src=\"https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif\" border=\"0\" name=\"submit\" alt=\"PayPal - The safer, easier way to pay online!\">\n" +
                "<img alt=\"\" border=\"0\" src=\"https://www.paypalobjects.com/es_ES/i/scr/pixel.gif\" width=\"1\" height=\"1\">\n" +
                "</form></center>\n";

        WebView webhtml = (WebView) findViewById(R.id.webhtml);
        String newUA = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
        webhtml.getSettings().setUserAgentString(newUA);
        TextView yourView = (TextView) findViewById(R.id.textView23) ;
        webhtml.loadData(summary, "text/html", null);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toolbar.setTitle("Store");
        NavigationView navigationView = (NavigationView) findViewById(R.id.NavigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.about) {
            Intent intent2 = new Intent(this,appthread.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }
        else  if (id == R.id.india) {
            Intent intent2 = new Intent(this,India.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        } else if (id == R.id.store) {
            Intent intent2 = new Intent(this,store.class);
            startActivity(intent2);
        } else if (id == R.id.store) {
            Intent intent2 = new Intent(this,store.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        } else if (id == R.id.nav_wiki) {
            Intent intent2 = new Intent(this,wiki.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        } else if (id == R.id.nav_discover) {
            Intent intent2 = new Intent(this,Discover.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        } else if (id == R.id.nav_themes) {
            Intent intent2 = new Intent(this,Preferences.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        } else if (id == R.id.Community) {
            Intent intent2 = new Intent(this,MainActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }  else if (id == R.id.messages) {
            Intent intent2 = new Intent(this,Messages.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }  else if (id == R.id.notifications) {
            Intent intent2 = new Intent(this,Notifications.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }  else if (id == R.id.signin) {
            Intent intent2 = new Intent(this,Login.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }   else if (id == R.id.forumsettings) {
            Intent intent2 = new Intent(this,forumsettings.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }else {
            setTheme(R.style.Electric);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }
}