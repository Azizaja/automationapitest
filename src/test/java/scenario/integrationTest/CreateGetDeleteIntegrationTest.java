package scenario.integrationTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apitest.base.BaseTest;
import com.apitest.model.request.RequestCreateBooking;
import com.apitest.model.response.ResponseCreateBoking;
import com.apitest.model.response.ResponseGetBooking;
import com.apitest.utils.Helper;

import apiengine.BooksColectionAPI;
import io.restassured.response.Response;

public class CreateGetDeleteIntegrationTest extends BaseTest {

    @Test(priority = 1)
    public void testCreateBooking() {
        System.out.println("=== Scenario 1: Create Booking ===");

        RequestCreateBooking bookingData = Helper.findByUseCase(
                "booking_data.json",
                "add_booking_data1",
                RequestCreateBooking.class);
        String requestBody = Helper.convertToJson(bookingData);

        Response response = BooksColectionAPI.createBookingAPI(requestBody);
        ResponseCreateBoking bookingResponse = Helper.convertResponseToObject(response, ResponseCreateBoking.class);

        Assert.assertEquals(response.statusCode(), 200, "Create booking failed");
        Assert.assertNotNull(bookingResponse.bookingId, "Booking ID should not be null");

        bookingId = bookingResponse.bookingId;
        System.out.println("Booking created with ID: " + bookingId);
    }

    @Test(priority = 2, dependsOnMethods = "testCreateBooking")
    public void testGetBooking() {
        System.out.println("=== Scenario 1: Get Booking ===");

        // Get created booking
        Response response = BooksColectionAPI.getBooksFromCollectionsAPI(bookingId);
        ResponseGetBooking bookingResponse = Helper.convertResponseToObject(response, ResponseGetBooking.class);

        Assert.assertEquals(response.statusCode(), 200, "Get booking failed");
        Assert.assertEquals(bookingResponse.firstName, "John", "Firstname mismatch");
        Assert.assertEquals(bookingResponse.lastName, "Doe", "Lastname mismatch");

        System.out.println("Booking retrieved successfully");
    }

    @Test(priority = 3, dependsOnMethods = "testGetBooking")
    public void testDeleteBooking() {
        System.out.println("=== Scenario 1: Delete Booking ===");

        Response response = BooksColectionAPI.deleteBookingAPI(bookingId);

        Assert.assertEquals(response.statusCode(), 201, "Delete booking failed");
        System.out.println("Booking deleted successfully");
    }
}
