package com.itb.tmbdmobileapp.Retrofit;


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

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface TMDBapi {

    @GET()
    Call<MoviesResponse> getMovies(@Url String url);

    @GET()
    Call<PeopleResponse> getPeople(@Url String url);

    @GET()
    Call<TvResponse> getSeries(@Url String url);

    @GET()
    Call<Cast> getCast(@Url String url);

    @GET()
    Call<GenreResponse> getGenre(@Url String url);

    @GET()
    Call<MovieCredits> getMovieCredits(@Url String url);

    @GET()
    Call<TvCredits> getTvCredits(@Url String url);

    @GET()
    Call<Movie> getMovieById(@Url String url);

    @GET()
    Call<People> getPeopleById(@Url String url);

    @GET()
    Call<TV> getTVById(@Url String url);

}