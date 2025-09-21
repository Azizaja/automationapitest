package com.apitest.utils;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class TokenManager {
    public static String token;

    public static String getToken() {

        if (token == null) {
            token = generateToken();
            
        }
        return token;
    }

    public static String generateToken() {
        System.out.println("Get Auth Token");

        String requestBody = """
                {
                   "username" : "admin",
                   "password" : "password123"
                }""";

        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/auth")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();
        token = response.jsonPath().getString("token");
        // BaseTest.setToken(token);
        System.out.println("Token: " + token);
        System.out.println("Get Auth Token Selesai");
        return token;
    }
}
