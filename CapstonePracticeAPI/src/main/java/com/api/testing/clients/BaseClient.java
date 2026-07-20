package com.api.testing.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseClient {

    protected static final String BASE_URL = "https://dummyjson.com";

    protected Response get(String endpoint) {

        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .get(endpoint);
    }

    protected Response get(String endpoint, String token) {

        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .get(endpoint);
    }

    protected Response post(String endpoint, Object body) {

        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .post(endpoint);
    }

    protected Response put(String endpoint, Object body) {

        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .put(endpoint);
    }

    protected Response delete(String endpoint) {

        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .delete(endpoint);
    }
}