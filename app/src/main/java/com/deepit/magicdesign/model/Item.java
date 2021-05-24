package com.deepit.magicdesign.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item implements Parcelable {
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("item_code")
    @Expose
    private String itemCode;
    @SerializedName("stitch")
    @Expose
    private String stitch;
    @SerializedName("needle")
    @Expose
    private String needle;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("short_desc")
    @Expose
    private String shortDesc;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("files")
    @Expose
    private List<File> files = null;

    public Item(Parcel in) {
        itemId = in.readString();
        name = in.readString();
        itemCode = in.readString();
        stitch = in.readString();
        needle = in.readString();
        width = in.readString();
        height = in.readString();
        unit = in.readString();
        shortDesc = in.readString();
        comment = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getStitch() {
        return stitch;
    }

    public String getNeedle() {
        return needle;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getUnit() {
        return unit;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getComment() {
        return comment;
    }

    public List<File> getFiles() {
        return files;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemId);
        dest.writeString(name);
        dest.writeString(itemCode);
        dest.writeString(stitch);
        dest.writeString(needle);
        dest.writeString(width);
        dest.writeString(height);
        dest.writeString(unit);
        dest.writeString(shortDesc);
        dest.writeString(comment);
    }
}
