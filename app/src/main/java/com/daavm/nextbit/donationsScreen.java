package com.daavm.nextbit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.webkit.WebView;

import com.pushbots.push.Pushbots;

/**
 * Created by David on 14/06/2016.
 */

public class donationsScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public void donate(View view)
    {
        Uri uri = Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=H2R44Y9YPSNQ4"); // missing 'http://' will cause crash
        Intent intent5 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent5);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
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
        super.onCreate(savedInstanceState);
        Pushbots.sharedInstance().init(this);
        setContentView(R.layout.activity_donationscreen);
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
            }else {
            setTheme(R.style.Electric);
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
            }
}
