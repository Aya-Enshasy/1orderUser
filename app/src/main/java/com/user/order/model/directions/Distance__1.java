
package com.user.order.model.directions;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Distance__1 implements Serializable
{

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("value")
    @Expose
    private Integer value;
    private final static long serialVersionUID = 2843207480357578430L;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Distance__1 withText(String text) {
        this.text = text;
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Distance__1 withValue(Integer value) {
        this.value = value;
        return this;
    }

}
