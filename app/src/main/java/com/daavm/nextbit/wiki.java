package com.daavm.nextbit;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.webkit.WebView;
import android.widget.TextView;

public class wiki extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }
    public void FAQ(View view)
    {
        Intent intent = new Intent(wiki.this,wikiFAQ.class);
        startActivity(intent);
    }
    public void Hardware(View view)
    {
        Intent intent = new Intent(wiki.this,wikiHardware.class);
        startActivity(intent);
    }
    public void home(View view)
    {
        Intent intent = new Intent(wiki.this,MainActivity.class);
        startActivity(intent);
    }
    public void AboutN(View view)
    {
        Intent intent = new Intent(wiki.this,wikiAbout.class);
        startActivity(intent);
    }
    public void history(View view)
    {
        Intent intent = new Intent(wiki.this,wikiHistory.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
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
        if (this.getLocalClassName().equals("wiki")){
            if (isFirstTime()) {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Welcome to the official Wiki!");
                alertDialog.setMessage("Here you will find info related to the Nextbit Robin! This is the official wiki that is created by the community.");
                alertDialog.setButton("Nice, thanks!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.setIcon(R.drawable.wikialert);
                alertDialog.show();
            }
        }
        if (this.getLocalClassName().equals("wiki")){
            setContentView(R.layout.activity_wiki);
        }
        else if (this.getLocalClassName().equals("wikiAbout")){
            setContentView(R.layout.activity_wiki_about);
        }
        else if (this.getLocalClassName().equals("wikiFAQ")){
            setContentView(R.layout.activity_wiki_faq);
        }
        else if (this.getLocalClassName().equals("wikiFAQ_Cloud")){
            setContentView(R.layout.activity_wiki_faq_cloud);
        }
        else if (this.getLocalClassName().equals("wikiFAQ_General")){
            setContentView(R.layout.activity_wiki_faq_general);
        }
        else if (this.getLocalClassName().equals("wikiFAQ_Hardware")){
            setContentView(R.layout.activity_wiki_faq_hardware);
        }
        else if (this.getLocalClassName().equals("wikiFAQ_MobNet")){
            setContentView(R.layout.activity_wiki_faq_mobnet);
        }
        else if (this.getLocalClassName().equals("wikiFAQ_Soft")){
            setContentView(R.layout.activity_wiki_faq_soft);
        }
        else if (this.getLocalClassName().equals("wikiFAQ_Store")){
            setContentView(R.layout.activity_wiki_faq_store);
        }
        else if (this.getLocalClassName().equals("wikiHardware")){
            setContentView(R.layout.activity_wiki_hardware);
        }
        else if (this.getLocalClassName().equals("wikiHistory")){
            setContentView(R.layout.activity_wiki_history);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView2 = (NavigationView) findViewById(R.id.NavigationView);
        navigationView2.setScrollBarSize(0);
        navigationView2.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
    }
    @Override
    public void onBackPressed() {
        WebView myWebView = (WebView) this.findViewById(R.id.webView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        else if (id == R.id.donate) {
            Intent intent2 = new Intent(this,donationsScreen.class);
            startActivity(intent2);
        }
        else if (id == R.id.india) {
            Intent intent2 = new Intent(this,India.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }
        else if (id == R.id.store) {
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
        }  else if (id == R.id.signout) {
            Intent intent2 = new Intent(this,Login.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }   else if (id == R.id.forumsettings) {
            Intent intent2 = new Intent(this,forumsettings.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
