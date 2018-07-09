package com.example.alex.subtitles;

public class ResultItem {
        private String SubFileName;
        private String IDMovieImdb;
        private String SubHash;
        private String SubLanguageID;
        private String ZipDownloadLink;
        private String SubDownloadLink;
        private String SubDownloadsCnt;

        public ResultItem(String fileName, String imdb , String hash, String lang, String zip, String sub,  String count){
            SubFileName = fileName;
            IDMovieImdb = imdb;
            SubHash = hash;
            SubLanguageID = lang;
            ZipDownloadLink = zip;
            SubDownloadLink = sub;
            SubDownloadsCnt = count;
        }

    public String getSubFileName() {
        return SubFileName;
    }

    public void setSubFileName(String subFileName) {
        SubFileName = subFileName;
    }

    public String getIDMovieImdb() {
        return IDMovieImdb;
    }

    public void setIDMovieImdb(String IDMovieImdb) {
        this.IDMovieImdb = IDMovieImdb;
    }

    public String getSubHash() {
        return SubHash;
    }

    public void setSubHash(String subHash) {
        SubHash = subHash;
    }

    public String getSubLanguageID() {
        return SubLanguageID;
    }

    public void setSubLanguageID(String subLanguageID) {
        SubLanguageID = subLanguageID;
    }

    public String getZipDownloadLink() {
        return ZipDownloadLink;
    }

    public void setZipDownloadLink(String zipDownloadLink) {
        ZipDownloadLink = zipDownloadLink;
    }

    public String getSubDownloadLink() {
        return SubDownloadLink;
    }

    public void setSubDownloadLink(String subDownloadLink) {
        SubDownloadLink = subDownloadLink;
    }

    public String getSubDownloadsCnt() {
        return SubDownloadsCnt;
    }

    public void setSubDownloadsCnt(String subDownloadsCnt) {
        SubDownloadsCnt = subDownloadsCnt;
    }
}
