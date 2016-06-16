package com.daavm.nextbit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by David on 16/06/2016.
 */

public class Start extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String loged = preferences.getString("loged", "no");
        super.onCreate(savedInstanceState);

        if(loged != "yes") {
            Intent logedBefore = new Intent(getApplicationContext(), LoginPage.class);
            startActivity(logedBefore);
        } else {
            Intent logedBefore = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(logedBefore);
        }
    }

}
