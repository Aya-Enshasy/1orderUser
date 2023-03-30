package com.user.order.model.notifications;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("created_at")
    private long createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("data")
    private NotificationData notification;

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public NotificationData getNotification() {
        return notification;
    }

    public void setNotification(NotificationData notification) {
        this.notification = notification;
    }
}
