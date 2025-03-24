package com.example.assignment2_mobilecomputing.ui.search;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2_mobilecomputing.data.models.OmdbMovie;
import com.example.assignment2_mobilecomputing.databinding.ItemMovieBinding;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<OmdbMovie> movieList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(OmdbMovie movie);
    }

    public MovieAdapter(List<OmdbMovie> movieList) {
        this.movieList = movieList;
    }

    public void setMovieList(List<OmdbMovie> newList) {
        this.movieList = newList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMovieBinding binding = ItemMovieBinding.inflate(inflater, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        OmdbMovie movie = movieList.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return (movieList != null) ? movieList.size() : 0;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieBinding binding;

        public MovieViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (listener != null && pos != RecyclerView.NO_POSITION) {
                    listener.onItemClick(movieList.get(pos));
                }
            });
        }

        public void bind(OmdbMovie movie) {
            binding.tvTitle.setText(movie.getTitle());
            binding.tvStudio.setText(movie.getType());
            binding.tvYear.setText(movie.getYear());
        }
    }
}
