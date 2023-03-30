package com.user.order.model.auth;

import com.google.gson.annotations.SerializedName;
import com.user.order.model.User;

public class AuthData {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("tokenType")
    private String token_type;

    @SerializedName("user")
    private User user;

    @SerializedName("expires_at")
    private String expiresAt;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }
}
