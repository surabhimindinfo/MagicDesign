package com.deepit.magicdesign.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.deepit.magicdesign.network.response.SubCategoryResponse;
import com.deepit.magicdesign.repositary.SubCategoryRepo;

public class SubCatViewModel extends AndroidViewModel {

    private LiveData<SubCategoryResponse> subCategoryResponseMutableLiveData;

    private SubCategoryRepo subCatRepo;

    public SubCatViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        subCatRepo = new SubCategoryRepo();
        subCategoryResponseMutableLiveData = subCatRepo.getSubCatResponse();
    }

    public void getSubCategory(String main_cat_id, String cat_id) {
        System.out.println("---- sub cat -- " + cat_id);
        System.out.println("---- main cat -- " + main_cat_id);
        subCatRepo.getSubCat(main_cat_id, cat_id);
    }

    public LiveData<SubCategoryResponse> getSubCategoryResponseLiveData() {

        if (subCategoryResponseMutableLiveData == null)
            subCategoryResponseMutableLiveData = subCatRepo.getSubCatResponse();

         return subCategoryResponseMutableLiveData;
    }
}
