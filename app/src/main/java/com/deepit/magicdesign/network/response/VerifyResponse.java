package com.deepit.magicdesign.network.response;

import com.deepit.magicdesign.model.UserRecord;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("record")
    @Expose
    private UserRecord record;

    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("otp")
    @Expose
    private String otp;

    public Integer getStatus() {
        return status;
    }


    public String getMessage() {
        return message;
    }


    public UserRecord getRecord() {
        return record;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getOtp() {
        return otp;
    }
}
