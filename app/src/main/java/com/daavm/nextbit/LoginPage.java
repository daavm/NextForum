package com.daavm.nextbit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnKeyListener;

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
        final Activity activity = this;
        final Boolean loged = preferences.getBoolean("loged", false);
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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        if(loged){
            Intent logedBefore = new Intent(getApplicationContext(), MainActivity.class);
            logedBefore.putExtra("password", "");
            logedBefore.putExtra("user", "");
            startActivity(logedBefore);
        }
            Pushbots.sharedInstance().init(this);
            setContentView(R.layout.activity_main_login);
            String username2 = preferences.getString("usernameWV", "hello");
            String pass2 = preferences.getString("passwordWV", "hello");
            if (preferences.getString("usernameWV", "hello") != "hello") {
                if (preferences.getString("passwordWV", "hello") != "hello") {
                    EditText user = (EditText) findViewById(R.id.editText);
                    EditText pass = (EditText) findViewById(R.id.editText2);
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
        final Button forgot = (Button) findViewById(R.id.forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logedBefore = new Intent(getApplicationContext(), forgotPassword.class);
                startActivity(logedBefore);
            }
        });
            final Button signup = (Button) findViewById(R.id.signup);
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent logedBefore = new Intent(getApplicationContext(), signupPage.class);
                    startActivity(logedBefore);
                }
            });
            final Button seguir = (Button) findViewById(R.id.button);
            seguir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = preferences.edit();
                    EditText user = (EditText) findViewById(R.id.editText);
                    EditText pass = (EditText) findViewById(R.id.editText2);
                    String username = user.getText().toString();
                    String password = pass.getText().toString();
                    editor.putString("usernameWV", username).commit();
                    editor.putString("passwordWV", password).commit();
                    editor.remove("loged" + Boolean.valueOf(loged));
                    editor.apply();
                    editor.putBoolean("loged", true);
                    editor.commit();
                    Intent logedBefore = new Intent(getApplicationContext(), Login.class);
                    logedBefore.putExtra("user", username);
                    logedBefore.putExtra("password", password);
                    startActivity(logedBefore);
                }
            });
            EditText pass = (EditText) findViewById(R.id.editText2);
        LinearLayout layout = (LinearLayout)findViewById(R.id.login_layout);
        if (midnightTheme) {
            layout.setBackgroundColor(getResources().getColor(R.color.black));
        } else if (mintTheme) {
            layout.setBackgroundColor(getResources().getColor(R.color.login));
        } else if (electricTheme) {
            layout.setBackgroundColor(getResources().getColor(R.color.black));
        } else {
            layout.setBackgroundColor(getResources().getColor(R.color.black));
        }
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
            pass.setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        seguir.performClick();
                        return true;
                    }
                    return false;
                }
            });
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.about) {
            Intent intent2 = new Intent(this,about.class);
            startActivity(intent2);
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
            startActivity(intent2);
        } else if (id == R.id.nav_wiki) {
            Intent intent2 = new Intent(this,wiki.class);
            startActivity(intent2);
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
