package com.example.alex.subtitles;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class CallAPI extends AsyncTask<String, String, String> {

    public CallAPI(){
        //set context variables if required
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... params) {

        String urlString = params[0]; // URL to call

        OutputStream out = null;
        try {

            //


        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

        return out.toString();
    }
}