package com.joshbgold.PirateSpanishFree;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by JoshG on 4/3/2015.
 */
public class SettingsActivity extends MainActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        final Button exitButton = (Button) findViewById(R.id.exitButton);
        final Button eraseButton = (Button) findViewById(R.id.eraseButton);

        View.OnClickListener eraseAppSettings = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear prefs
                savePrefs("UserPoints", 0);
                savePrefs("UserRank", 0);
                savePrefs("WordIndex", 0);
                Toast.makeText(SettingsActivity.this, "All yer settings be erased Matey", Toast.LENGTH_LONG).show();
            }
        };

        View.OnClickListener walkThePlank = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };

        eraseButton.setOnClickListener(eraseAppSettings);
        exitButton.setOnClickListener(walkThePlank);
    }

    //save prefs
    public void savePrefs(String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
}
