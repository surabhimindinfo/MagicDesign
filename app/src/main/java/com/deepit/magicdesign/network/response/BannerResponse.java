package com.deepit.magicdesign.network.response;

import com.deepit.magicdesign.model.Banner;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannerResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("record")
    @Expose
    private List<Banner> record = null;

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Banner> getRecord() {
        return record;
    }
}
