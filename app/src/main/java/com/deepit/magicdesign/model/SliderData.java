package com.deepit.magicdesign.model;

public class SliderData {
    // image url is used to
    // store the url of image
    private String imgUrl;
    private int imgDrawable;

    // Constructor method.
    public SliderData(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public SliderData(int imgDrawable)
    {
        this.imgDrawable=imgDrawable;
    }

    public int getImgDrawable() {
        return imgDrawable;
    }

    public void setImgDrawable(int imgDrawable) {
        this.imgDrawable = imgDrawable;
    }

    // Getter method
    public String getImgUrl() {
        return imgUrl;
    }

    // Setter method
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
