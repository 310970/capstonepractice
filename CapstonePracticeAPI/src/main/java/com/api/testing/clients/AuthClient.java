package com.api.testing.clients;

import com.api.testing.models.request.LoginRequest;
import io.restassured.response.Response;

public class AuthClient extends BaseClient {

    private static final String LOGIN = "/auth/login";
    private static final String CURRENT_USER = "/auth/me";

    public Response login(LoginRequest request) {
        return post(LOGIN, request);
    }

    public Response getCurrentUser(String token) {
        return get(CURRENT_USER, token);
    }

    public String getAccessToken(LoginRequest request) {
        return login(request)
                .jsonPath()
                .getString("accessToken");
    }

    public String getUsername(String token) {
        return getCurrentUser(token)
                .jsonPath()
                .getString("username");
    }

    public String getEmail(String token) {
        return getCurrentUser(token)
                .jsonPath()
                .getString("email");
    }
}