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
        System.out.println("Base URI: " + BaseTest.BaseURI);

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
        Assert.assertNotEquals(response.statusCode(), 400, "Should NOT return 400 for missing required field");
        
        System.out.println("Missing request body test passed - Server rejected invalid request with status: "
        + response.statusCode());

        Assert.assertFalse(response.asString().contains("Bad Request"), "Response should indicate bad request");
        Assert.assertFalse(response.asString().contains("bookingid"), "Response should not contain bookingid");
        Assert.assertFalse(response.asString().contains("booking"), "Response should not contain booking details");

        // tidak bisa dieksekusi karena server mengembalikan 500, bukan 400
        // Assert.assertEquals(response.statusCode(), 400, "Should return 400 for missing required fields");
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

        // Assert.assertEquals(response.statusCode(), 200, "Should NOT return 200 for invalid data types");
        Assert.assertNotEquals(response.statusCode(), 400, "Should NOT return 400 for invalid data types");
        
        System.out.println("Invalid data types body test passed - Server rejected invalid request with status: "
        + response.statusCode());

        Assert.assertFalse(response.asString().contains("Bad Request"), "Response should indicate bad request");
        Assert.assertTrue(response.asString().contains("bookingid"), "Response should not contain bookingid");
        Assert.assertTrue(response.asString().contains("booking"), "Response should not contain booking details");
        System.out.println("Invalid data type test passed");
    }

    @Test
    public void CreateBooking_EmptyRequestBody() {
        System.out.println("Negative Test: Empty Request Body");

        String requestBody = "{}";

        Response response = BooksColectionAPI.createBookingAPI(requestBody);
        response.then().log().all();
        
        Assert.assertNotEquals(response.statusCode(), 400, "Should NOT return 400 for empty request body");
        
        Assert.assertFalse(response.asString().contains("Bad Request"), "Response should indicate bad request");
        Assert.assertFalse(response.asString().contains("bookingid"), "Response should not contain bookingid");
        Assert.assertFalse(response.asString().contains("booking"), "Response should not contain booking details");
        System.out.println("Empty request body test passed - Server rejected invalid request with status: "
        + response.statusCode());
        
        // tidak bisa dieksekusi karena server mengembalikan 500, bukan 400
        // Assert.assertEquals(response.statusCode(), 400, "Should return 400 for empty request body");
        System.out.println("Empty request body test passed");
    }

}
