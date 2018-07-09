package com.example.alex.subtitles;

import java.util.List;

public class ResultItemList {
    private List<ResultItem> resultItemList;
    public List<ResultItem> getResultItemList(){
        return resultItemList;
    }
    public void setResultItemList(List<ResultItem> customResponseList) {
        this.resultItemList = customResponseList;
    }
}
