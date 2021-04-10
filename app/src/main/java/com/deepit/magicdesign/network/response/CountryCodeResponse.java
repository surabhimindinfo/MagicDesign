package com.deepit.magicdesign.network.response;

import com.deepit.magicdesign.model.CountryRecord;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryCodeResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("record")
    @Expose
    private List<CountryRecord> countryRecord = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CountryRecord> getCountryRecord() {
        return countryRecord;
    }

    public void setCountryRecord(List<CountryRecord> countryRecord) {
        this.countryRecord = countryRecord;
    }

}
