package com.deepit.magicdesign.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRecord {
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("country_id")
        @Expose
        private String countryId;
        @SerializedName("otp")
        @Expose
        private Integer otp;
        @SerializedName("create_date")
        @Expose
        private String createDate;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
