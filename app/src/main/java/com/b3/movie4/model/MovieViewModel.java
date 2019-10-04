package com.b3.movie4.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.b3.movie4.BuildConfig;
import com.b3.movie4.database.MovieRepo;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<MovieResponse> mutableLiveData;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        MovieRepo movieRepository = MovieRepo.getInstance();
        mutableLiveData = movieRepository.getMovie(BuildConfig.API_TOKEN, BuildConfig.ORI_LANGUAGE);
    }

    public LiveData<MovieResponse> getMovieRepo() {
        return mutableLiveData;
    }
}
