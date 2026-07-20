package com.api.testing.models.request;

public class LoginRequest {

    private String username;
    private String password;
    private int expiresInMins;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password, int expiresInMins) {
        this.username = username;
        this.password = password;
        this.expiresInMins = expiresInMins;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getExpiresInMins() {
        return expiresInMins;
    }

    public void setExpiresInMins(int expiresInMins) {
        this.expiresInMins = expiresInMins;
    }
}