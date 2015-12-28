package com.byronhenze.homecontrol;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

    WifiBroadcastReceiver receiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = this.getPreferenceManager().getSharedPreferences();
        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences preferences = this.getPreferenceManager().getSharedPreferences();
        preferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Context context = this.getActivity().getApplicationContext();
        if (key.equals(this.getString(R.string.pref_key_wifi))) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (preferences.getBoolean(key, false)) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
                receiver = new WifiBroadcastReceiver();
                context.registerReceiver(this.receiver, intentFilter);
            } else if (receiver != null) {
                context.unregisterReceiver(this.receiver);
            }
        }
    }
}