package com.itb.tmbdmobileapp.Retrofit;

import android.view.View;

import com.itb.tmbdmobileapp.Adapters.ActorAdapter;
import com.itb.tmbdmobileapp.Adapters.MovieAdapter;
import com.itb.tmbdmobileapp.Adapters.TvAdapter;
import com.itb.tmbdmobileapp.Fragments.ActorDetailsFragment;
import com.itb.tmbdmobileapp.Fragments.FilmAndSeriesDetailsFragment;
import com.itb.tmbdmobileapp.Fragments.RecomendationsFragment;
import com.itb.tmbdmobileapp.Fragments.SearchFragment;
import com.itb.tmbdmobileapp.Modelos.Cast;
import com.itb.tmbdmobileapp.Modelos.Genre;
import com.itb.tmbdmobileapp.Modelos.GenreResponse;
import com.itb.tmbdmobileapp.Modelos.Movie;
import com.itb.tmbdmobileapp.Modelos.MovieCredits;
import com.itb.tmbdmobileapp.Modelos.MoviesResponse;
import com.itb.tmbdmobileapp.Modelos.People;
import com.itb.tmbdmobileapp.Modelos.PeopleResponse;
import com.itb.tmbdmobileapp.Modelos.TV;
import com.itb.tmbdmobileapp.Modelos.TvCredits;
import com.itb.tmbdmobileapp.Modelos.TvResponse;
import com.itb.tmbdmobileapp.R;
import com.itb.tmbdmobileapp.Support.Common;
import com.itb.tmbdmobileapp.SupportFragmentManagement.FragmentChanger;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static List<Movie> auxMovie = new ArrayList<>();
    public static List<TV> auxTV = new ArrayList<>();
    public static List<People> auxPeople = new ArrayList<>();

    private TMDBapi client;

    public void crearCliente() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Common.TMDB_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();

        client = retrofit.create(TMDBapi.class);
    }


    private void executeMovies(String url, int i, View view) {
        Call<MoviesResponse> call = client.getMovies(url);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NotNull Call<MoviesResponse> call, @NotNull Response<MoviesResponse> response) {
                List<Movie> movies = Arrays.asList(Objects.requireNonNull(response.body()).getResults());
                MovieAdapter adapter;
                if (i == 3) { adapter = new MovieAdapter(movies, R.layout.item_view_linear, movieTest -> FragmentChanger.searchToFilmDetails(movieTest, view));}
                else { adapter = new MovieAdapter(movies, R.layout.item_view_grid, movieTest -> FragmentChanger.recomendatiosToFilmsDetail(movieTest, view)); }
                switch (i) {
                    case 0:
                        RecomendationsFragment.recyclerView1.setAdapter(adapter);
                        break;
                    case 1:
                        RecomendationsFragment.recyclerView2.setAdapter(adapter);
                        break;
                    case 2:
                        RecomendationsFragment.recyclerView3.setAdapter(adapter);
                        break;
                    case 3:
                        SearchFragment.recyclerView.setAdapter(adapter);
                        break;
                }
            }

            @Override
            public void onFailure(@NotNull Call<MoviesResponse> call, @NotNull Throwable t) {}
        });
    }

    private void executeTvResponse(String url, int i, View v) {
        Call<TvResponse> call = client.getSeries(url);
        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(@NotNull Call<TvResponse> call, @NotNull Response<TvResponse> response) {
                List<TV> tv = Arrays.asList(Objects.requireNonNull(response.body()).getResults());
                TvAdapter adapter = new TvAdapter(tv, R.layout.item_view_grid, TV -> FragmentChanger.recomendationsToTvDetails(TV, v));
                switch (i) {
                    case 0:
                        RecomendationsFragment.recyclerView1.setAdapter(adapter);
                        break;
                    case 1:
                        RecomendationsFragment.recyclerView2.setAdapter(adapter);
                        break;
                    case 2:
                        RecomendationsFragment.recyclerView3.setAdapter(adapter);
                        break;
                }
            }

            @Override
            public void onFailure(@NotNull Call<TvResponse> call, @NotNull Throwable t) {}
        });
    }

    private void executeCast(String url, View v) {
        Call<Cast> call = client.getCast(url);
        call.enqueue(new Callback<Cast>() {
            @Override
            public void onResponse(@NotNull Call<Cast> call, @NotNull Response<Cast> response) {
                List<People> tv = Arrays.asList(Objects.requireNonNull(response.body()).getCast());
                ActorAdapter adapter = new ActorAdapter(tv, R.layout.item_view_grid_people, actors -> FragmentChanger.filmDetailsToActorDetail(actors, v));

                FilmAndSeriesDetailsFragment.recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NotNull Call<Cast> call, @NotNull Throwable t) {}
        });
    }

    private void executeMovieCredits(String url, View v) {
        Call<MovieCredits> call = client.getMovieCredits(url);
        call.enqueue(new Callback<MovieCredits>() {
            @Override
            public void onResponse(@NotNull Call<MovieCredits> call, @NotNull Response<MovieCredits> response) {
                List<Movie> movies = Objects.requireNonNull(response.body()).getCast();
                MovieAdapter adapter = new MovieAdapter(movies, R.layout.item_view_grid, movie -> FragmentChanger.actorDetailsToFilmsDetail(movie, v));

                ActorDetailsFragment.recyclerViewFilms.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NotNull Call<MovieCredits> call, @NotNull Throwable t) {}
        });
    }

    private void executeTvCredits(String url, View v) {
        Call<TvCredits> call = client.getTvCredits(url);
        call.enqueue(new Callback<TvCredits>() {
            @Override
            public void onResponse(@NotNull Call<TvCredits> call, @NotNull Response<TvCredits> response) {
                List<TV> tvs = Objects.requireNonNull(response.body()).getCast();
                TvAdapter adapter = new TvAdapter(tvs, R.layout.item_view_grid, tv -> FragmentChanger.actorDetailsToTvDetail(tv, v));

                ActorDetailsFragment.recyclerViewSeries.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NotNull Call<TvCredits> call, @NotNull Throwable t) {}
        });
    }

    private void executePeople(String url, int i, View v) {
        Call<PeopleResponse> call = client.getPeople(url);
        call.enqueue(new Callback<PeopleResponse>() {
            @Override
            public void onResponse(@NotNull Call<PeopleResponse> call, @NotNull Response<PeopleResponse> response) {
                List<People> tv = Arrays.asList(Objects.requireNonNull(response.body()).getResults());
                ActorAdapter adapter = new ActorAdapter(tv, R.layout.item_view_grid_people, actors -> FragmentChanger.recomendationsToActorDetail(actors, v));
                switch (i) {
                    case 0:
                        RecomendationsFragment.recyclerView1.setAdapter(adapter);
                        break;
                    case 1:
                        RecomendationsFragment.recyclerView2.setAdapter(adapter);
                        break;
                    case 2:
                        RecomendationsFragment.recyclerView3.setAdapter(adapter);
                        break;
                }
            }

            @Override
            public void onFailure(Call<PeopleResponse> call, Throwable t) {}
        });
    }

    private void executeGenre(String url, List<Integer> genres) {
        Call<GenreResponse> call = client.getGenre(url);
        call.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(@NotNull Call<GenreResponse> call, @NotNull Response<GenreResponse> response) {
                List<Genre> gnr = Objects.requireNonNull(response.body()).getGenres();
                StringBuilder aux = new StringBuilder();
                boolean flag;
                for (int i = 0; i < gnr.size(); i++) {
                    flag = true;
                    for (int j = 0; j < genres.size() && flag; j++) {
                        if (genres.get(j) == gnr.get(i).getId()) {
                            aux.append(gnr.get(i).getName()).append(", ");
                            flag = false;
                        }
                    }
                }
                FilmAndSeriesDetailsFragment.genres.setText(aux.toString());
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {}
        });
    }

    private void executeOnlyOneMovie(String url, boolean flag, View v) {
        Call<Movie> call = client.getMovieById(url);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                auxMovie.add(response.body());
                if (flag) {
                    MovieAdapter adapter = new MovieAdapter(auxMovie, R.layout.item_view_grid, movie -> FragmentChanger.recomendatiosToFilmsDetail(movie, v));
                    RecomendationsFragment.recyclerView1.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    private void executeOnlyOneTv(String url, boolean flag, View v) {
        Call<TV> call = client.getTVById(url);
        call.enqueue(new Callback<TV>() {
            @Override
            public void onResponse(Call<TV> call, Response<TV> response) {
                auxTV.add(response.body());
                if (flag) {
                    TvAdapter adapter = new TvAdapter(auxTV, R.layout.item_view_grid, tv -> FragmentChanger.recomendationsToTvDetails(tv, v));
                    RecomendationsFragment.recyclerView2.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<TV> call, Throwable t) {

            }
        });
    }

    private void executeOnlyOnePeople(String url, boolean flag, View v) {
        Call<People> call = client.getPeopleById(url);
        call.enqueue(new Callback<People>() {
            @Override
            public void onResponse(Call<People> call, Response<People> response) {
                auxPeople.add(response.body());
                if (flag) {
                    ActorAdapter adapter = new ActorAdapter(auxPeople, R.layout.item_view_grid_people, people -> FragmentChanger.recomendationsToActorDetail(people, v));
                    RecomendationsFragment.recyclerView3.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<People> call, Throwable t) {

            }
        });
    }

    public void onlyFilms(View v) {
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    executeMovies(Common.GET_POPULAR_MOVIES, i, v);
                    break;
                case 1:
                    executeMovies(Common.GET_BEST_MOVIES, i, v);
                    break;
                case 2:
                    executeMovies(Common.GET_NEW_MOVIES, i, v);
                    break;
            }
        }
    }

    public void onlyTv(View v) {
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    executeTvResponse(Common.GET_POPULAR_SERIES, i, v);
                    break;
                case 1:
                    executeTvResponse(Common.GET_BEST_SERIES, i, v);
                    break;
                case 2:
                    executeTvResponse(Common.GET_NEW_SERIES, i, v);
                    break;
            }
        }
    }

    public void onlyPeople(View v) {
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    executePeople(Common.GET_POPULAR_ACTORS, i, v);
                    break;
                case 1:
                    executePeople(Common.GET_POPULAR_ACTORS, i, v);
                    break;
                case 2:
                    executePeople(Common.GET_POPULAR_ACTORS, i, v);
                    break;
            }
        }
    }

    public void allTypes(View v) {
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    executeMovies(Common.GET_POPULAR_MOVIES, i, v);
                    break;
                case 1:
                    executeTvResponse(Common.GET_POPULAR_SERIES, i, v);
                    break;
                case 2:
                    executePeople(Common.GET_POPULAR_ACTORS, i, v);
                    break;
            }
        }
    }

    public void setfilmActors(View v, int movieId) { executeCast(Common.getUrlCastMovies(movieId), v); }
    public void setGenreFilm(List<Integer> genres) { executeGenre(Common.GET_GENRE_MOVIE, genres); }

    public void setGenreTV(List<Integer> genres) {
        executeGenre(Common.GET_GENRE_SERIE, genres);
    }
    public void setTvActors(View v, int movieId) {
        executeCast(Common.getUrlCastTv(movieId), v);
    }

    public void setActorFilms(View v, int peopleId) { executeMovieCredits(Common.getUrlMovieCredits(peopleId), v); }
    public void setActorSeries(View v, int peopleId) { executeTvCredits(Common.getUrlTvCredits(peopleId), v); }

    public void setSearch(View v, String search) { executeMovies(Common.getUrlSearch(search), 3, v);}

    public void setFavorites(View v, List<Integer> moviIds, List<Integer> serieIds, List<Integer> peopleIds) {
        if (moviIds.size() == 0) {
            MovieAdapter adapter = new MovieAdapter(auxMovie, R.layout.item_view_grid, movie -> FragmentChanger.recomendatiosToFilmsDetail(movie, v));
            RecomendationsFragment.recyclerView1.setAdapter(adapter);
        }else {
            for (int i = 0; i < moviIds.size(); i++) {
                executeOnlyOneMovie(Common.getUrlFilmById(moviIds.get(i)), i != moviIds.size() -1, v);
            }
        }

        if (serieIds.size() == 0) {
            TvAdapter adapter = new TvAdapter(auxTV, R.layout.item_view_grid, tv -> FragmentChanger.recomendationsToTvDetails(tv, v));
            RecomendationsFragment.recyclerView2.setAdapter(adapter);
        }else {
            for (int i = 0; i < serieIds.size(); i++) {
                executeOnlyOneTv(Common.getUrlTvById(serieIds.get(i)), i != serieIds.size() - 1, v);
            }
        }

        if (peopleIds.size() == 0) {
            ActorAdapter adapter = new ActorAdapter(auxPeople, R.layout.item_view_grid_people, people -> FragmentChanger.recomendationsToActorDetail(people, v));
            RecomendationsFragment.recyclerView3.setAdapter(adapter);
        }else {
            for (int i = 0; i < peopleIds.size(); i++) {
                executeOnlyOnePeople(Common.getUrlPeopleById(peopleIds.get(i)), i != peopleIds.size() - 1, v);
            }
        }
    }
}
