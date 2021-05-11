package com.deepit.magicdesign.repositary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.deepit.magicdesign.network.ApiController;
import com.deepit.magicdesign.network.ApiInterface;
import com.deepit.magicdesign.network.response.SubCategoryResponse;
import com.deepit.magicdesign.network.response.SubCategoryResponse;

import retrofit2.Call;
import retrofit2.Response;

public class SubCategoryRepo {

    private MutableLiveData<SubCategoryResponse> subCategoryResponseMutableLiveData;

    public SubCategoryRepo()
    {
        subCategoryResponseMutableLiveData=new MutableLiveData<>();
    }

    public void getSubCat(String main_cat_id,String cat_id) {


        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
        Call<SubCategoryResponse> call = apiInterface.getSubCategory(main_cat_id,cat_id);
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

    public LiveData<SubCategoryResponse> getSubCatResponse() {

        System.out.println("---- design obj in repo --- " + subCategoryResponseMutableLiveData);

        return subCategoryResponseMutableLiveData;
    }
}
