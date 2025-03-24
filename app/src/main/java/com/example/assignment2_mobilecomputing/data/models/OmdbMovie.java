package com.example.assignment2_mobilecomputing.data.models;

public class OmdbMovie {
    private String title;
    private String year;
    private String imdbID;
    private String type;
    private String poster;

    public OmdbMovie(String title, String year, String imdbID, String type, String poster) {
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.type = type;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }
    public String getYear() {
        return year;
    }
    public String getImdbID() {
        return imdbID;
    }
    public String getType() {
        return type;
    }
    public String getPoster() {
        return poster;
    }
}
