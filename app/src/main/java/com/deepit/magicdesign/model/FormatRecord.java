package com.deepit.magicdesign.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FormatRecord {
    @SerializedName("format")
    @Expose
    private List<Result> format = null;
    @SerializedName("main_category")
    @Expose
    private List<MainCategory> mainCategory = null;

    @SerializedName("category")
    @Expose
    private List<MainCategory> category = null;
    @SerializedName("subcategory")
    @Expose
    private List<MainCategory> subcategory = null;

    public List<MainCategory> getCategory() {
        return category;
    }

    public List<MainCategory> getSubcategory() {
        return subcategory;
    }

    public List<Result> getFormat() {
        return format;
    }

    public List<MainCategory> getMainCategory() {
        return mainCategory;
    }
}
