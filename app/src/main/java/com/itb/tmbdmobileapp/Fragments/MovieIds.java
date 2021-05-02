package com.itb.tmbdmobileapp.Fragments;

import java.util.List;
import java.util.Map;

public class MovieIds {

    private List<Map<Integer, Integer>> id;

    public MovieIds(List<Map<Integer, Integer>> id) {
        this.id = id;
    }

    public List<Map<Integer, Integer>> getId() {
        return id;
    }

    public void setId(List<Map<Integer, Integer>> id) {
        this.id = id;
    }

    public MovieIds() {
    }
}
