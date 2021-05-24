package com.deepit.magicdesign.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Format {
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
}
