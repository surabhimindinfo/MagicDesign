package com.deepit.magicdesign.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class File {
    @SerializedName("file_id")
    @Expose
    private String fileId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("file_200")
    @Expose
    private String file200;
    @SerializedName("file")
    @Expose
    private String file;

    public String getFileId() {
        return fileId;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getFormat() {
        return format;
    }

    public String getFile200() {
        return file200;
    }

    public String getFile() {
        return file;
    }
}
