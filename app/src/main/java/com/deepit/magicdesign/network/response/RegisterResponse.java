package com.deepit.magicdesign.network.response;

import com.deepit.magicdesign.model.RegisterRecord;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("record")
    @Expose
    private RegisterRecord record;

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

    public RegisterRecord getRecord() {
        return record;
    }

    public void setRecord(RegisterRecord record) {
        this.record = record;
    }
}
