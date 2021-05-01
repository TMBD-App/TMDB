package com.itb.tmbdmobileapp.Adapters;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.itb.tmbdmobileapp.Modelos.Movie;
import com.itb.tmbdmobileapp.R;
import com.itb.tmbdmobileapp.Support.Common;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private final OnItemClickListener itemClickListener;
    private final List<Movie> movies;
    private final int layout;

    public interface  OnItemClickListener {
        void onItemClick(Movie movieTest);
    }

    public MovieAdapter(List<Movie> movies, int layout, OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
        this.movies = movies;
        this.layout = layout;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new MovieHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        holder.bind(movies.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView puntuationText, title;
        ProgressBar progressBar;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.photo);
            puntuationText = itemView.findViewById(R.id.progress_bar_num);
            title = itemView.findViewById(R.id.textViewTitle);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Movie movie, final OnItemClickListener listener) {

            Picasso.get().load(Common.MOVIEDB_SMALL_POSTER_URL + movie.getPoster_path()).into(image);

            puntuationText.setText(movie.getVote_average()+"");
            title.setText(movie.getTitle());
            progressBar.setProgress((int) movie.getVote_average() * 10);

            itemView.setOnClickListener(v -> listener.onItemClick(movie));
        }
    }
}
