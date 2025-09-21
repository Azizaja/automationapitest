package com.apitest.base;

import static io.restassured.RestAssured.given;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.apitest.utils.TokenManager;

import io.restassured.RestAssured;

public class BaseTest {
    public static String token, baseURI;
    // public static int bookingId;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite: Setting up base URI and token");
        token = TokenManager.getToken();
        baseURI = "https://restful-booker.herokuapp.com";
    }

    @BeforeMethod
    public void setupRequestSpecification(){
        System.out.println("This is Before Method");
        RestAssured.requestSpecification = given()
                                            .baseUri(baseURI)
                                            .header("Content-Type", "application/json")
                                            .header("Cookie", "token=" + token);
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("This is After Method");
        if (RestAssured.requestSpecification != null) {
            RestAssured.requestSpecification = null;
        }
    }

}
