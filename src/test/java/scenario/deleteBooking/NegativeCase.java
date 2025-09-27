package scenario.deleteBooking;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apitest.base.BaseTest;

import apiengine.BooksColectionAPI;
import io.restassured.response.Response;

public class NegativeCase extends BaseTest{
    @Test
    public void DeleteBooking_WithoutAuthentication() {
        System.out.println("Negative Test: Delete Booking Without Token");
        
        Response response = BooksColectionAPI.deleteBookingWithoutToken(1);
        
        Assert.assertEquals(response.statusCode(), 403);
        System.out.println("✓ Delete without authentication properly rejected");
    }

    @Test
    public void DeleteBooking_NonExistentID() {
        System.out.println("Negative Test: Delete Non-Existent Booking");
        
        Response response = BooksColectionAPI.deleteBookingWithId(999999);
        
        Assert.assertEquals(response.statusCode(), 405);
        System.out.println("✓ Delete non-existent booking properly handled");
    }

    @Test
    public void DeleteBooking_AlreadyDeleted() {
        System.out.println("Negative Test: Delete Already Deleted Booking");
        
        // First create and delete a booking
        String createBody = """
            {
                "firstname": "Temp",
                "lastname": "User",
                "totalprice": 100,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2024-01-01",
                    "checkout": "2024-01-02"
                }
            }
            """;
            
        Response createResponse = BooksColectionAPI.createBookingAPI(createBody);
        int tempBookingId = createResponse.jsonPath().getInt("bookingid");
        
        // Delete it first
        BooksColectionAPI.deleteBookingWithId(tempBookingId);
        
        // Try to delete again
        Response secondDeleteResponse = BooksColectionAPI.deleteBookingWithId(tempBookingId);
        
        Assert.assertEquals(secondDeleteResponse.statusCode(), 405);
        System.out.println("✓ Delete already deleted booking properly handled");
    }

}
