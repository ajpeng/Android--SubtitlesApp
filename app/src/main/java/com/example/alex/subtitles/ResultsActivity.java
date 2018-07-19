package com.example.alex.subtitles;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nbsp.materialfilepicker.MaterialFilePicker;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

//        URL serverUrl = new URL("https", "api.opensubtitles.org", 443, "/xml-rpc");
// https://forum.opensubtitles.org/viewtopic.php?f=8&t=16453#p39771 reference for REST API
public class ResultsActivity extends AppCompatActivity {
    String query = "";
    private static final String TAG = "ResultsActivity";
    private ArrayList arrayList;
    ListView resultListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
       // configureBackButton();
        resultListView = (ListView) findViewById(R.id.resultsListView);


        query = getIntent().getStringExtra("QUERY");
        try {
            queryString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }

    }

//    private void configureBackButton(){
//        Button backButton = (Button) findViewById(R.id.backButton);
//        backButton.setOnClickListener(view -> finish());
//    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("User-agent", "UntitledSubtitles");
        headers.put("Content-Type", "application/json; charset=utf-8");
        headers.put("Accept-Language", "en");
        return headers;
    }

    public void queryString() throws MalformedURLException, AuthFailureError {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://rest.opensubtitles.org/search/query-" + query;
        Log.d(TAG, "queryString()");
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
                        setResultFragment();
                        //openPicker();
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

    public ArrayList<ResultItem> createResultItemArrayList(List<String> list){
        ArrayList<ResultItem> resultList = new ArrayList();
        Gson gson = new Gson();
        for(String str: list){
            ResultItem item = gson.fromJson(str, ResultItem.class);
            resultList.add(item);
        }
        return resultList;
    }


//    public void filePicker(){
//        new MaterialFilePicker()
//                .withActivity(ResultsActivity.this)
//                .withRequestCode(1)
//                .withFilter(Pattern.compile(".*\\.txt$")) // Filtering files and directories by file name using regexp
//                .withFilterDirectories(true) // Set directories filterable (false by default)
//                .withHiddenFiles(true) // Show hidden files and folders
//                .start();
//    }

    public void setResultFragment(){
        ArrayList tmp = createResultItemArrayList(arrayList);
        ResultListAdapter adapter = new ResultListAdapter(this , R.layout.result_single , tmp);
//        ResultListAdapter adapter = new ResultListAdapter(getApplicationContext(), R.layout.result_single , tmp);
        resultListView.setAdapter(adapter);
    }

    public void openPicker(){
        new MaterialFilePicker()
                .withActivity(ResultsActivity.this)
                .withRequestCode(1)
                .withFilter(Pattern.compile(".*\\.txt$")) // Filtering files and directories by file name using regexp
                .withFilterDirectories(true) // Set directories filterable (false by default)
                .withHiddenFiles(true) // Show hidden files and folders
                .start();
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


}
