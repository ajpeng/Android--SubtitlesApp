package com.example.alex.subtitles;

import java.io.Serializable;

public class ResultItem implements Serializable{
    String SubFileName;
    String IDMovieImdb;
    String SubHash;
    String MovieName;
    String SubDownloadLink;
    String ZipDownloadLink;
    String SubtitlesLink;

    public String getMovieName() {
        return MovieName;
    }

    public String getSubHash() {
        return SubHash;
    }

    public ResultItem(String SubFileName, String IDMovieImdb, String SubHash, String MovieName, String SubDownloadLink , String ZipDownloadLink , String SubtitlesLink){
        this.SubFileName = SubFileName;
        this.MovieName = MovieName;
        this.IDMovieImdb = IDMovieImdb;
        this.SubHash = SubHash;
        this.SubDownloadLink = SubDownloadLink;
        this.ZipDownloadLink = ZipDownloadLink;
        this.SubtitlesLink = SubtitlesLink;
    }

    public String getSubDownloadLink() {
        return SubDownloadLink;
    }

    public String getZipDownloadLink() {
        return ZipDownloadLink;
    }

    public String getSubtitlesLink() {
        return SubtitlesLink;
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