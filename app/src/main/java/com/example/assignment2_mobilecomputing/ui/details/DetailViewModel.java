package com.example.assignment2_mobilecomputing.ui.details;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment2_mobilecomputing.data.models.OmdbMovieDetail;
import com.example.assignment2_mobilecomputing.data.repository.MovieRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DetailViewModel extends ViewModel {
    private final MovieRepository repository = new MovieRepository();

    private final MutableLiveData<OmdbMovieDetail> _movieDetail = new MutableLiveData<>();
    public LiveData<OmdbMovieDetail> getMovieDetail() {
        return _movieDetail;
    }

    public void fetchMovieDetail(String imdbID) {
        repository.getMovieDetails(imdbID, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                _movieDetail.postValue(null);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String data = response.body().string();
                    try {
                        JSONObject obj = new JSONObject(data);

                        OmdbMovieDetail detail = new OmdbMovieDetail();
                        detail.setTitle(obj.optString("Title"));
                        detail.setYear(obj.optString("Year"));
                        detail.setRated(obj.optString("Rated"));
                        detail.setPlot(obj.optString("Plot"));
                        detail.setPoster(obj.optString("Poster"));

                        _movieDetail.postValue(detail);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        _movieDetail.postValue(null);
                    }
                } else {
                    _movieDetail.postValue(null);
                }
            }
        });
    }
}
