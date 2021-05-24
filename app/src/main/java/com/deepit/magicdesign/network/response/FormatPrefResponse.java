package com.deepit.magicdesign.network.response;

import com.deepit.magicdesign.model.FormatRecord;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormatPrefResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("record")
    @Expose
    private FormatRecord record;

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public FormatRecord getRecord() {
        return record;
    }
}
