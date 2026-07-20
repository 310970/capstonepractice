package com.api.testing.tests;

import com.api.testing.base.BaseTest;
import com.api.testing.clients.ProductClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.api.testing.models.request.ProductRequest;
import com.api.testing.models.response.ProductResponse;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class ProductTest extends BaseTest {    ProductClient productClient = new ProductClient();

    @Test
    public void getAllProducts() {

        Response response = RestAssured
                .given()
                .when()
                .get("/products");

        // Print Response
        response.prettyPrint();

        // Status Code Validation
        assertEquals(response.getStatusCode(), 200);

        // Response Body Validation
        List<?> products = response.jsonPath().getList("products");

        assertFalse(products.isEmpty());

        // Validate Total
        assertEquals(response.jsonPath().getInt("total"), 194);

        // Validate Skip
        assertEquals(response.jsonPath().getInt("skip"), 0);

        // Validate Limit
        assertEquals(response.jsonPath().getInt("limit"), 30);
    }

    @Test
    public void validateResponseHeaders() {

        Response response = RestAssured
                .given()
                .when()
                .get("/products");

        // Status Code
        assertEquals(response.getStatusCode(), 200);

        // Content-Type
        assertEquals(response.getHeader("Content-Type"),
                "application/json; charset=utf-8");

        // Server Header
        assertEquals(response.getHeader("Server"), "cloudflare");

        // Response Time
        System.out.println("Response Time : " + response.time() + " ms");
    }

    @Test
    public void printAllHeaders() {

        Response response = RestAssured
                .given()
                .when()
                .get("/products");

        response.getHeaders().forEach(System.out::println);
    }
    @Test
    public void getSingleProduct() {

        Response response = RestAssured
                .given()
                .pathParam("id", 1)
                .when()
                .get("/products/{id}");

        response.prettyPrint();

        assertEquals(response.getStatusCode(), 200);

        assertEquals(response.jsonPath().getInt("id"), 1);

        assertEquals(response.jsonPath().getString("title"),
                "Essence Mascara Lash Princess");

        assertEquals(response.jsonPath().getString("category"),
                "beauty");
    }
    @Test
    public void searchProduct() {

        Response response = RestAssured
                .given()
                .queryParam("q", "phone")

                .get("/products/search");

        response.prettyPrint();

        assertEquals(response.getStatusCode(), 200);

        List<?> products = response.jsonPath().getList("products");

        assertFalse(products.isEmpty());

        assertTrue(response.jsonPath()
                .getString("products.title")
                .contains("Apple AirPods Max Silver"));
    }
    @Test
    public void addProductUsingPojo() {

        ProductRequest request = new ProductRequest();
        request.setTitle("BMW Pencil");

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/products/add");

        response.prettyPrint();

        assertEquals(response.getStatusCode(), 201);
        assertEquals(response.jsonPath().getString("title"), "BMW Pencil");
        assertTrue(response.jsonPath().getInt("id") > 0);
    }
    @Test
    public void addProductUsingSerializationAndDeserialization() {

        ProductRequest request = new ProductRequest();
        request.setTitle("BMW Pencil");

        ProductResponse response = RestAssured
                .given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/products/add")
                .as(ProductResponse.class);

        System.out.println("Product Id : " + response.getId());
        System.out.println("Product Title : " + response.getTitle());

        assertTrue(response.getId() > 0);
        assertEquals(response.getTitle(), "BMW Pencil");
    }
    @Test
    public void updateProduct() {

        ProductRequest request = new ProductRequest();
        request.setTitle("iPhone Galaxy +1");

        ProductResponse response = RestAssured
                .given()
                .pathParam("id", 1)
                .contentType("application/json")
                .body(request)
                .when()
                .put("/products/{id}")
                .as(ProductResponse.class);

        System.out.println("Updated Product ID : " + response.getId());
        System.out.println("Updated Product Title : " + response.getTitle());

        assertEquals(response.getId(), 1);
        assertEquals(response.getTitle(), "iPhone Galaxy +1");
    }
    @Test
    public void patchProduct() {

        ProductRequest request = new ProductRequest();
        request.setTitle("Patched iPhone");

        ProductResponse response =
                RestAssured
                        .given()
                        .pathParam("id", 1)
                        .contentType("application/json")
                        .body(request)

                        .when()
                        .patch("/products/{id}")

                        .then()
                        .statusCode(200)
                        .extract()
                        .as(ProductResponse.class);

        System.out.println("Updated Title : " + response.getTitle());

        Assert.assertEquals(response.getId(), 1);
        Assert.assertEquals(response.getTitle(), "Patched iPhone");
    }
    @Test
    public void deleteProduct() {

        ProductResponse response =
                RestAssured
                        .given()
                        .pathParam("id", 1)

                        .when()
                        .delete("/products/{id}")

                        .then()
                        .statusCode(200)
                        .extract()
                        .as(ProductResponse.class);

        System.out.println("Deleted Product ID : " + response.getId());
        System.out.println("Deleted Product Title : " + response.getTitle());
        System.out.println("Is Deleted : " + response.isDeleted());

        Assert.assertEquals(response.getId(), 1);

    }
    @Test
    public void verifyProductById() {

        Response response = productClient.getProductById(5);

        ProductResponse product = response.as(ProductResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(product.getId(), 5);

        System.out.println("Product Name : " + product.getTitle());
    }
    @Test
    public void searchProductTest() {

        Response response = productClient.searchProduct("phone");

        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);
    }
    @Test
    public void validateRequestHeaders() {

        Response response = RestAssured
                .given()
                .header("Accept", "application/json")
                .header("User-Agent", "RestAssured Framework")
                .when()
                .get("/products");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test
    public void multipleHeadersTest() {

        Response response = RestAssured
                .given()
                .headers(
                        "Accept", "application/json",
                        "Content-Type", "application/json"
                )
                .when()
                .get("/products");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

}