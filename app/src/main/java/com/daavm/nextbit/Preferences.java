package com.daavm.nextbit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Preferences extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final boolean midnightTheme = preferences.getBoolean("midnightTheme", false);
        final boolean mintTheme = preferences.getBoolean("mintTheme", false);
        final boolean electricTheme = preferences.getBoolean("electricTheme", false);
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
        setContentView(R.layout.preferences_layout);
        addPreferencesFromResource(R.xml.preferences);
        final Preference mint = (Preference) findPreference("mintTheme");
        final Preference midnight = (Preference) findPreference("midnightTheme");
        final Preference electric = (Preference) findPreference("electricTheme");
        if (preferences.getBoolean("mintTheme", true)) {
            getPreferenceScreen().findPreference("midnightTheme").setEnabled(false);
            getPreferenceScreen().findPreference("electricTheme").setEnabled(false);
        } else if (preferences.getBoolean("midnightTheme", true)) {
            getPreferenceScreen().findPreference("electricTheme").setEnabled(false);
            getPreferenceScreen().findPreference("mintTheme").setEnabled(false);
        } else if (preferences.getBoolean("electricTheme", true)) {
            getPreferenceScreen().findPreference("midnightTheme").setEnabled(false);
            getPreferenceScreen().findPreference("mintTheme").setEnabled(false);
        } else {
            getPreferenceScreen().findPreference("midnightTheme").setEnabled(false);
            getPreferenceScreen().findPreference("mintTheme").setEnabled(false);
            CheckBoxPreference check = (CheckBoxPreference)electric;
            check.setChecked(true);
        }

        mint.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (preferences.getBoolean("mintTheme", true)){
                    getPreferenceScreen().findPreference("midnightTheme").setEnabled(false);
                    getPreferenceScreen().findPreference("electricTheme").setEnabled(false);
                } else {
                    getPreferenceScreen().findPreference("midnightTheme").setEnabled(true);
                    getPreferenceScreen().findPreference("electricTheme").setEnabled(true);
                }
                return true;
            }
        });
        midnight.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (preferences.getBoolean("midnightTheme", true)){
                    getPreferenceScreen().findPreference("electricTheme").setEnabled(false);
                    getPreferenceScreen().findPreference("mintTheme").setEnabled(false);
                } else {
                    getPreferenceScreen().findPreference("electricTheme").setEnabled(true);
                    getPreferenceScreen().findPreference("mintTheme").setEnabled(true);
                }
                return true;
            }
        });
        electric.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (preferences.getBoolean("electricTheme", true)){
                    getPreferenceScreen().findPreference("midnightTheme").setEnabled(false);
                    getPreferenceScreen().findPreference("mintTheme").setEnabled(false);
                } else {
                    getPreferenceScreen().findPreference("midnightTheme").setEnabled(true);
                    getPreferenceScreen().findPreference("mintTheme").setEnabled(true);
                }
                return true;
            }
        });

    }

    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "App will be restarted to change the theme, enjoy it!",
                Toast.LENGTH_LONG).show();
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}