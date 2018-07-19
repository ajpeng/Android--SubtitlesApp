package com.example.alex.subtitles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//        URL serverUrl = new URL("https", "api.opensubtitles.org", 443, "/xml-rpc");
// https://forum.opensubtitles.org/viewtopic.php?f=8&t=16453#p39771 reference for REST API
public class ResultsActivity extends AppCompatActivity {
    static String query;
    private static final String TAG = "ResultsActivity";
    private ArrayList arrayList;
    ListView resultListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        configureBackButton();

        resultListView = (ListView) findViewById(R.id.resultListView);
    }

    private void configureBackButton(){
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> finish());
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("User-agent", "UntitledSubtitles");
        headers.put("Content-Type", "application/json; charset=utf-8");
        headers.put("Accept-Language", "en");
        return headers;
    }

    public void launchXMLRPC(View view) throws MalformedURLException, AuthFailureError {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://rest.opensubtitles.org/search/query-" + query;
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
        resultListView = (ListView) findViewById(R.id.resultListView);
        ArrayList tmp = createResultItemArrayList(arrayList);
        ResultListAdapter adapter = new ResultListAdapter(this , R.layout.result_single , tmp);
        resultListView.setAdapter(adapter);
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
