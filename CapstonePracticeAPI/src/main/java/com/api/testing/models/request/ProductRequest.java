package com.api.testing.models.request;

public class ProductRequest {

    private String title;

    public ProductRequest() {
    }

    public ProductRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}