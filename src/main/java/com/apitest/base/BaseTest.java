package com.apitest.base;

import org.testng.annotations.AfterSuite;

// import static io.restassured.RestAssured.given;

// import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.apitest.utils.Helper;
import com.apitest.utils.TokenManager;

// import io.restassured.RestAssured;

public class BaseTest {
    public static String token, BaseURI;
    public static int bookingId;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite: Setting up base URI and token");
        token = TokenManager.getToken();
        BaseURI = Helper.getEnv("BASE_URI");
    }

    @BeforeMethod
    public void checkBookingId() {
        System.out.println("Current Booking ID: " + bookingId);
        if (bookingId == 0) {
            System.out.println("Warning: Booking ID is 0. Create booking test should run first.");
        }
    }

    // @BeforeMethod
    // public void setupRequestSpecification(){
    // System.out.println("This is Before Method");
    // RestAssured.requestSpecification = given()
    // .baseUri(baseURI)
    // .header("Content-Type", "application/json")
    // .header("Cookie", "token=" + token);
    // }

    // @AfterMethod
    // public void afterMethod() {
    // System.out.println("This is After Method");
    // if (RestAssured.requestSpecification != null) {
    // RestAssured.requestSpecification = null;
    // }
    // }

    // set id booking
    public static void setBookingId(int id) {
        bookingId = id;
    }

    // get id booking
    public static int getBookingId() {
        return bookingId;
    }

    @AfterSuite
    public void cleanup() {
        bookingId = 0; // Reset setelah semua test selesai
        token = null;
    }
}
