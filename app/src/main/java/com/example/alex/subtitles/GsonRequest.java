package com.example.alex.subtitles;

import com.android.volley.AuthFailureError;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class GsonRequest<T> extends Request<T> {
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Map<String, String> params;
    private final Response.Listener<T> listener;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url
     *            URL of the request to make
     * @param clazz
     *            Relevant class object, for Gson's reflection
     * @param headers
     *            Map of request headers
     */
    public GsonRequest(int method, String url, Class<T> clazz,
                       Map<String, String> headers, Map<String, String> params,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.params = params;
        this.listener = listener;
    }

    /**
     * Recieves header
     *
     * @param method
     * @param url
     * @param clazz
     * @param params
     * @param listener
     * @param errorListener
     */
    public GsonRequest(int method, String url, Class<T> clazz,
                       Map<String, String> params, Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {
        super(method,url, errorListener);
        this.clazz = clazz;
        this.headers = new HashMap<String, String>();
        this.params = params;
        this.listener = listener;
    }

    /**
     * No params or headers
     *
     * @param method
     * @param url
     * @param clazz
     * @param listener
     * @param errorListener
     */
    public GsonRequest(int method, String url, Class<T> clazz,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.headers = new HashMap<String, String>();
        this.params = new HashMap<String, String>();
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return params != null ? params : super.getParams();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
//    @Override
//    public Map<String, String> getHeaders() throws AuthFailureError {
//        HashMap<String, String> headers = new HashMap<>();
//        headers.put("User-agent", "UntitledSubtitles");
//        headers.put("Content-Type", "application/json; charset=utf-8");
//        headers.put("Accept-Language", "en");
//        return headers;
//    }
}