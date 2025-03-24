package com.example.assignment2_mobilecomputing.ui.search;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment2_mobilecomputing.data.models.OmdbMovie;
import com.example.assignment2_mobilecomputing.data.repository.MovieRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchViewModel extends ViewModel {
    private final MovieRepository repository = new MovieRepository();

    private final MutableLiveData<List<OmdbMovie>> _searchResults = new MutableLiveData<>();
    public LiveData<List<OmdbMovie>> getSearchResults() {
        return _searchResults;
    }

    public void searchMovies(String searchTerm) {
        repository.getMovies(searchTerm, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                _searchResults.postValue(new ArrayList<>());
                android.util.Log.e("SearchViewModel", "API call failed:", e);

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String data = response.body().string();
                    android.util.Log.d("SearchViewModel", "API response: " + data);
                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        JSONArray searchArray = jsonObject.optJSONArray("Search");

                        List<OmdbMovie> results = new ArrayList<>();
                        if (searchArray != null) {
                            for (int i = 0; i < searchArray.length(); i++) {
                                JSONObject movieObj = searchArray.getJSONObject(i);

                                OmdbMovie movie = new OmdbMovie(
                                        movieObj.optString("Title"),
                                        movieObj.optString("Year"),
                                        movieObj.optString("imdbID"),
                                        movieObj.optString("Type"),
                                        movieObj.optString("Poster")
                                );
                                results.add(movie);
                            }
                        }
                        _searchResults.postValue(results);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        _searchResults.postValue(new ArrayList<>());
                    }
                } else {
                    _searchResults.postValue(new ArrayList<>());
                }
            }
        });
    }
}
