package com.deepit.magicdesign.repositary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.deepit.magicdesign.network.ApiController;
import com.deepit.magicdesign.network.ApiInterface;
import com.deepit.magicdesign.network.response.FilterResponse;
import com.deepit.magicdesign.network.response.FormatPrefResponse;
import com.deepit.magicdesign.network.response.ItemResponse;
import com.deepit.magicdesign.network.response.SubCategoryResponse;

import retrofit2.Call;
import retrofit2.Response;

public class SubCategoryRepo {

    private final MutableLiveData<SubCategoryResponse> subCategoryResponseMutableLiveData;
    private final MutableLiveData<ItemResponse> itemResponseMutableLiveData;
    private final MutableLiveData<FilterResponse> filterResponseMutableLiveData;
    private final MutableLiveData<FormatPrefResponse> formatPrefResponseMutableLiveData;
    private final MutableLiveData<FormatPrefResponse> catPrefResponseMutableLiveData;
    private final MutableLiveData<FormatPrefResponse> subCatPrefResponseMutableLiveData;
    private final MutableLiveData<FormatPrefResponse> uploadPrefResponseMutableLiveData;
    private final MutableLiveData<FormatPrefResponse> downloadResponseMutableLiveData;
    private final MutableLiveData<Boolean> loadingData ;

    public SubCategoryRepo() {
        subCategoryResponseMutableLiveData = new MutableLiveData<>();
        itemResponseMutableLiveData = new MutableLiveData<>();
        filterResponseMutableLiveData = new MutableLiveData<>();
        formatPrefResponseMutableLiveData = new MutableLiveData<>();
        catPrefResponseMutableLiveData = new MutableLiveData<>();
        subCatPrefResponseMutableLiveData = new MutableLiveData<>();
        uploadPrefResponseMutableLiveData = new MutableLiveData<>();
        downloadResponseMutableLiveData = new MutableLiveData<>();
        loadingData = new MutableLiveData<>();
    }

