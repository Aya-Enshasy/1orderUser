package com.user.order.model.profile;

import com.google.gson.annotations.SerializedName;
import com.user.order.model.User;

public class ProfileData {

    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
