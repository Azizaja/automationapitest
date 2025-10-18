package scenario.deleteBooking;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apitest.base.BaseTest;
import com.apitest.model.request.RequestCreateBooking;
import com.apitest.utils.Helper;

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
        RequestCreateBooking bookingData = Helper.findByUseCase(
                "booking_data.json",
                "add_booking_data3",
                RequestCreateBooking.class);
        String requestBody = Helper.convertToJson(bookingData);

        Response response = BooksColectionAPI.createBookingAPI(requestBody);
        int tempBookingId = response.jsonPath().getInt("bookingid");
        
        // Delete it first
        BooksColectionAPI.deleteBookingWithId(tempBookingId);
        
        // Try to delete again
        Response secondDeleteResponse = BooksColectionAPI.deleteBookingWithId(tempBookingId);
        
        Assert.assertEquals(secondDeleteResponse.statusCode(), 405);
        System.out.println("✓ Delete already deleted booking properly handled");
    }

}
