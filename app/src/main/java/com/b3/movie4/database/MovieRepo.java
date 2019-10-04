package com.b3.movie4.database;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.b3.movie4.model.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepo {
    private static MovieRepo movieRepo;
    private Service service;

    public static MovieRepo getInstance(){
        if (movieRepo == null) {
            movieRepo = new MovieRepo();
        }
        return movieRepo;
    }

    private MovieRepo(){
        service = Base.createService();
    }

    public MutableLiveData<MovieResponse> getMovie(String apiKey, String oriLanguage){
        final MutableLiveData<MovieResponse> movieResponseMutableLiveData = new MutableLiveData<>();
        service.getMovieList(apiKey, oriLanguage).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    movieResponseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                movieResponseMutableLiveData.setValue(null);
            }
        });
        return movieResponseMutableLiveData;
    }
}
