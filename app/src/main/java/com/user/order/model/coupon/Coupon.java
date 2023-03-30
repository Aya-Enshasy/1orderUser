
package com.user.order.model.coupon;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coupon implements Serializable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("discount_value")
    @Expose
    private Integer discountValue;
    @SerializedName("coupon_id")
    @Expose
    private Integer couponId;
    private final static long serialVersionUID = 1226456462243765568L;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Coupon withSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Coupon withMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Integer discountValue) {
        this.discountValue = discountValue;
    }

    public Coupon withDiscountValue(Integer discountValue) {
        this.discountValue = discountValue;
        return this;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Coupon withCouponId(Integer couponId) {
        this.couponId = couponId;
        return this;
    }

}
