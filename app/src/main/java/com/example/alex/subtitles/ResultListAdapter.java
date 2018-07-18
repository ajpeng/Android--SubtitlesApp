package com.example.alex.subtitles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.xml.transform.Result;

public class ResultListAdapter extends ArrayAdapter<ResultItem> {
    Context mCtx;
    int resource;
    List<ResultItem> resultItemList;

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
        //imageView.setImageDrawable(drawable, null);

//        view.findViewById(R.id.downloadBtn).setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mCtx, "Downloading subtitle file", Toast.LENGTH_SHORT);
//            }
//        });
        return view;
    }
}
