package com.deepit.magicdesign.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainCategory {
    @SerializedName("main_category_id")
    @Expose
    private String mainCategoryId;
    @SerializedName("subcategory_id")
    @Expose
    private String subcategoryId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("checked")
    @Expose
    private Integer checked;

    public String getSubcategoryId() {
        return subcategoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getMainCategoryId() {
        return mainCategoryId;
    }

    public String getName() {
        return name;
    }

    public Integer getChecked() {
        return checked;
    }
}
