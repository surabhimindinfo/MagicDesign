package com.deepit.magicdesign.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.deepit.magicdesign.network.response.BannerResponse;
import com.deepit.magicdesign.network.response.MainDesignListResponse;
import com.deepit.magicdesign.repositary.MainDesignRepositary;

public class MainDesignViewModel extends AndroidViewModel {

    private LiveData<MainDesignListResponse> mainDesignListResponseMutableLiveData;
    private LiveData<BannerResponse> bannerResponseMutableLiveData;

    private MainDesignRepositary mainDesignRepo;

     public MainDesignViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        mainDesignRepo = new MainDesignRepositary();
        mainDesignListResponseMutableLiveData = mainDesignRepo.getmainDesignResponse();
        bannerResponseMutableLiveData = mainDesignRepo.getBannerResponse();
    }

    public void getMainDesign() {
         mainDesignRepo.getMainDesign( );
    }

    public void getBanner() {
         mainDesignRepo.getBanner( );
    }

    public LiveData<MainDesignListResponse> getMainDesignListResponseLiveData() {

        if (mainDesignListResponseMutableLiveData == null)
            mainDesignListResponseMutableLiveData = mainDesignRepo.getmainDesignResponse();

        System.out.println("--- main design obj ---- " + mainDesignListResponseMutableLiveData);
        return mainDesignListResponseMutableLiveData;
    }
    public LiveData<BannerResponse> getBannerResponseLiveData() {

        if (bannerResponseMutableLiveData == null)
            bannerResponseMutableLiveData = mainDesignRepo.getBannerResponse();

        System.out.println("--- main design obj ---- " + bannerResponseMutableLiveData);
        return bannerResponseMutableLiveData;
    }
}
