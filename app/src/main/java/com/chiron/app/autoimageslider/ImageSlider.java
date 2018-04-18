package com.chiron.app.autoimageslider;

import android.support.annotation.DrawableRes;

/**
 * Created by appyworld on 13/04/18.
 */

public class ImageSlider {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    //optional @DrawableRes
    @DrawableRes
    private int resId;

    public ImageSlider(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    @Override
    public String toString() {
        return name;
    }
}

