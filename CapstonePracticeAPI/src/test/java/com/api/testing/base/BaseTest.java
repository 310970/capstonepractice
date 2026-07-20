package com.api.testing.base;

import com.api.testing.utils.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup() {

        RestAssured.baseURI = ConfigReader.get("base.url");
    }
}