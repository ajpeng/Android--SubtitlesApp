package com.example.alex.subtitles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultItemList {
    private ArrayList<ResultItem> resultItemList;
    public ResultItemList(ArrayList<ResultItem> list){
        setResultItemList(list);
    }

    public ArrayList<ResultItem> getResultItemList(){
        return resultItemList;
    }

    public void setResultItemList(ArrayList<ResultItem> customResponseList) {
        this.resultItemList = customResponseList;
    }


    public String toString(){
        return Arrays.toString(new List[]{resultItemList});
    }
    public int size(){
        return resultItemList.size();
    }
}
