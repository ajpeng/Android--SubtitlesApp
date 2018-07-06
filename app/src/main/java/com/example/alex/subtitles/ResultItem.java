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
}
