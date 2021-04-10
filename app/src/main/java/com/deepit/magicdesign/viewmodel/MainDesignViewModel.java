package com.deepit.magicdesign.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.deepit.magicdesign.network.response.MainDesignListResponse;
import com.deepit.magicdesign.repositary.MainDesignRepositary;

public class MainDesignViewModel extends AndroidViewModel {

    private LiveData<MainDesignListResponse> mainDesignListResponseMutableLiveData;

    private MainDesignRepositary mainDesignRepo;

     public MainDesignViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        mainDesignRepo = new MainDesignRepositary();
        mainDesignListResponseMutableLiveData = mainDesignRepo.getmainDesignResponse();
    }

    public void getMainDesign() {
         mainDesignRepo.getMainDesign( );
    }

    public LiveData<MainDesignListResponse> getMainDesignListResponseLiveData() {

        if (mainDesignListResponseMutableLiveData == null)
            mainDesignListResponseMutableLiveData = mainDesignRepo.getmainDesignResponse();

        System.out.println("--- main design obj ---- " + mainDesignListResponseMutableLiveData);
        return mainDesignListResponseMutableLiveData;
    }
}
