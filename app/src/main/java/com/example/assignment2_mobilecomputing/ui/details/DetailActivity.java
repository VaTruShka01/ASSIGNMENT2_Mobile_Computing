package com.example.assignment2_mobilecomputing.ui.details;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.assignment2_mobilecomputing.R;
import com.example.assignment2_mobilecomputing.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private DetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        Button backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> finish());
        String imdbID = getIntent().getStringExtra("imdbID");
        if (imdbID != null) {
            viewModel.fetchMovieDetail(imdbID);
        }

        viewModel.getMovieDetail().observe(this, detail -> {
            if (detail != null) {
                binding.titleTextView.setText(detail.getTitle());
                binding.yearTextView.setText(detail.getYear());
                binding.ratedTextView.setText(detail.getRated());
                binding.plotTextView.setText(detail.getPlot());
                Glide.with(this)
                        .load(detail.getPoster())
                        .error(R.drawable.ic_launcher_foreground)
                        .into(binding.imageView);

            }

            binding.backButton.setOnClickListener(v -> {
                finish();
            });


        });
    }
}
