package com.deepit.magicdesign.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("cat_short_desc")
    @Expose
    private String catShortDesc;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatShortDesc() {
        return catShortDesc;
    }

    public void setCatShortDesc(String catShortDesc) {
        this.catShortDesc = catShortDesc;
    }

}
