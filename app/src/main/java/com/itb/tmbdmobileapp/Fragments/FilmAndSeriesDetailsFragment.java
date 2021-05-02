package com.itb.tmbdmobileapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.itb.tmbdmobileapp.Activities.MainActivity;
import com.itb.tmbdmobileapp.Database.DatabaseHelper;
import com.itb.tmbdmobileapp.Modelos.Movie;
import com.itb.tmbdmobileapp.Modelos.TV;
import com.itb.tmbdmobileapp.R;
import com.itb.tmbdmobileapp.Support.AppFragmentPossibilities;
import com.itb.tmbdmobileapp.Support.Common;
import com.squareup.picasso.Picasso;

public class FilmAndSeriesDetailsFragment extends Fragment {
    public static RecyclerView recyclerView;
    public static TextView genres;
    private enum State {film, serie}
    private State currentState;
    private Movie movie;
    private TV serie;
    private ImageView imageView;
    private TextView title, puntuationText, description;
    private ProgressBar progressBar;
    private FloatingActionButton addButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WellcomeFragment.fragment = this;
        WellcomeFragment.currentFragment = AppFragmentPossibilities.FilmAndSeriesDetailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_specific_movie_serie, container, false);
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (requireArguments().get("film") != null) {
            movie = (Movie) requireArguments().get("film");
            currentState = State.film;
        }

        if (requireArguments().get("serie") != null) {
            serie = (TV) requireArguments().get("serie");
            currentState = State.serie;
        }

        addButton = view.findViewById(R.id.add_button);
        imageView = view.findViewById(R.id.photoSpecific);
        title = view.findViewById(R.id.titleSpecific);
        puntuationText = view.findViewById(R.id.progress_bar_num);
        description = view.findViewById(R.id.textViewDescription);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.recyclerViewFilms);
        genres = view.findViewById(R.id.textViewGenres);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));



        if (currentState == State.film) { executeMovie(); }
        if (currentState == State.serie) { executeSerie(); }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentState == State.film){
                    DatabaseHelper.updatePelis(movie, FirebaseAuth.getInstance().getUid());
                    Toast.makeText(getContext(), "FILM ADDED CORRECTLY", Toast.LENGTH_SHORT).show();
                }
                if (currentState == State.serie){
                    DatabaseHelper.updateSeries(serie, FirebaseAuth.getInstance().getUid());
                    Toast.makeText(getContext(), "SERIE ADDED CORRECTLY", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void executeMovie() {
        Picasso.get().load(Common.MOVIEDB_SMALL_POSTER_URL + movie.getPoster_path()).into(imageView);
        title.setText(movie.getTitle());
        puntuationText.setText(movie.getVote_average()+"");
        description.setText(movie.getOverview());
        progressBar.setProgress((int) movie.getVote_average() * 10);

        MainActivity.apiClient.setfilmActors(requireView(), movie.getId());
        MainActivity.apiClient.setGenreFilm(movie.getGenre_id());
    }

    @SuppressLint("SetTextI18n")
    private void executeSerie() {
        Picasso.get().load(Common.MOVIEDB_SMALL_POSTER_URL + serie.getPosterPath()).into(imageView);
        title.setText(serie.getName());
        puntuationText.setText(serie.getVote_average()+"");
        description.setText(serie.getOverview());
        progressBar.setProgress((int) serie.getVote_average() * 10);

        MainActivity.apiClient.setTvActors(requireView(), serie.getId());
        MainActivity.apiClient.setGenreTV(serie.getGenre_ids());
    }
}
