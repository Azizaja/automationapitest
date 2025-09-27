package scenario.createBooking;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apitest.base.BaseTest;

import apiengine.BooksColectionAPI;
import io.restassured.response.Response;

public class NegativeCase extends BaseTest {

    @Test
    public void CreateBooking_MissingRequiredField() {
        System.out.println("Negative Test: Missing Required Field");

        String requestBody = """
            {
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
            """;

        Response response = BooksColectionAPI.createBookingAPI(requestBody);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 400, "Should return 400 for missing required fields");
        Assert.assertTrue(response.asString().contains("Bad Request"), "Response should indicate bad request");
        Assert.assertFalse(response.asString().contains("bookingid"), "Response should not contain bookingid");
        Assert.assertFalse(response.asString().contains("booking"), "Response should not contain booking details");
        System.out.println("Missing required field test passed");
    }

    @Test
    public void CreateBooking_InvalidDataType() {
        System.out.println("Negative Test: Invalid Data Type");

        String requestBody = """
            {
                "firstname": "Jim",
                "lastname": "Brown",
                "totalprice": "one hundred eleven",
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
            """;

        Response response = BooksColectionAPI.createBookingAPI(requestBody);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 400, "Should return 400 for invalid data types");
        Assert.assertTrue(response.asString().contains("Bad Request"), "Response should indicate bad request");
        Assert.assertFalse(response.asString().contains("bookingid"), "Response should not contain bookingid");
        Assert.assertFalse(response.asString().contains("booking"), "Response should not contain booking details");
        System.out.println("Invalid data type test passed");
    }

    @Test
    public void CreateBooking_EmptyRequestBody() {
        System.out.println("Negative Test: Empty Request Body");

        String requestBody = "{}";

        Response response = BooksColectionAPI.createBookingAPI(requestBody);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 400, "Should return 400 for empty request body");
        Assert.assertTrue(response.asString().contains("Bad Request"), "Response should indicate bad request");
        Assert.assertFalse(response.asString().contains("bookingid"), "Response should not contain bookingid");
        Assert.assertFalse(response.asString().contains("booking"), "Response should not contain booking details");
        System.out.println("Empty request body test passed");
    }

}
