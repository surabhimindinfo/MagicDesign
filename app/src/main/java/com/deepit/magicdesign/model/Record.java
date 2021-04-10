package com.deepit.magicdesign.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Record {

    @SerializedName("main_category_id")
    @Expose
    private String mainCategoryId;
    @SerializedName("main_name")
    @Expose
    private String mainName;
    @SerializedName("main_image")
    @Expose
    private String mainImage;
    @SerializedName("main_short_desc")
    @Expose
    private String mainShortDesc;
    @SerializedName("cat_image")
    @Expose
    private String catImage;
    @SerializedName("category")
    @Expose
    private List<Category> category = null;

    public String getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(String mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getMainShortDesc() {
        return mainShortDesc;
    }

    public void setMainShortDesc(String mainShortDesc) {
        this.mainShortDesc = mainShortDesc;
    }

    public String getCatImage() {
        return catImage;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }
}
