package com.deepit.magicdesign.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.deepit.magicdesign.network.response.FilterResponse;
import com.deepit.magicdesign.network.response.FormatPrefResponse;
import com.deepit.magicdesign.network.response.ItemResponse;
import com.deepit.magicdesign.network.response.SubCategoryResponse;
import com.deepit.magicdesign.repositary.SubCategoryRepo;

public class SubCatViewModel extends AndroidViewModel {

    private LiveData<SubCategoryResponse> subCategoryResponseMutableLiveData;
    private LiveData<ItemResponse> itemResponseMutableLiveData;
    private LiveData<FilterResponse> filterResponseMutableLiveData;
    private LiveData<FormatPrefResponse> formatPrefResponseMutableLiveData;
    private LiveData<FormatPrefResponse> catPrefResponseMutableLiveData;
    private LiveData<FormatPrefResponse> subCatPrefResponseMutableLiveData;
    private LiveData<FormatPrefResponse> uploadPrefResponseMutableLiveData;
    private LiveData<FormatPrefResponse> downloadResponseMutableLiveData;
    private SubCategoryRepo subCatRepo;

    public SubCatViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        subCatRepo = new SubCategoryRepo();
        subCategoryResponseMutableLiveData = subCatRepo.getSubCatResponse();
        itemResponseMutableLiveData = subCatRepo.getItemResponse();
        filterResponseMutableLiveData = subCatRepo.getFilterResponse();
        formatPrefResponseMutableLiveData = subCatRepo.getFormatPrefResponse();
        catPrefResponseMutableLiveData = subCatRepo.getCatPrefResponse();
        subCatPrefResponseMutableLiveData = subCatRepo.getSubCatPrefResponse();
        uploadPrefResponseMutableLiveData = subCatRepo.uploadPrefResponse();
        downloadResponseMutableLiveData = subCatRepo.downloadResponse();
    }

    public void getSubCategory(String main_cat_id, String cat_id) {

        subCatRepo.getSubCat(main_cat_id, cat_id);
    }

    public void getItem(String main_category_id, String category_id,String subcategory_id,
                       int perpage,int page,String search,String filterid) {
        System.out.println("-- get item ---");
        subCatRepo.getItem(main_category_id,category_id,
                subcategory_id,perpage,page,search,filterid);
    }

    public void showFilter() {

        subCatRepo.getFilter();
    }

    public void getFormatPref(String userID) {

        subCatRepo.getFormatPref(userID);
    }

    public void getCatPref(String userID,String main_cat) {

        subCatRepo.getCatPref(userID,main_cat);
    }

    public void getSubCatPref(String userID,String catID) {

        subCatRepo.getSubCatPref(userID,catID);
    }
    public void checkDownload(String userID,String itemID) {

        subCatRepo.checkAvailabilityDownload(itemID,userID);
    }
    public void savePrefToServer(String userID,String machineId,String mainCatId,String catID,String subCatId) {

        subCatRepo.savePrefToServer(userID,machineId,mainCatId,catID,subCatId);
    }

    public LiveData<FormatPrefResponse> downloadResponseLiveData() {

        if (downloadResponseMutableLiveData == null)
            downloadResponseMutableLiveData = subCatRepo.downloadResponse();

         return downloadResponseMutableLiveData;
    }


    public LiveData<SubCategoryResponse> getSubCategoryResponseLiveData() {

        if (subCategoryResponseMutableLiveData == null)
            subCategoryResponseMutableLiveData = subCatRepo.getSubCatResponse();

         return subCategoryResponseMutableLiveData;
    }
    public LiveData<ItemResponse> getItemResponseLiveData() {

        if (itemResponseMutableLiveData == null)
            itemResponseMutableLiveData = subCatRepo.getItemResponse();

         return itemResponseMutableLiveData;
    }
    public LiveData<FilterResponse> getFilterResponseLiveData() {

        if (filterResponseMutableLiveData == null)
            filterResponseMutableLiveData = subCatRepo.getFilterResponse();

         return filterResponseMutableLiveData;
    }
    public LiveData<FormatPrefResponse> getFormatPrefLiveData() {

        if (formatPrefResponseMutableLiveData == null)
            formatPrefResponseMutableLiveData = subCatRepo.getFormatPrefResponse();

         return formatPrefResponseMutableLiveData;
    }
    public LiveData<FormatPrefResponse> getCatPrefLiveData() {

        if (catPrefResponseMutableLiveData == null)
            catPrefResponseMutableLiveData = subCatRepo.getCatPrefResponse();

         return catPrefResponseMutableLiveData;
    }public LiveData<FormatPrefResponse> getSubCatPrefLiveData() {

        if (subCatPrefResponseMutableLiveData == null)
            subCatPrefResponseMutableLiveData = subCatRepo.getSubCatPrefResponse();

         return subCatPrefResponseMutableLiveData;
    }

    public LiveData<FormatPrefResponse> uploadPrefLiveData() {

        if (uploadPrefResponseMutableLiveData == null)
            uploadPrefResponseMutableLiveData = subCatRepo.uploadPrefResponse();

         return uploadPrefResponseMutableLiveData;
    }
}
