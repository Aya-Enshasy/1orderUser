
package com.user.order.model.rating;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingsData implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("content_id")
    @Expose
    private String contentId;
    @SerializedName("content_type")
    @Expose
    private String contentType;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("created_timestamp")
    @Expose
    private String createdTimestamp;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("user")
    @Expose
    private User user;
    private final static long serialVersionUID = -6522034060268034964L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RatingsData withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public RatingsData withContentId(String contentId) {
        this.contentId = contentId;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public RatingsData withContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public RatingsData withRate(String rate) {
        this.rate = rate;
        return this;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public RatingsData withReview(String review) {
        this.review = review;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public RatingsData withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RatingsData withType(String type) {
        this.type = type;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public RatingsData withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public RatingsData withCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public RatingsData withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public RatingsData withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RatingsData withUser(User user) {
        this.user = user;
        return this;
    }

}
