package com.example.alex.subtitles;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public ArrayList arrayList;
    ResultItem[] resultItemArray;
    ResultItemList resultItemList = null;
    ArrayList<ResultItem> resultItemList2;
    ListView resultListView2;

    private static final String TAG = "MainActivity";
    TextView query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      //  this.context = context;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        query = (TextView) findViewById(R.id.queryTextField);
        Button button2 = findViewById(R.id.button2);
    }

    //@Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("User-agent", "UntitledSubtitles");
        headers.put("Content-Type", "application/json; charset=utf-8");
        headers.put("Accept-Language", "en");
        return headers;
    }

//    public ArrayList<ResultItem> getResultItemArray(){
//        ArrayList<ResultItem> resultArrayList = new ArrayList<>();
//        Gson gson = new Gson();
//        for(String str: arrayList){
//            ResultItem item = gson.fromJson(str, ResultItem.class);
//            resultArrayList.add(item);
//        }
//        return resultArrayList;
//    }

    public void launchXMLRPC(View view) throws MalformedURLException, AuthFailureError {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://rest.opensubtitles.org/search/query-" + query.getText();
        Log.d(TAG, "LaunchedXMLRPC");
        GsonRequest<ResultItem[]> gsonRequest = null;
        arrayList = new ArrayList<>();

        gsonRequest = new GsonRequest<ResultItem[]>(Request.Method.GET, url, ResultItem[].class, getHeaders(),
                new Response.Listener<ResultItem[]>() {
                    @Override
                    public void onResponse(ResultItem[] response) {
                        Gson gson = new Gson();
                        for (ResultItem item : response) {
                            String jsonString = gson.toJson(item);
                            arrayList.add(jsonString);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
                ) {@Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("User-agent", BuildConfig.ApiKey);
                        params.put("Accept-Language", "en");
                        return params;
                    }};
        queue.add(gsonRequest);
    }

//        URL serverUrl = new URL("https", "api.opensubtitles.org", 443, "/xml-rpc");
// https://forum.opensubtitles.org/viewtopic.php?f=8&t=16453#p39771 reference for REST API



    public ArrayList<ResultItem> createResultItemArrayList(List<String> list){
        ArrayList<ResultItem> resultList = new ArrayList();
        Gson gson = new Gson();
        for(String str: list){
            ResultItem item = gson.fromJson(str, ResultItem.class);
            resultList.add(item);
        }
        return resultList;
    }


    public void setResultFragment(View view){
        resultListView2 = (ListView) findViewById(R.id.resultItemList2);
        ArrayList tmp = createResultItemArrayList(arrayList);
        ResultListAdapter adapter = new ResultListAdapter(this , R.layout.result_single , tmp);
        resultListView2.setAdapter(adapter);
    }

    private Response.Listener<ResultItem> createMyReqSuccessListener() {
        return new Response.Listener<ResultItem>() {
            @Override
            public void onResponse(ResultItem response) {
                // Do whatever you want to do with response;
                // Like response.tags.getListing_count(); etc. etc.
            }
        };
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Do whatever you want to do with error.getMessage();
            }
        };
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
