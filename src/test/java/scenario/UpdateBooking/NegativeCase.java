package scenario.UpdateBooking;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apitest.base.BaseTest;

import apiengine.BooksColectionAPI;
import io.restassured.response.Response;

public class NegativeCase extends BaseTest{

    @Test
    public void UpdateBooking_InvalidData(){
        int bookingId = 0; // Ganti dengan ID booking yang valid
        String requestBody = """
            {
                "firstname": 12345,
                "lastname": true,
                "totalprice": "one hundred eleven",
                "depositpaid": "yes",
                "bookingdates": {
                    "checkin": "invalid-date",
                    "checkout": "another-invalid-date"
                },
                "additionalneeds": 456
            }
            """;
        System.out.println("Negative Test: Update Booking with Invalid Data Types");
        Response response = BooksColectionAPI.updateBookingAPI(requestBody, bookingId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 400, "Should return 400 for invalid data types");
        Assert.assertTrue(response.asString().contains("Bad Request"), "Response should indicate bad request");
        System.out.println("Update booking with invalid data types test passed");
    }

    @Test
    public void UpdateBooking_EmptyBody(){
        int bookingId = 0; // Ganti dengan ID booking yang valid
        String requestBody = "{}";
        System.out.println("Negative Test: Update Booking with Empty Body");
        Response response = BooksColectionAPI.updateBookingAPI(requestBody, bookingId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 400, "Should return 400 for empty request body");
        Assert.assertTrue(response.asString().contains("Bad Request"), "Response should indicate bad request");
        System.out.println("Update booking with empty body test passed");
    }

}
