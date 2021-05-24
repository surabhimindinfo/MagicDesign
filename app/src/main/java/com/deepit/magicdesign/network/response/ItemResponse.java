package com.deepit.magicdesign.network.response;

import com.deepit.magicdesign.model.Item;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemResponse {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("TotalRecords")
    @Expose
    private Integer totalRecords;
    @SerializedName("TotalPages")
    @Expose
    private Integer totalPages;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("record")
    @Expose
    private List<Item> record = null;

    public Integer getPage() {
        return page;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Item> getRecord() {
        return record;
    }
}
