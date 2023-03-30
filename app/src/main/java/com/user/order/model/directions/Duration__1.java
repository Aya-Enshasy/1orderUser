
package com.user.order.model.directions;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Duration__1 implements Serializable
{

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("value")
    @Expose
    private Integer value;
    private final static long serialVersionUID = -228490046309441062L;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Duration__1 withText(String text) {
        this.text = text;
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Duration__1 withValue(Integer value) {
        this.value = value;
        return this;
    }

}