    public void getSubCat(String main_cat_id, String cat_id) {

        loadingData.setValue(true);
        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<SubCategoryResponse> call = apiInterface.getSubCategory(main_cat_id, cat_id);
        call.enqueue(new retrofit2.Callback<SubCategoryResponse>() {
            @Override
            public void onResponse(Call<SubCategoryResponse> call, Response<SubCategoryResponse> response) {

                SubCategoryResponse response1 = response.body();
                System.out.println("----- response code ----- " + response1.getStatus());
                if (response.body() != null) {
                    System.out.println("---- response success----- " + response.message());
                    subCategoryResponseMutableLiveData.postValue(response.body());
                    loadingData.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<SubCategoryResponse> call, Throwable t) {
                System.out.println("---- response fail----- ");
                t.printStackTrace();
                loadingData.setValue(false);
                subCategoryResponseMutableLiveData.postValue(null);

            }
        });

    }

    public void getItem(String main_category_id, String category_id, String subcategory_id,
                        int perpage, int page, String search, String filterid) {

        System.out.println("main_category_id :" + main_category_id);
        System.out.println("category_id :" + category_id);
        System.out.println("subcategory_id :" + subcategory_id);
        System.out.println("perpage :" + perpage);
        System.out.println("page :" + page);
        System.out.println("filterid :" + filterid);
         loadingData.setValue(true);
        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<ItemResponse> call = apiInterface.getItem(main_category_id, category_id,
                subcategory_id, perpage, page, search, filterid);
        call.enqueue(new retrofit2.Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {

                ItemResponse response1 = response.body();
//                System.out.println("----- response code ----- " + response1.g());
                if (response.body() != null) {
                    System.out.println("---- response success----- " + response.message());
                    itemResponseMutableLiveData.postValue(response.body());
                    loadingData.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                System.out.println("---- response fail----- ");
                t.printStackTrace();
                loadingData.setValue(false);
                itemResponseMutableLiveData.postValue(null);

            }
        });

    }

    public void getFilter() {

         loadingData.setValue(true);
        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<FilterResponse> call = apiInterface.getFilter();
        call.enqueue(new retrofit2.Callback<FilterResponse>() {
            @Override
            public void onResponse(Call<FilterResponse> call, Response<FilterResponse> response) {

                FilterResponse response1 = response.body();
                if (response1 != null) {
                    System.out.println("---- response success----- " + response.message());
                    filterResponseMutableLiveData.postValue(response.body());
                    loadingData.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<FilterResponse> call, Throwable t) {
                System.out.println("---- response fail----- ");
                t.printStackTrace();
                loadingData.setValue(false);
                filterResponseMutableLiveData.postValue(null);

            }
        });

    }

    public void getFormatPref(String userID) {

         loadingData.setValue(true);
        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<FormatPrefResponse> call = apiInterface.getFormatPref(userID);
        call.enqueue(new retrofit2.Callback<FormatPrefResponse>() {
            @Override
            public void onResponse(Call<FormatPrefResponse> call, Response<FormatPrefResponse> response) {

                FormatPrefResponse response1 = response.body();
                if (response1 != null) {
                    System.out.println("---- response success----- " + response.message());
                    loadingData.setValue(false);
                    formatPrefResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FormatPrefResponse> call, Throwable t) {
                System.out.println("---- response fail----- ");
                t.printStackTrace();
                loadingData.setValue(false);
                formatPrefResponseMutableLiveData.postValue(null);

            }
        });

    }
    public void getCatPref(String userID,String main_cat) {

         loadingData.setValue(true);
        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<FormatPrefResponse> call = apiInterface.getCatPref(userID,main_cat);
        call.enqueue(new retrofit2.Callback<FormatPrefResponse>() {
            @Override
            public void onResponse(Call<FormatPrefResponse> call, Response<FormatPrefResponse> response) {

                FormatPrefResponse response1 = response.body();
                if (response1 != null) {
                    System.out.println("---- response success----- " + response.message());
                    loadingData.setValue(false);
                    catPrefResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FormatPrefResponse> call, Throwable t) {
                System.out.println("---- response fail----- ");
                t.printStackTrace();
                loadingData.setValue(false);
                catPrefResponseMutableLiveData.postValue(null);

            }
        });

    }
    public void getSubCatPref(String userID,String main_cat) {

         loadingData.setValue(true);
        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<FormatPrefResponse> call = apiInterface.getSubCatPref(userID,main_cat);
        call.enqueue(new retrofit2.Callback<FormatPrefResponse>() {
            @Override
            public void onResponse(Call<FormatPrefResponse> call, Response<FormatPrefResponse> response) {

                FormatPrefResponse response1 = response.body();
                if (response1 != null) {
                    loadingData.setValue(false);
                    subCatPrefResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FormatPrefResponse> call, Throwable t) {
                t.printStackTrace();
                loadingData.setValue(false);
                subCatPrefResponseMutableLiveData.postValue(null);

            }
        });

    }
    public void savePrefToServer(String userID,String machineId,String main_cat,String catIds,String subCatIds) {

         loadingData.setValue(true);
        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<FormatPrefResponse> call = apiInterface.savePrefToServer(userID,machineId,main_cat,catIds,subCatIds);
        call.enqueue(new retrofit2.Callback<FormatPrefResponse>() {
            @Override
            public void onResponse(Call<FormatPrefResponse> call, Response<FormatPrefResponse> response) {

                FormatPrefResponse response1 = response.body();
                if (response1 != null) {
                    loadingData.setValue(false);
                    uploadPrefResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FormatPrefResponse> call, Throwable t) {
                t.printStackTrace();
                loadingData.setValue(false);
                uploadPrefResponseMutableLiveData.postValue(null);

            }
        });

    }


    public void checkAvailabilityDownload(String itemId,String userId) {

         loadingData.setValue(true);
        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<FormatPrefResponse> call = apiInterface.checkDownloadAvailability(itemId,userId);
        call.enqueue(new retrofit2.Callback<FormatPrefResponse>() {
            @Override
            public void onResponse(Call<FormatPrefResponse> call, Response<FormatPrefResponse> response) {

                FormatPrefResponse response1 = response.body();
                if (response1 != null) {
                    loadingData.setValue(false);
                    downloadResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FormatPrefResponse> call, Throwable t) {
                t.printStackTrace();
                loadingData.setValue(false);
                downloadResponseMutableLiveData.postValue(null);

            }
        });

    }

    public LiveData<SubCategoryResponse> getSubCatResponse() { return subCategoryResponseMutableLiveData; }
    public LiveData<ItemResponse> getItemResponse() { return itemResponseMutableLiveData; }
    public LiveData<FilterResponse> getFilterResponse() { return filterResponseMutableLiveData; }
    public LiveData<FormatPrefResponse> getFormatPrefResponse() { return formatPrefResponseMutableLiveData; }
    public LiveData<FormatPrefResponse> getCatPrefResponse() { return catPrefResponseMutableLiveData; }
    public LiveData<FormatPrefResponse> getSubCatPrefResponse() { return subCatPrefResponseMutableLiveData; }
    public LiveData<FormatPrefResponse> uploadPrefResponse() { return uploadPrefResponseMutableLiveData; }
    public LiveData<FormatPrefResponse> downloadResponse() { return downloadResponseMutableLiveData; }

    public LiveData<Boolean> getLoading() {
        return loadingData;
    }
}
