package com.byronhenze.homecontrol;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(R.string.app_title);
        setContentView(R.layout.activity_main);
    }
}
