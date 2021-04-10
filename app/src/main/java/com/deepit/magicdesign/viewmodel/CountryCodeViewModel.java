package com.deepit.magicdesign.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.deepit.magicdesign.network.response.CountryCodeResponse;
import com.deepit.magicdesign.repositary.CountryCodeRepo;

public class CountryCodeViewModel extends AndroidViewModel {

    private LiveData<CountryCodeResponse> countryCodeResponseMutableLiveData;

    private CountryCodeRepo countryCodeRepo;

     public CountryCodeViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        countryCodeRepo = new CountryCodeRepo();
        countryCodeResponseMutableLiveData = countryCodeRepo.getmainDesignResponse();
    }

    public void getCountryCode() {
         countryCodeRepo.getCountryCode( );
    }

    public LiveData<CountryCodeResponse> getCountryCodeResponseLiveData() {

        if (countryCodeResponseMutableLiveData == null)
            countryCodeResponseMutableLiveData = countryCodeRepo.getmainDesignResponse();

         return countryCodeResponseMutableLiveData;
    }
}
