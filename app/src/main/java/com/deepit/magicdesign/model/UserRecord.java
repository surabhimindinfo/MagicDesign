package com.deepit.magicdesign.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRecord implements Parcelable {

    private static UserRecord userRecord=null;

    private UserRecord( ) {


    }

    public static UserRecord getUserRecord() {
        return userRecord;
    }

    public static void setUserRecord(UserRecord userRecord) {
        UserRecord.userRecord = userRecord;
    }

    public static UserRecord getInstance()
    {
        if (userRecord==null)
            userRecord=new UserRecord();

        return userRecord;
    }
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("country_id")
    @Expose
    public String countryId;


    @SerializedName("country_name")
    @Expose
    public String countryName;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("area")
    @Expose
    public String area;
    @SerializedName("create_date")
    @Expose
    public String createDate;
    @SerializedName("update_date")
    @Expose
    public String updateDate;
    @SerializedName("phonecode")
    @Expose
    public String phoneCode;



    protected UserRecord(Parcel in) {
        userId = in.readString();
        name = in.readString();
        email = in.readString();
        mobile = in.readString();
        countryId = in.readString();
        countryName = in.readString();
        state = in.readString();
        city = in.readString();
        area = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
        phoneCode = in.readString();
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

    public String getCountryName() {
        return countryName ;
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

    public String getPhoneCode() {
        return phoneCode;
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
        parcel.writeString(countryName);
        parcel.writeString(state);
        parcel.writeString(city);
        parcel.writeString(area);
        parcel.writeString(createDate);
        parcel.writeString(updateDate);
        parcel.writeString(phoneCode);

    }
}
