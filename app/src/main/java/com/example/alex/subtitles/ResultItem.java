package com.example.alex.subtitles;

import java.io.Serializable;

public class ResultItem implements Serializable{
    String SubFileName;
    String IDMovieImdb;
    String SubHash;

    public String getSubHash() {
        return SubHash;
    }

    public ResultItem(String SubFileName, String IDMovieImdb, String SubHash){
        this.SubFileName = SubFileName;

        this.IDMovieImdb = IDMovieImdb;
        this.SubHash = SubHash;
    }

    public String getIDMovieImdb() {
        return IDMovieImdb;
    }

    public String getSubFileName(){
        return SubFileName;
    }

    public String getSubDownloadsCnt(){
        return IDMovieImdb;
    }

    @Override
    public String toString() {
        return "ResultItem{" +
                "SubFileName='" + SubFileName + '\'' +
                ", IDMovieImdb='" + IDMovieImdb + '\'' +
                '}';
    }
}