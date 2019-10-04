package com.b3.movie4.database;


import com.b3.movie4.model.MovieResponse;
import com.b3.movie4.model.TVShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("discover/movie")
    Call<MovieResponse> getMovieList(@Query("api_key") String apiKey, @Query("language") String oriLanguage);

    @GET("discover/tv")
    Call<TVShowResponse> getTVList(@Query("api_key") String apiKey, @Query("language") String oriLanguage);
}
