package com.daavm.nextbit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pushbots.push.Pushbots;

import org.w3c.dom.Text;

/**
 * Created by David on 14/06/2016.
 */

public class LoginPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ////////////////
        //Choose Theme//
        ////////////////
        boolean midnightTheme = preferences.getBoolean("midnightTheme", false);
        boolean mintTheme = preferences.getBoolean("mintTheme", false);
        boolean electricTheme = preferences.getBoolean("electricTheme", false);

        if (midnightTheme) {
            setTheme(R.style.Midnight);
        } else if (mintTheme) {
            setTheme(R.style.Mint);
        } else if (electricTheme) {
            setTheme(R.style.Electric);
        } else {
            setTheme(R.style.Electric);
        }
        final String loged = preferences.getString("loged", "no");
        String nope = preferences.getString("nope", "yes");
        super.onCreate(savedInstanceState);
        if(loged.equals("no")){
        }else{
            Intent logedBefore = new Intent(getApplicationContext(), MainActivity.class);
            logedBefore.putExtra("password", "");
            logedBefore.putExtra("user", "");
            startActivity(logedBefore);
        }
        Pushbots.sharedInstance().init(this);
        setContentView(R.layout.activity_main_login);
        String username2 = preferences.getString("usernameWV", "hello");
        String pass2 = preferences.getString("passwordWV", "hello");
        if(preferences.getString("usernameWV", "hello") != "hello") {
            if(preferences.getString("passwordWV", "hello") != "hello") {
                EditText user = (EditText)findViewById(R.id.editText);
                EditText pass = (EditText)findViewById(R.id.editText2);
                user.setText(username2, TextView.BufferType.EDITABLE);
                pass.setText(pass2, TextView.BufferType.EDITABLE);
                Button seguir = (Button) findViewById(R.id.button);
                seguir.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                Intent logedBefore = new Intent(getApplicationContext(), Login.class);
                logedBefore.putExtra("user", preferences.getString("usernameWV", "hello"));
                logedBefore.putExtra("password", preferences.getString("passwordWV", "hello"));
                startActivity(logedBefore);
                                              }
                });
            }
        }
        Button seguir = (Button) findViewById(R.id.button);
        seguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                EditText user = (EditText)findViewById(R.id.editText);
                EditText pass = (EditText)findViewById(R.id.editText2);
                String username = user.getText().toString();
                String password = pass.getText().toString();
                editor.putString("usernameWV", username).commit();
                editor.putString("passwordWV", password).commit();
                Intent logedBefore = new Intent(getApplicationContext(), Login.class);
                logedBefore.putExtra("user", username);
                logedBefore.putExtra("password", password);
                startActivity(logedBefore);
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setVerticalScrollBarEnabled(false);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.NavigationView);
        navigationView.setScrollBarSize(0);
        navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.about) {
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.loadUrl("https://community.nextbit.com/t5/General-Q-A/Nextbit-Forum-App-NextForum/m-p/16977");
        }
        else if (id == R.id.india) {
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.loadUrl("https://community.nextbit.com/t5/India/bd-p/India");
        }
        else  if (id == R.id.donate) {
            Intent intent2 = new Intent(this,donationsScreen.class);
            startActivity(intent2);
        }
        else if (id == R.id.store) {
            Intent intent2 = new Intent(this,store.class);
            startActivity(intent2);
        } else if (id == R.id.nav_wiki) {
            Intent intent = new Intent(this,wiki.class);
            startActivity(intent);
        } else if (id == R.id.nav_discover) {
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.loadUrl("https://www.nextbit.com/pages/meet-robin");
        } else if (id == R.id.nav_themes) {
            Intent intent2 = new Intent(this,Preferences.class);
            startActivity(intent2);
        } else if (id == R.id.Community) {
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.loadUrl("https://community.nextbit.com");
        }  else if (id == R.id.messages) {
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.loadUrl("https://community.nextbit.com/t5/notes/privatenotespage");
        }  else if (id == R.id.notifications) {
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.loadUrl("https://community.nextbit.com/t5/notificationfeed/page");
        }  else if (id == R.id.signin) {
            Intent intent = new Intent(this,LoginPage.class);
            startActivity(intent);
        }   else if (id == R.id.forumsettings) {
            WebView myWebView = (WebView) this.findViewById(R.id.webView);
            myWebView.loadUrl("https://community.nextbit.com/t5/user/myprofilepage/tab/personal-profile");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
