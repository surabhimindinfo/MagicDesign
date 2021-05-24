package com.deepit.magicdesign.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("machine_format_id")
    @Expose
    private String machineFormatId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("checked")
    @Expose
    private Integer checked;

    public String getMachineFormatId() {
        return machineFormatId;
    }

    public String getName() {
        return name;
    }

    public Integer getChecked() {
        return checked;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
