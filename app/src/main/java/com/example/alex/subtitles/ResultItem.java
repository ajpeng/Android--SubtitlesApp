package com.example.alex.subtitles;

import java.io.Serializable;

public class ResultItem implements Serializable{
    String MovieHash;
    String IDMovieImdb;

    public ResultItem(String MovieHash, String IDMovieImdb){
        this.MovieHash = MovieHash;
        this.IDMovieImdb = IDMovieImdb;
    }


    public String getSubFileName(){
        return MovieHash;
    }

    public String getSubDownloadsCnt(){
        return IDMovieImdb;
    }

    @Override
    public String toString() {
        return "ResultItem{" +
                "MovieHash='" + MovieHash + '\'' +
                ", IDMovieImdb='" + IDMovieImdb + '\'' +
                '}';
    }
}