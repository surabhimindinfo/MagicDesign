package com.deepit.magicdesign.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Record implements Parcelable {
    public static final Creator<Record> CREATOR = new Creator<Record>() {
        @Override
        public Record createFromParcel(Parcel in) {
            return new Record(in);
        }

        @Override
        public Record[] newArray(int size) {
            return new Record[size];
        }
    };
    @SerializedName("subcategory_id")
    @Expose
    private final String subcategoryId;
    @SerializedName("sub_name")
    @Expose
    private final String subName;
    @SerializedName("sub_image")
    @Expose
    private final String subImage;
    @SerializedName("sub_short_desc")
    @Expose
    private final String subShortDesc;
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

    @SerializedName("category")
    @Expose
    private List<Category> category = null;
    protected Record(Parcel in) {
        subcategoryId = in.readString();
        subName = in.readString();
        subImage = in.readString();
        subShortDesc = in.readString();
        mainCategoryId = in.readString();
        mainName = in.readString();
        mainImage = in.readString();
        mainShortDesc = in.readString();
     }

    public String getSubcategoryId() {
        return subcategoryId;
    }

    public String getSubName() {
        return subName;
    }

    public String getSubImage() {
        return subImage;
    }

    public String getSubShortDesc() {
        return subShortDesc;
    }

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



    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(subcategoryId);
        dest.writeString(subName);
        dest.writeString(subImage);
        dest.writeString(subShortDesc);
        dest.writeString(mainCategoryId);
        dest.writeString(mainName);
        dest.writeString(mainImage);
        dest.writeString(mainShortDesc);
     }
}
