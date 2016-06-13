package com.daavm.nextbit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Preferences extends PreferenceActivity {
    private SharedPreferences.OnSharedPreferenceChangeListener prefListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean midnightTheme = preferences.getBoolean("midnightTheme", false);
        boolean mintTheme = preferences.getBoolean("mintTheme", false);
        boolean electricTheme = preferences.getBoolean("electricTheme", false);
        if (midnightTheme) {
            setTheme(R.style.Midnight);
        } else if (mintTheme) {
            setTheme(R.style.Mint);
        } else if (electricTheme) {
            setTheme(R.style.Electric);
        }else {
            setTheme(R.style.Electric);
        }
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
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