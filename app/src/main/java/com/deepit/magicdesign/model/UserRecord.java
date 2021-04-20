package com.deepit.magicdesign.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRecord implements Parcelable {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("country_name")
    @Expose
    private String country_name;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("update_date")
    @Expose
    private String updateDate;



    protected UserRecord(Parcel in) {
        userId = in.readString();
        name = in.readString();
        email = in.readString();
        mobile = in.readString();
        country_name = in.readString();
        countryId = in.readString();
        state = in.readString();
        city = in.readString();
        area = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
    }

    public static final Creator<UserRecord> CREATOR = new Creator<UserRecord>() {
        @Override
        public UserRecord createFromParcel(Parcel in) {
            return new UserRecord(in);
        }

        @Override
        public UserRecord[] newArray(int size) {
            return new UserRecord[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getCountry_name() {
        return country_name;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userId);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(mobile);
        parcel.writeString(countryId);
        parcel.writeString(state);
        parcel.writeString(city);
        parcel.writeString(area);
        parcel.writeString(createDate);
        parcel.writeString(updateDate);
        parcel.writeString(country_name);
    }
}
