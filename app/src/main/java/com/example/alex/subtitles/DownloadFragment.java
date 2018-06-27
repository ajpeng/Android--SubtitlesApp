package com.example.alex.subtitles;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DownloadFragment extends Fragment {
    private ArrayAdapter<String> mDownloadAdapter;
    ListView resultList;

    public DownloadFragment(){
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one , container, false);
        resultList = (ListView) getView().findViewById(R.id.resultListView);
        try {
            String dt = new DownloadTask().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return view;
    }

    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            try{
                String[] animals= {"cat" ,"dog", "bee", "antelope"};
                final ArrayList list = new ArrayList<String>();
                for(String animal : animals){
                    list.add(animal);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,list);
                resultList.setAdapter(adapter);
                //Document doc = Jsoup.connect("http://google.com").get();
                //return doc.outerHtml();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
