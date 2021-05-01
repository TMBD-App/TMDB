package com.itb.tmbdmobileapp.SupportFragmentManagement;

import android.view.VerifiedInputEvent;
import android.view.View;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.itb.tmbdmobileapp.Fragments.ActorDetailsFragmentDirections;
import com.itb.tmbdmobileapp.Fragments.FilmAndSeriesDetailsFragmentDirections;
import com.itb.tmbdmobileapp.Fragments.RecomendationsFragmentDirections;
import com.itb.tmbdmobileapp.Fragments.SearchFragmentDirections;
import com.itb.tmbdmobileapp.Modelos.Movie;
import com.itb.tmbdmobileapp.Modelos.People;
import com.itb.tmbdmobileapp.Modelos.TV;
import com.itb.tmbdmobileapp.Support.AppFragmentPossibilities;

public class FragmentChanger {

    public static NavDirections anyFragmentToRecomendations(AppFragmentPossibilities fragment) {
        NavDirections navDirections = null;

        switch (fragment) {
            case ActorDetailsFragment:
                navDirections = ActorDetailsFragmentDirections.actorDetailsToRecomendations();
                break;
            case FilmAndSeriesDetailsFragment:
                navDirections = FilmAndSeriesDetailsFragmentDirections.filmAndSeriesDetailsToRecomendations();
                break;
            case SearchFragment:
                navDirections = SearchFragmentDirections.searchToRecomendations();
                break;
        }

        return navDirections;
    }

    public static NavDirections anyFragmentToSearch(AppFragmentPossibilities fragment) {
        NavDirections navDirections = null;

        switch (fragment) {
            case ActorDetailsFragment:
                navDirections = ActorDetailsFragmentDirections.actorDetailsToSearch();
                break;
            case FilmAndSeriesDetailsFragment:
                navDirections = FilmAndSeriesDetailsFragmentDirections.filmAndSeriesDetailsToSearch();
                break;
            case RecomendationsFragment:
                navDirections = RecomendationsFragmentDirections.recomendationsToSearch();
                break;
        }

        return navDirections;
    }

    public static void recomendatiosToFilmsDetail(Movie movie, View v) {
        NavDirections navDirections = RecomendationsFragmentDirections.recomendationsToFilmAndSeriesDetailsFilm(movie);
        Navigation.findNavController(v).navigate(navDirections);
    }

    public static void recomendationsToTvDetails(TV tv, View v) {
        NavDirections navDirections = RecomendationsFragmentDirections.recomendationsToFilmAndSeriesDetailsSerie(tv);
        Navigation.findNavController(v).navigate(navDirections);
    }

    public static void recomendationsToActorDetail(People people, View v) {
        NavDirections navDirections = RecomendationsFragmentDirections.recomendationsToActorDetails(people);
        Navigation.findNavController(v).navigate(navDirections);
    }

    public static void filmDetailsToActorDetail(People people, View v) {
        NavDirections navDirections = FilmAndSeriesDetailsFragmentDirections.filmAndSeriesDetailsToActorDetails(people);
        Navigation.findNavController(v).navigate(navDirections);
    }

    public static void actorDetailsToFilmsDetail(Movie movie, View v) {
        NavDirections navDirections = ActorDetailsFragmentDirections.actorDetailsToFilmAndSeriesDetailsFilm(movie);
        Navigation.findNavController(v).navigate(navDirections);
    }

    public static void actorDetailsToTvDetail(TV tv, View v) {
        NavDirections navDirections = ActorDetailsFragmentDirections.actorDetailsToFilmAndSeriesDetailsSerie(tv);
        Navigation.findNavController(v).navigate(navDirections);
    }

    public static void searchToFilmDetails(Movie movie, View v) {
        NavDirections navDirections = SearchFragmentDirections.searchToFilmAndSeriesDetailsFilm(movie);
        Navigation.findNavController(v).navigate(navDirections);
    }
}
