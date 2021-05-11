package com.deepit.magicdesign.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("cat_short_desc")
    @Expose
    private String catShortDesc;
    @SerializedName("cat_image")
    @Expose
    private String catImage;
    @SerializedName("main_category_id")
    @Expose
    private String main_category_id;

    protected Category(Parcel in) {
        categoryId = in.readString();
        catName = in.readString();
        catShortDesc = in.readString();
        catImage = in.readString();
        main_category_id = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getCategoryId() {
        return categoryId;
    }
    public String getCatName() {
        return catName;
    }
    public String getCatShortDesc() {
        return catShortDesc;
    }

    public String getMain_category_id() {
        return main_category_id;
    }

    public String getCatImage() {
        return catImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(categoryId);
        dest.writeString(catName);
        dest.writeString(catShortDesc);
        dest.writeString(catImage);
        dest.writeString(main_category_id);
    }

}
