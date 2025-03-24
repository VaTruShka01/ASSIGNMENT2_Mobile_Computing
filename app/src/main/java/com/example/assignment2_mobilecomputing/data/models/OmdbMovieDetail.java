package com.example.assignment2_mobilecomputing.data.models;

public class OmdbMovieDetail {
    private String title;
    private String year;
    private String rated;
    private String plot;
    private String poster;

    public OmdbMovieDetail() {}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getRated() {
        return rated;
    }
    public void setRated(String rated) {
        this.rated = rated;
    }
    public String getPlot() {
        return plot;
    }
    public void setPlot(String plot) {
        this.plot = plot;
    }
    public String getPoster() {
        return poster;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }
}
