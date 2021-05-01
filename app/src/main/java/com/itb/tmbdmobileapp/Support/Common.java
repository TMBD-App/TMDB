package com.itb.tmbdmobileapp.Support;

public class Common {
    public static final String TMDB_API_KEY = "c2037ccc91166de183a05d4c5716824a";
    public final static String TMDB_API_URL = "http://api.themoviedb.org/3/";

    public static final String GET_NEW_MOVIES = "movie/now_playing?api_key="+ TMDB_API_KEY +"&language=en-US&page=1";
    public static final String GET_POPULAR_MOVIES = "movie/popular?api_key="+TMDB_API_KEY+"&language=en-US&page=1";
    public static final String GET_BEST_MOVIES = "movie/top_rated?api_key="+TMDB_API_KEY+"&language=en-US&page=1";

    public static final String GET_NEW_SERIES =  "tv/on_the_air?api_key="+ TMDB_API_KEY +"&language=en-US&page=1";
    public static final String GET_POPULAR_SERIES = "tv/popular?api_key="+TMDB_API_KEY+"&language=en-US&page=1";
    public static final String GET_BEST_SERIES = "tv/top_rated?api_key="+TMDB_API_KEY+"&language=en-US&page=1";

    public static final String GET_POPULAR_ACTORS = "person/popular?api_key="+TMDB_API_KEY+"&language=en-US&page=1";

    public static final String GET_GENRE_MOVIE = "genre/movie/list?api_key="+TMDB_API_KEY+"&language=en-US";
    public static final String GET_GENRE_SERIE = "genre/tv/list?api_key="+TMDB_API_KEY+"&language=en-US";

    public static String getUrlCastMovies(int movieId) {
        return "movie/"+movieId+"/credits?api_key="+TMDB_API_KEY+"&language=en-US";
    }

    public static String getUrlCastTv(int tvId) {
        return "tv/"+tvId+"/credits?api_key="+TMDB_API_KEY+"&language=en-US";
    }

    public static String getUrlMovieCredits(int idPeople) {
        return "person/"+idPeople+"/movie_credits?api_key="+TMDB_API_KEY+"&language=en-US";
    }

    public static String getUrlTvCredits(int idPeople) {
        return "person/"+idPeople+"/tv_credits?api_key="+TMDB_API_KEY+"&language=en-US";
    }

    public static String getUrlSearch(String search) {
        return "search/movie?api_key="+TMDB_API_KEY+"&language=en-US&query="+search;
    }

    public static String getUrlFilmById(int movieId) {
        return "movie/"+movieId+"?api_key="+TMDB_API_KEY+"&language=en-US";
    }

    public static String getUrlTvById(int tvId) {
        return "tv/"+tvId+"?api_key="+TMDB_API_KEY+"&language=en-US";
    }

    public static String getUrlPeopleById(int peopleId) {
        return "person/"+peopleId+"?api_key="+TMDB_API_KEY+"&language=en-US";
    }

    public final static String MOVIEDB_SMALL_POSTER_URL = "http://image.tmdb.org/t/p/w185";
}