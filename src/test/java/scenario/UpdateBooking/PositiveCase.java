package scenario.UpdateBooking;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apitest.base.BaseTest;
import apiengine.BooksColectionAPI;
import io.restassured.response.Response;

public class PositiveCase {

    // @Test(dependsOnGroups = {"bookingFlow"})
    @Test   
    public void UpdateBooking() {
        System.out.println("Update Booking");
        
        // Debug info - pastikan bookingId dan token sudah ada
        System.out.println("Booking ID from BaseTest: " + BaseTest.getBookingId());
        System.out.println("Token from BaseTest: " + BaseTest.token);
        
        if (BaseTest.getBookingId() == 0) {
            throw new RuntimeException("Booking ID is 0! Create booking test must run first.");
        }
        
        String requestBody = """
            {
                "firstname": "James",
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
            
        Response response = BooksColectionAPI.updateBookingAPI(requestBody);
        
        // Debug response
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertEquals(response.jsonPath().getString("firstname"), "James", "Firstname is not James");
        System.out.println("Update Booking Selesai");
    }
}