package com.example.alex.subtitles;

import com.google.gson.Gson;

import java.util.List;


public class ResultParser {
    List<ResultItem> resultItemList;

    public List<ResultItem> getResultItemList() {
        return resultItemList;
    }

    public ResultParser(List<ResultItem> list){
        resultItemList = list;

    }

    public ResultItem get(int i){
        return resultItemList.get(i);
    }

    public List<ResultItem> getList(){
        return resultItemList;
    }

    public void parseGson(){

        Gson gson = new Gson();
        for(ResultItem item :resultItemList){

        }
    }



}
