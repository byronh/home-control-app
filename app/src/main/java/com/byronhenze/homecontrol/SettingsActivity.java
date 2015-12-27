package com.byronhenze.homecontrol;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(R.string.action_settings);
        this.setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar)this.findViewById(R.id.toolbar_settings);
        this.setSupportActionBar(toolbar);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
