package com.example.alex.subtitles;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DownloadFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "DownloadFragment";

//    private ArrayAdapter<String> mDownloadAdapter;
    ListView resultList;

//
    public DownloadFragment(){
    }
//
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_fragment, container, false);
        resultList = (ListView) view.findViewById(R.id.resultListView);
        //resultList = (ListView) getView.findViewById(R.id.resultListView);

        DownloadTask dt = new DownloadTask();
        List<String> list = null;
        try {
            list = new ArrayList<>(dt.execute().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,list);
        resultList.setAdapter(adapter);

        resultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), resultList.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                startIntent(resultList.getItemAtPosition(position).toString());
            }
        });

        return view;
    }

    public void startIntent(String query){
        Intent intent = new Intent(getActivity() , ResultsActivity.class);
        intent.putExtra("QUERY" , query);
        startActivity(intent);
    }

    public class DownloadTask extends AsyncTask<Void, Void, List<String>>{

        @Override
        protected List<String> doInBackground(Void... voids) {
            try{
                Log.d(TAG , "doInBackground");
                Document doc = Jsoup.connect("https://www.imdb.com/chart/tvmeter").get();

                //return doc.outerHtml().toString();
                return getResultList(doc);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public List<String> getResultList(Document doc){
            List resultList = new LinkedList<>();
//            table id="search_results" is the table which contains the content
//            for( Element table: doc.select(".chart")){
                for( Element innerTable: doc.getElementsByClass("chart full-width")){
                    for (Element row: innerTable.select("tr")){
                        Elements tds = row.select("td");
                        if(tds.size() >= 5){
                            final String title = row.select(".titleColumn a").text();
                            final String year = row.select(".titleColumn span.secondaryInfo").first().text();
                            final String rating = row.select(".imdbRating").text();
                            resultList.add(title + " " + year ) ;
                            //+ " -> " + rating+ " \u2B50
                        }
                    }

                //}
            }
            return resultList;
        }


    }

    public class DownloadTableContent extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            try{
                //Document doc = Jsoup.connect("http://google.com").get();
                Log.d(TAG , "doInBackground");
                Document doc = Jsoup.connect("https://www.opensubtitles.org/en/search2/sublanguageid-all/moviename-" + strings[0]).userAgent("UntitledSubtitles").get();
                getResultList(doc);
                return doc.outerHtml().toString();
                //return strings[0];
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "failed to complete task";
        }

        public List<String> getResultList(Document doc){
            List resultList = new LinkedList<>();
            //table id="search_results" is the table which contains the content
            for( Element table: doc.select("#search_results")){
                for (Element row: table.select("tr")){
                    Elements tds = row.select("td");
                    if(tds.size() >= 4){
                        resultList.add(tds.get(0).text() + " : " + tds.get(1).text());
                        //Log.d(TAG,tds.get(0).text() + " : " + tds.get(1).text());
                    }
                }
            }

            return resultList;
        }
    }


}
