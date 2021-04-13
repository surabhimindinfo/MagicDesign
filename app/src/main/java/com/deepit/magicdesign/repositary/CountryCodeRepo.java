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

    public CountryCodeRepo()
    {
        countryCodeResponseLiveData=new MutableLiveData<>();
    }

    public void getCountryCode() {


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
                }
            }

            @Override
            public void onFailure(Call<CountryCodeResponse> call, Throwable t) {
                System.out.println("---- response fail----- ");
                t.printStackTrace();
                 countryCodeResponseLiveData.postValue(null);

            }
        });

    }

    public LiveData<CountryCodeResponse> getmainDesignResponse() {


        return countryCodeResponseLiveData;
    }
}
