package com.b3.movie4.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("results")
    private List<Movie> results = null;

    public List<Movie> getResults() {
        return results;
    }
}

