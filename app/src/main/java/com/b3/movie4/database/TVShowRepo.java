package com.b3.movie4.database;

import androidx.lifecycle.MutableLiveData;

import com.b3.movie4.model.TVShowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowRepo {
    private static TVShowRepo tvShowRepo;
    private Service service;

    public static TVShowRepo getInstance(){
        if (tvShowRepo == null){
            tvShowRepo = new TVShowRepo();
        }
        return tvShowRepo;
    }

    private TVShowRepo(){
        service = Base.createService();
    }

    public MutableLiveData<TVShowResponse> getTV(String apiKey, String oriLanguage){
        final MutableLiveData<TVShowResponse> tvShowResponseMutableLiveData = new MutableLiveData<>();
        service.getTVList(apiKey, oriLanguage).enqueue(new Callback<TVShowResponse>() {
            @Override
            public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                if (response.isSuccessful()){
                    tvShowResponseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TVShowResponse> call, Throwable t) {
                tvShowResponseMutableLiveData.setValue(null);
            }
        });
        return tvShowResponseMutableLiveData;
    }
}
