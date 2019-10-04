package com.b3.movie4.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.b3.movie4.BuildConfig;
import com.b3.movie4.database.TVShowRepo;

public class TVShowViewModel extends ViewModel {
    private MutableLiveData<TVShowResponse> mutableLiveData;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        TVShowRepo tvShowRepo = TVShowRepo.getInstance();
        mutableLiveData = tvShowRepo.getTV(BuildConfig.API_TOKEN, BuildConfig.ORI_LANGUAGE);
    }

    public LiveData<TVShowResponse> getTVRepo() {
        return mutableLiveData;
    }
}
