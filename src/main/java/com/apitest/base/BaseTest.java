package com.apitest.base;

import org.testng.annotations.AfterSuite;

import static io.restassured.RestAssured.given;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.apitest.utils.Helper;
import com.apitest.utils.TokenManager;

import io.restassured.RestAssured;

public class BaseTest {
    public static String token, BaseURI;
    public static int bookingId;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite: Setting up base URI and token");
        token = TokenManager.getToken();
        BaseURI = Helper.getEnv("BASE_URI");
    }

    // untuk set request specification
    // supaya tidak perlu set berulang di setiap test
    @BeforeMethod
    public void setupRequestSpecification() {
        System.out.println("This is Before Method");
        System.out.println("Current Booking ID: " + bookingId);
        if (bookingId == 0) {
            System.out.println("Warning: Booking ID is 0. Create booking test should run first.");
        }
        RestAssured.requestSpecification = given()
                .baseUri(BaseURI)
                // .basePath("/booking")
                .header("Content-Type", "application/json");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("This is After Method");
        if (RestAssured.requestSpecification != null) {
            RestAssured.requestSpecification = null;
        }
    }

    @AfterSuite
    public void cleanup() {
        bookingId = 0; // Reset setelah semua test selesai
        token = null;
        System.out.println("After Suite: Cleanup done");
    }

    // setter getter dikomen agar tiap test case lebih eksplisit passing id
    // dan tidak tergantung state dari test lain (jika dalam satu class skenario lebih
    // dari satu test case)
    
    // set id booking
    // public static void setBookingId(int id) {
    //     bookingId = id;
    // }

    // // get id booking
    // public static int getBookingId() {
    //     return bookingId;
    // }
}
