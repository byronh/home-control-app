package com.byronhenze.homecontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements ErrorListener, Listener<JSONObject> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(R.string.app_title);
        this.setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)this.findViewById(R.id.toolbar_main);
        this.setSupportActionBar(toolbar);

        final Button desktopOnButton = (Button)findViewById(R.id.button_desktop_on);
        desktopOnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.toast_desktop_on, Toast.LENGTH_SHORT).show();
                String url = BuildConfig.SERVER_URL + "/api/desktop/on";
                JsonObjectRequest request = new JsonObjectRequest(Method.GET, url, null, MainActivity.this, MainActivity.this);
                VolleySingleton.getInstance().getRequestQueue().add(request);
            }
        });

        final Button lightsOnButton = (Button)findViewById(R.id.button_lights_on);
        lightsOnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.toast_lights_on, Toast.LENGTH_SHORT).show();
                String url = BuildConfig.SERVER_URL + "/api/lights/on";
                JsonObjectRequest request = new JsonObjectRequest(Method.GET, url, null, MainActivity.this, MainActivity.this);
                VolleySingleton.getInstance().getRequestQueue().add(request);
            }
        });

        final Button lightsOffButton = (Button)findViewById(R.id.button_lights_off);
        lightsOffButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.toast_lights_off, Toast.LENGTH_SHORT).show();
                String url = BuildConfig.SERVER_URL + "/api/lights/off";
                JsonObjectRequest request = new JsonObjectRequest(Method.GET, url, null, MainActivity.this, MainActivity.this);
                VolleySingleton.getInstance().getRequestQueue().add(request);
            }
        });

        final Button lightsDimButton = (Button)findViewById(R.id.button_lights_dim);
        lightsDimButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.toast_lights_dim, Toast.LENGTH_SHORT).show();
                String url = BuildConfig.SERVER_URL + "/api/lights/dim";
                JsonObjectRequest request = new JsonObjectRequest(Method.GET, url, null, MainActivity.this, MainActivity.this);
                VolleySingleton.getInstance().getRequestQueue().add(request);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                this.startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onErrorResponse(VolleyError e) {
        Log.e(BuildConfig.APP_NAME, e.toString());
    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
