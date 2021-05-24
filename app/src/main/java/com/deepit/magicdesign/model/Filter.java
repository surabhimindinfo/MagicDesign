package com.deepit.magicdesign.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Filter {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public String getName() {
        return name;
    }

    public List<Result> getResult() {
        return result;
    }


}
