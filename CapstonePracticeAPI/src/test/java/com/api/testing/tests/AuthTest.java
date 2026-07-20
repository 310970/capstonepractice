package com.api.testing.tests;

import com.api.testing.clients.AuthClient;
import com.api.testing.models.request.LoginRequest;
import com.api.testing.models.response.LoginResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTest {

    @Test
    public void loginTest() {

        LoginRequest request =
                new LoginRequest("emilys", "emilyspass", 30);

        LoginResponse response =
                RestAssured
                        .given()
                        .baseUri("https://dummyjson.com")
                        .contentType(ContentType.JSON)
                        .body(request)
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(LoginResponse.class);

        System.out.println("User ID      : " + response.getId());
        System.out.println("Username     : " + response.getUsername());
        System.out.println("Email        : " + response.getEmail());
        System.out.println("Access Token : " + response.getAccessToken());

        Assert.assertEquals(response.getUsername(), "emilys");
        Assert.assertNotNull(response.getAccessToken());
    }
    @Test
    public void getCurrentUserTest() {

        LoginRequest request =
                new LoginRequest("emilys", "emilyspass", 30);

        AuthClient authClient = new AuthClient();

        String token = authClient.login(request)
                .jsonPath()
                .getString("accessToken");

        System.out.println("Token : " + token);

        authClient.getCurrentUser(token)
                .then()
                .statusCode(200)
                .log()
                .body();
    }
    @Test
    public void verifyCurrentUser() {

        LoginRequest request =
                new LoginRequest("emilys", "emilyspass", 30);

        AuthClient authClient = new AuthClient();

        String token = authClient.getAccessToken(request);

        System.out.println("Username : " + authClient.getUsername(token));
        System.out.println("Email    : " + authClient.getEmail(token));
    }
}