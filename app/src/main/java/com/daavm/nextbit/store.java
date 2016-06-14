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

public class store extends AppCompatActivity
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

        final Activity activity = this;
        super.onCreate(savedInstanceState);
        Pushbots.sharedInstance().init(this);
        setContentView(R.layout.activity_store);

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
    //WIKI PART
    public void FAQ(View view)
    {
        setContentView(R.layout.activity_wiki_faq);
    }
    public void Hardware(View view)
    {
        setContentView(R.layout.activity_wiki_faq_hardware);

    }
    public void home(View view)
    {
        setContentView(R.layout.activity_wiki);

    }
    public void AboutN(View view)
    {
        setContentView(R.layout.activity_wiki_about);

    }
    public void history(View view)
    {
        setContentView(R.layout.activity_wiki_history);

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.about) {
            Intent intent2 = new Intent(store.this,appthread.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }
        if (id == R.id.india) {
            Intent intent2 = new Intent(store.this,India.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }
        if (id == R.id.store) {
            Intent intent2 = new Intent(store.this,store.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        } else if (id == R.id.nav_wiki) {
            Intent intent2 = new Intent(store.this,wiki.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        } else if (id == R.id.nav_discover) {
            Intent intent2 = new Intent(store.this,Discover.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        } else if (id == R.id.nav_themes) {
            Intent intent2 = new Intent(store.this,Preferences.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        } else if (id == R.id.donate) {
            Intent intent2 = new Intent(this,donationsScreen.class);
            startActivity(intent2);
        }else if (id == R.id.Community) {
            Intent intent2 = new Intent(store.this,MainActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }  else if (id == R.id.messages) {
            Intent intent2 = new Intent(store.this,Messages.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }  else if (id == R.id.notifications) {
            Intent intent2 = new Intent(store.this,Notifications.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }  else if (id == R.id.signin) {
            Intent intent2 = new Intent(store.this,Login.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }   else if (id == R.id.forumsettings) {
            Intent intent2 = new Intent(store.this,forumsettings.class);
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
