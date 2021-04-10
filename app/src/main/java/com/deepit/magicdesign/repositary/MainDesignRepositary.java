package com.deepit.magicdesign.repositary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.deepit.magicdesign.network.ApiController;
import com.deepit.magicdesign.network.ApiInterface;
import com.deepit.magicdesign.network.response.MainDesignListResponse;

import retrofit2.Call;
import retrofit2.Response;

public class MainDesignRepositary {

    private MutableLiveData<MainDesignListResponse> mainDesignListResponseMutableLiveData;

    public MainDesignRepositary()
    {
        mainDesignListResponseMutableLiveData=new MutableLiveData<>();
    }

    public void getMainDesign() {


        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<MainDesignListResponse> call = apiInterface.getMainDesignList();
        call.enqueue(new retrofit2.Callback<MainDesignListResponse>() {
            @Override
            public void onResponse(retrofit2.Call<MainDesignListResponse> call, Response<MainDesignListResponse> response) {

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

                mainDesignListResponseMutableLiveData.postValue(null);

            }
        });

    }

    public LiveData<MainDesignListResponse> getmainDesignResponse() {

        System.out.println("---- design obj in repo --- " + mainDesignListResponseMutableLiveData);

        return mainDesignListResponseMutableLiveData;
    }
}
