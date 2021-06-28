package com.deepit.magicdesign.repositary;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.deepit.magicdesign.network.ApiController;
import com.deepit.magicdesign.network.ApiInterface;
import com.deepit.magicdesign.network.response.CountryCodeResponse;

import retrofit2.Call;
import retrofit2.Response;

public class CountryCodeRepo {

    private MutableLiveData<CountryCodeResponse> countryCodeResponseLiveData;
    private MutableLiveData<Integer> loadingData;

    public CountryCodeRepo()
    {
        countryCodeResponseLiveData=new MutableLiveData<>();
        loadingData=new MutableLiveData<>();
    }

    public void getCountryCode() {

        loadingData.setValue(0);
        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<CountryCodeResponse> call = apiInterface.getCountryCode();
        call.enqueue(new retrofit2.Callback<CountryCodeResponse>() {
            @Override
            public void onResponse(Call<CountryCodeResponse> call, Response<CountryCodeResponse> response) {

                CountryCodeResponse response1 = response.body();
                System.out.println("----- response code country code----- " + response1.getStatus());
                if (response.body() != null) {
                    System.out.println("---- response body----- " + response.body() );
                    System.out.println("---- response success----- " + response.body().getCountryRecord() );
                    countryCodeResponseLiveData.postValue(response.body());
                    loadingData.setValue(1);
                }
            }

            @Override
            public void onFailure(Call<CountryCodeResponse> call, Throwable t) {
                System.out.println("---- response fail----- ");
                t.printStackTrace();
                loadingData.setValue(1);
                 countryCodeResponseLiveData.postValue(null);

            }
        });

    }

    public LiveData<CountryCodeResponse> getmainDesignResponse() {

        return countryCodeResponseLiveData;
    }
    public LiveData<Integer> getLoadingValue() {

        return loadingData;
    }
}
