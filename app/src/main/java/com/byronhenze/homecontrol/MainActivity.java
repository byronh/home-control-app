package com.byronhenze.homecontrol;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.byronhenze.homecontrol.LightControlFragment.OnFragmentInteractionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener,
        Listener<JSONObject>,
        ErrorListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(R.string.app_title);
        this.setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)this.findViewById(R.id.toolbar_main);
        this.setSupportActionBar(toolbar);
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
    protected void onStart() {
        super.onStart();

        String url = BuildConfig.SERVER_URL + "/api/lights";
        JsonObjectRequest request = new JsonObjectRequest(Method.GET, url, null, this, this);
        VolleySingleton.getInstance().getRequestQueue().add(request);
    }

    @Override
    public void onErrorResponse(VolleyError e) {
        Log.e(BuildConfig.APP_NAME, e.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        FragmentManager fragmentManager = this.getFragmentManager();
        try {
            JSONArray groups = response.getJSONArray("groups");
            for (int i = 0; i < groups.length(); i++) {
                JSONObject group = groups.getJSONObject(i);
                String name = group.getString("name");
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.linearLayout, LightControlFragment.newInstance(name));
                transaction.commit();
            }
        } catch (JSONException e) {
            Log.e(BuildConfig.APP_NAME, e.toString());
        }
    }

    @Override
    public void onFragmentInteraction(boolean on) {

    }
}
