
package com.user.order.model.cancel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelOrderReasons implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("reason_ar")
    @Expose
    private String reasonAr;
    @SerializedName("reason_en")
    @Expose
    private String reasonEn;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("created_at")
    @Expose
    private Integer createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("country_id")
    @Expose
    private Object countryId;
    @SerializedName("reason")
    @Expose
    private String reason;
    private final static long serialVersionUID = 7776488405469988748L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CancelOrderReasons withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getReasonAr() {
        return reasonAr;
    }

    public void setReasonAr(String reasonAr) {
        this.reasonAr = reasonAr;
    }

    public CancelOrderReasons withReasonAr(String reasonAr) {
        this.reasonAr = reasonAr;
        return this;
    }

    public String getReasonEn() {
        return reasonEn;
    }

    public void setReasonEn(String reasonEn) {
        this.reasonEn = reasonEn;
    }

    public CancelOrderReasons withReasonEn(String reasonEn) {
        this.reasonEn = reasonEn;
        return this;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public CancelOrderReasons withDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public CancelOrderReasons withCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CancelOrderReasons withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public CancelOrderReasons withIsActive(Integer isActive) {
        this.isActive = isActive;
        return this;
    }

    public Object getCountryId() {
        return countryId;
    }

    public void setCountryId(Object countryId) {
        this.countryId = countryId;
    }

    public CancelOrderReasons withCountryId(Object countryId) {
        this.countryId = countryId;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public CancelOrderReasons withReason(String reason) {
        this.reason = reason;
        return this;
    }

}
