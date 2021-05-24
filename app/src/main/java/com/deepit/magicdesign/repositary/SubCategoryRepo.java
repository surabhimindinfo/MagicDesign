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

    public SubCategoryRepo() {
        subCategoryResponseMutableLiveData = new MutableLiveData<>();
        itemResponseMutableLiveData = new MutableLiveData<>();
        filterResponseMutableLiveData = new MutableLiveData<>();
        formatPrefResponseMutableLiveData = new MutableLiveData<>();
        catPrefResponseMutableLiveData = new MutableLiveData<>();
        subCatPrefResponseMutableLiveData = new MutableLiveData<>();
        uploadPrefResponseMutableLiveData = new MutableLiveData<>();
        downloadResponseMutableLiveData = new MutableLiveData<>();
    }

    public void getSubCat(String main_cat_id, String cat_id) {


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
                }
            }

            @Override
            public void onFailure(Call<SubCategoryResponse> call, Throwable t) {
                System.out.println("---- response fail----- ");
                t.printStackTrace();

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
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                System.out.println("---- response fail----- ");
                t.printStackTrace();

                itemResponseMutableLiveData.postValue(null);

            }
        });

    }

    public void getFilter() {


        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<FilterResponse> call = apiInterface.getFilter();
        call.enqueue(new retrofit2.Callback<FilterResponse>() {
            @Override
            public void onResponse(Call<FilterResponse> call, Response<FilterResponse> response) {

                FilterResponse response1 = response.body();
                if (response1 != null) {
                    System.out.println("---- response success----- " + response.message());
                    filterResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FilterResponse> call, Throwable t) {
                System.out.println("---- response fail----- ");
                t.printStackTrace();

                filterResponseMutableLiveData.postValue(null);

            }
        });

    }

    public void getFormatPref(String userID) {


        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<FormatPrefResponse> call = apiInterface.getFormatPref(userID);
        call.enqueue(new retrofit2.Callback<FormatPrefResponse>() {
            @Override
            public void onResponse(Call<FormatPrefResponse> call, Response<FormatPrefResponse> response) {

                FormatPrefResponse response1 = response.body();
                if (response1 != null) {
                    System.out.println("---- response success----- " + response.message());
                    formatPrefResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FormatPrefResponse> call, Throwable t) {
                System.out.println("---- response fail----- ");
                t.printStackTrace();

                formatPrefResponseMutableLiveData.postValue(null);

            }
        });

    }
    public void getCatPref(String userID,String main_cat) {


        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<FormatPrefResponse> call = apiInterface.getCatPref(userID,main_cat);
        call.enqueue(new retrofit2.Callback<FormatPrefResponse>() {
            @Override
            public void onResponse(Call<FormatPrefResponse> call, Response<FormatPrefResponse> response) {

                FormatPrefResponse response1 = response.body();
                if (response1 != null) {
                    System.out.println("---- response success----- " + response.message());
                    catPrefResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FormatPrefResponse> call, Throwable t) {
                System.out.println("---- response fail----- ");
                t.printStackTrace();

                catPrefResponseMutableLiveData.postValue(null);

            }
        });

    }
    public void getSubCatPref(String userID,String main_cat) {


        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<FormatPrefResponse> call = apiInterface.getSubCatPref(userID,main_cat);
        call.enqueue(new retrofit2.Callback<FormatPrefResponse>() {
            @Override
            public void onResponse(Call<FormatPrefResponse> call, Response<FormatPrefResponse> response) {

                FormatPrefResponse response1 = response.body();
                if (response1 != null) {
                    subCatPrefResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FormatPrefResponse> call, Throwable t) {
                t.printStackTrace();
                subCatPrefResponseMutableLiveData.postValue(null);

            }
        });

    }
    public void savePrefToServer(String userID,String machineId,String main_cat,String catIds,String subCatIds) {


        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<FormatPrefResponse> call = apiInterface.savePrefToServer(userID,machineId,main_cat,catIds,subCatIds);
        call.enqueue(new retrofit2.Callback<FormatPrefResponse>() {
            @Override
            public void onResponse(Call<FormatPrefResponse> call, Response<FormatPrefResponse> response) {

                FormatPrefResponse response1 = response.body();
                if (response1 != null) {
                    uploadPrefResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FormatPrefResponse> call, Throwable t) {
                t.printStackTrace();

                uploadPrefResponseMutableLiveData.postValue(null);

            }
        });

    }


    public void checkAvailabilityDownload(String itemId,String userId) {


        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<FormatPrefResponse> call = apiInterface.checkDownloadAvailability(itemId,userId);
        call.enqueue(new retrofit2.Callback<FormatPrefResponse>() {
            @Override
            public void onResponse(Call<FormatPrefResponse> call, Response<FormatPrefResponse> response) {

                FormatPrefResponse response1 = response.body();
                if (response1 != null) {
                    downloadResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FormatPrefResponse> call, Throwable t) {
                t.printStackTrace();

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
}
