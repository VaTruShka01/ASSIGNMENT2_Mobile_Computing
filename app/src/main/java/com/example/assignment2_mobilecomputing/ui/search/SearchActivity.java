package com.example.assignment2_mobilecomputing.ui.search;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.assignment2_mobilecomputing.databinding.ActivitySearchBinding;
import com.example.assignment2_mobilecomputing.ui.details.DetailActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private ActivitySearchBinding binding;
    private SearchViewModel viewModel;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        binding.moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieAdapter(new ArrayList<>());
        binding.moviesRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(movie -> {
            Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
            intent.putExtra("imdbID", movie.getImdbID());
            startActivity(intent);
        });

        binding.searchButton.setOnClickListener(v -> {
            String searchTerm = binding.searchPlainText.getText().toString().trim();
            android.util.Log.d("SearchActivity", "Button clicked and User entered:" + searchTerm);
            if (!searchTerm.isEmpty()) {
                viewModel.searchMovies(searchTerm);
            }
        });

        viewModel.getSearchResults().observe(this, results -> {
            adapter.setMovieList(results);
        });
    }
}
