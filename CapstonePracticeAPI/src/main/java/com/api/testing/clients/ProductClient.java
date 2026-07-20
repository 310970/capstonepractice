package com.api.testing.clients;

import com.api.testing.models.request.ProductRequest;
import io.restassured.response.Response;

public class ProductClient extends BaseClient {

    private static final String PRODUCTS = "/products";

    public Response getAllProducts() {
        return get(PRODUCTS);
    }

    public Response getProduct(int id) {
        return get(PRODUCTS + "/" + id);
    }

    // Path Parameter (URL built manually)
    public Response getProductById(int id) {
        return get(PRODUCTS + "/" + id);
    }

    // Query Parameter
    public Response searchProduct(String keyword) {
        return get(PRODUCTS + "/search?q=" + keyword);
    }

    public Response addProduct(ProductRequest request) {
        return post(PRODUCTS + "/add", request);
    }

    public Response updateProduct(int id, ProductRequest request) {
        return put(PRODUCTS + "/" + id, request);
    }

    public Response deleteProduct(int id) {
        return delete(PRODUCTS + "/" + id);
    }
}