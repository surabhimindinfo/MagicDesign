package com.deepit.magicdesign.repositary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.deepit.magicdesign.network.ApiController;
import com.deepit.magicdesign.network.ApiInterface;
import com.deepit.magicdesign.network.response.BannerResponse;
import com.deepit.magicdesign.network.response.MainDesignListResponse;

import retrofit2.Call;
import retrofit2.Response;

public class MainDesignRepositary {


    private final MutableLiveData<MainDesignListResponse> mainDesignListResponseMutableLiveData;
    private final MutableLiveData<BannerResponse> bannerResponseMutableLiveData;
    private final MutableLiveData<Boolean> loadingData;

    public MainDesignRepositary() {
        mainDesignListResponseMutableLiveData = new MutableLiveData<>();
        bannerResponseMutableLiveData = new MutableLiveData<>();
        loadingData = new MutableLiveData<>();
    }

    public void getMainDesign() {

        loadingData.setValue(true);
        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<MainDesignListResponse> call = apiInterface.getMainDesignList();
        call.enqueue(new retrofit2.Callback<MainDesignListResponse>() {
            @Override
            public void onResponse(retrofit2.Call<MainDesignListResponse> call, Response<MainDesignListResponse> response) {
                loadingData.setValue(false);

                MainDesignListResponse response1 = response.body();
                System.out.println("----- response code ----- " + response1.getStatus());
                if (response.body() != null) {
                    System.out.println("---- response success----- " + response.message());
                    mainDesignListResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<MainDesignListResponse> call, Throwable t) {
                System.out.println("---- response fail----- ");
                t.printStackTrace();
                loadingData.setValue(false);
                mainDesignListResponseMutableLiveData.postValue(null);

            }
        });

    }

    public void getBanner() {
        loadingData.setValue(true);
        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<BannerResponse> call = apiInterface.getBanner();
        call.enqueue(new retrofit2.Callback<BannerResponse>() {
            @Override
            public void onResponse(retrofit2.Call<BannerResponse> call, Response<BannerResponse> response) {
                loadingData.setValue(false);
                BannerResponse response1 = response.body();
                System.out.println("----- response code ----- " + response1.getStatus());
                if (response.body() != null) {
                    System.out.println("---- response success----- " + response.message());

                    bannerResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<BannerResponse> call, Throwable t) {
                System.out.println("---- response fail----- ");
                t.printStackTrace();
                loadingData.setValue(false);
                bannerResponseMutableLiveData.postValue(null);

            }
        });

    }

    public LiveData<MainDesignListResponse> getmainDesignResponse() {
        return mainDesignListResponseMutableLiveData;
    }

    public LiveData<BannerResponse> getBannerResponse() {
        return bannerResponseMutableLiveData;
    }

    public LiveData<Boolean> getLoading() {
        return loadingData;
    }
}
