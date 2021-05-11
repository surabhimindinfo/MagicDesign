package com.deepit.magicdesign.network.response;

import com.deepit.magicdesign.model.Record;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCategoryResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("record")
    @Expose
    private List<Record> record = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Record> getSubCategory() {
        return record;
    }

    public void setSubCategory(List<Record> record) {
        this.record = record;
    }
}
