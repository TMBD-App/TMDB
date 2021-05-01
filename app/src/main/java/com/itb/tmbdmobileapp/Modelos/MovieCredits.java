package com.itb.tmbdmobileapp.Modelos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieCredits {
    @SerializedName("cast")
    private List<Movie> cast;

    public MovieCredits(List<Movie> cast) {
        this.cast = cast;
    }

    public List<Movie> getCast() {
        return cast;
    }

    public void setCast(List<Movie> cast) {
        this.cast = cast;
    }
}
