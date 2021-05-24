package com.deepit.magicdesign.network.response;

import com.deepit.magicdesign.model.Filter;
import com.deepit.magicdesign.model.Height;
import com.deepit.magicdesign.model.Needle;
import com.deepit.magicdesign.model.Stitch;
import com.deepit.magicdesign.model.Width;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("record")
    @Expose
    private List<Filter> record = null;

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Filter> getRecord() {
        return record;
    }



}
