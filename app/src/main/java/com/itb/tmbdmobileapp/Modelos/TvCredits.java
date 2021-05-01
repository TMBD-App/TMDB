package com.itb.tmbdmobileapp.Modelos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvCredits {
    @SerializedName("cast")
    private List<TV> cast;

    public TvCredits(List<TV> cast) {
        this.cast = cast;
    }

    public List<TV> getCast() {
        return cast;
    }

    public void setCast(List<TV> cast) {
        this.cast = cast;
    }
}
