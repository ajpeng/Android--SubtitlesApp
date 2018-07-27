package com.example.alex.subtitles;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.transform.Result;

import static com.example.alex.subtitles.ResultsActivity.progress_bar_type;

public class ResultListAdapter extends ArrayAdapter<ResultItem> {
    Context mCtx;
    int resource;
    List<ResultItem> resultItemList;
    private static final String TAG = "ResultListAdapter ::";
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    ResultItem resultItem;

    public ResultListAdapter(Context mCtx , int resource, List<ResultItem> resultItemList){
        super(mCtx, resource, resultItemList);
        this.mCtx = mCtx;
        this.resource = resource;
        this.resultItemList = resultItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.result_single , null);
        TextView mediaTitle = view.findViewById(R.id.mediaTitle);
        TextView downloadCnt = view.findViewById(R.id.downloadCnt);
        ImageView imageView = view.findViewById(R.id.mediaImageView);

        resultItem = resultItemList.get(position);

        mediaTitle.setText(resultItem.getSubFileName());
        downloadCnt.setText(resultItem.getSubDownloadsCnt());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG , "DownloadOnClick");
                alertBuilder();
            }
        });
        return view;
    }


    public void alertBuilder(){

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("IMDB Info");

        WebView wv = new WebView(getContext());
        wv.loadUrl("https:\\www.imdb.com/title/tt" + resultItem.getIDMovieImdb() +"/");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        alert.setView(wv);
        alert.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(mCtx, android.R.style.Theme_Material_Light_Dialog);
        } else {
            builder = new AlertDialog.Builder(mCtx);
        }
        builder.setTitle("Download file")
                .setIcon(android.R.drawable.stat_sys_download_done)
                .setItems(R.array.resultDialogOptions, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, Integer.toString(which));
                        switch (which){
                            case 0:
                                //View IMDB info
                                //TODO add webview
                                alert.show();
                            case 1:
                                new DownloadFileFromURL().execute(resultItem.getSubDownloadLink(), resultItem.getSubFileName());
                                //TODO fix null
                                Toast.makeText(getContext(), resultItem.getSubFileName() +" download completed", Toast.LENGTH_SHORT).show();
                            case 2:
                                new DownloadFileFromURL().execute(resultItem.getZipDownloadLink(), resultItem.getSubFileName());
                                //TODO fix null
                                Toast.makeText(getContext(), resultItem.getSubFileName() +" download completed", Toast.LENGTH_SHORT).show();

                        }
                        // of the selected item
                    }
                })
                .show();
    }

    /**
     * Background Async Task to download file
     * */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                String filename = f_url[1];
                URLConnection conection = url.openConnection();
                conection.connect();

                // this will be useful so that you can show a tipical 0-100%
                // progress bar
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);

                // Output stream
                OutputStream output = new FileOutputStream(Environment
                        .getExternalStorageDirectory().toString() + filename
                        );

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {

            // dismiss the dialog after the file was downloaded
         //   dismissDialog(progress_bar_type);
        }

    }

}
