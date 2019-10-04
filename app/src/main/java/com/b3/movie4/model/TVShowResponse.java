package com.b3.movie4.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVShowResponse {
    @SerializedName("results")
    private List<TVShow> results = null;

    public List<TVShow> getResults() {
        return results;
    }
}
