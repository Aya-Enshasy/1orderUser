package com.user.order.model.map.publicShops;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OpeningHours implements Serializable {

    @SerializedName("open_now")
    private boolean isOpenNow;

    public boolean isOpenNow() {
        return isOpenNow;
    }

    public void setOpenNow(boolean openNow) {
        isOpenNow = openNow;
    }
}
