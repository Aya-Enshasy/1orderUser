package com.user.order.model.map.publicShops;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlusCode implements Serializable {

    @SerializedName("compound_code")
    private String compoundCode;

    @SerializedName("global_code")
    private String globalCode;

    public String getCompoundCode() {
        return compoundCode;
    }

    public void setCompoundCode(String compoundCode) {
        this.compoundCode = compoundCode;
    }

    public String getGlobalCode() {
        return globalCode;
    }

    public void setGlobalCode(String globalCode) {
        this.globalCode = globalCode;
    }
}
