package com.example.alex.subtitles;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.List;
import java.util.regex.Pattern;

import javax.xml.transform.Result;

public class ResultListAdapter extends ArrayAdapter<ResultItem> {
    Context mCtx;
    int resource;
    List<ResultItem> resultItemList;
    private static final String TAG = "ResultListAdapter :: ";


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

        ResultItem resultItem = resultItemList.get(position);

        mediaTitle.setText(resultItem.getSubFileName());
        downloadCnt.setText(resultItem.getSubDownloadsCnt());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG , "DownloadOnClick");
             //   alertBuilder();

            }
        });
        return view;
    }




    public void alertBuilder(){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(mCtx, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(mCtx);
        }
        builder.setTitle("Download file")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
//                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do nothing
//                    }
//                })
                .setIcon(android.R.drawable.stat_sys_download)
                .show();
    }

}
