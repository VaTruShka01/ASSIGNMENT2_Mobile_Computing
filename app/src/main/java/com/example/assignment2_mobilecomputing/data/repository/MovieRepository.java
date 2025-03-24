package com.example.assignment2_mobilecomputing.data.repository;

import com.example.assignment2_mobilecomputing.data.api.ApiClient;
import okhttp3.Callback;

public class MovieRepository {
    private static final String API_KEY = "43c4487c";
    private static final String BASE_URL = "https://www.omdbapi.com/?type=movie&apikey=" + API_KEY;

    public void getMovies(String searchTerm, Callback callback) {
        String url = BASE_URL + "&s=" + searchTerm;
        ApiClient.get(url, callback);
    }

    public void getMovieDetails(String imdbID, Callback callback) {
        String url = BASE_URL + "&i=" + imdbID + "&plot=full";
        ApiClient.get(url, callback);
    }
}
