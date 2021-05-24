package com.deepit.magicdesign.network;

import com.deepit.magicdesign.network.response.BannerResponse;
import com.deepit.magicdesign.network.response.CountryCodeResponse;
import com.deepit.magicdesign.network.response.FilterResponse;
import com.deepit.magicdesign.network.response.FormatPrefResponse;
import com.deepit.magicdesign.network.response.ItemResponse;
import com.deepit.magicdesign.network.response.MainDesignListResponse;
import com.deepit.magicdesign.network.response.RegisterResponse;
import com.deepit.magicdesign.network.response.SubCategoryResponse;
import com.deepit.magicdesign.network.response.VerifyResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("main_category/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<MainDesignListResponse> getMainDesignList();


    @POST("banner/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<BannerResponse> getBanner();

    @POST("country/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<CountryCodeResponse> getCountryCode();


    @FormUrlEncoded
    @POST("registration/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<RegisterResponse> register(
            @Field("mobile") String mobile,
            @Field("country_id") String country_id,
            @Field("api_type") String api_type,
            @Field("device_id") String device_id

    );

    @FormUrlEncoded
    @POST("login/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<VerifyResponse> login(
            @Field("mobile") String mobile,
            @Field("country_id") String country_id,
            @Field("api_type") String api_type,
            @Field("device_id") String device_id

    );


    @FormUrlEncoded
    @POST("verify_registration/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<VerifyResponse> verifyOTP(
            @Field("name") String name,
            @Field("otp") String otp,
            @Field("mobile") String mobile,
            @Field("country_id") String country_id,
            @Field("api_type") String api_type,
            @Field("device_id") String device_id

    );

    @FormUrlEncoded
    @POST("verify_login/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<VerifyResponse> verifyLoginOTP(
            @Field("user_id") String user_id,
            @Field("otp") String otp,
            @Field("api_type") String api_type,
            @Field("device_id") String device_id

    );

    @FormUrlEncoded
    @POST("profile/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<VerifyResponse> updateProfile(
            @Field("user_id") String user_id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("country_id") String country_id,
            @Field("state") String state,
            @Field("city") String city,
            @Field("area") String area

    );

    @FormUrlEncoded
    @POST("sub_category/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<SubCategoryResponse> getSubCategory(
            @Field("main_category_id") String main_category_id,
            @Field("category_id") String category_id

    );

    @FormUrlEncoded
    @POST("item/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<ItemResponse> getItem(
            @Field("main_category_id") String main_category_id,
            @Field("category_id") String category_id,
            @Field("subcategory_id") String subcategory_id,
            @Field("perpage") int perpage,
            @Field("page") int page,
            @Field("search") String search,
            @Field("filter_id") String filter_id

    );

    @POST("filter_values/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<FilterResponse> getFilter();


    @FormUrlEncoded
    @POST("pref_getformat/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<FormatPrefResponse> getFormatPref(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("pref_getcategory/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<FormatPrefResponse> getCatPref(
            @Field("user_id") String user_id,
            @Field("main_category_id") String main_category_id
    );

    @FormUrlEncoded
    @POST("pref_getsubcategory/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<FormatPrefResponse> getSubCatPref(
            @Field("user_id") String user_id,
            @Field("category_id") String category_id
    );


    @FormUrlEncoded
    @POST("pref_save/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<FormatPrefResponse> savePrefToServer(
            @Field("user_id") String user_id,
            @Field("machine_format_id") String machine_format_id,
            @Field("main_category_id") String main_category_id,
            @Field("category_id") String category_id,
            @Field("subcategory_id") String subcategory_id
    );
    @FormUrlEncoded
    @POST("download/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<FormatPrefResponse> checkDownloadAvailability(
            @Field("item_id") String item_id,
            @Field("user_id") String user_id
    );

}
