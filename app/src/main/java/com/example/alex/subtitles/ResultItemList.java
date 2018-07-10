package com.example.alex.subtitles;

import java.util.Arrays;
import java.util.List;

public class ResultItemList {
    private List<ResultItem> resultItemList;
    public ResultItemList(List<ResultItem> list){
        setResultItemList(list);
    }
    public List<ResultItem> getResultItemList(){
        return resultItemList;
    }
    public void setResultItemList(List<ResultItem> customResponseList) {
        this.resultItemList = customResponseList;
    }

    public String toString(){
        return Arrays.toString(new List[]{resultItemList});
    }
}
