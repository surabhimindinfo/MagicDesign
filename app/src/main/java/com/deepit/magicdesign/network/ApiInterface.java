package com.deepit.magicdesign.network;

import com.deepit.magicdesign.network.response.CountryCodeResponse;
import com.deepit.magicdesign.network.response.MainDesignListResponse;
import com.deepit.magicdesign.network.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("main_category/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<MainDesignListResponse> getMainDesignList();

    @POST("country/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<CountryCodeResponse> getCountryCode();


    @POST("registration/index")
    @Headers("x-api-key: 5eab42f448ef5a5c0722dd6308dd5543")
    Call<RegisterResponse> register(
            @Field("mobile") String mobile,
            @Field("country_id") String country_id,
            @Field("api_type") String api_type,
            @Field("device_id") String device_id

    );
}
