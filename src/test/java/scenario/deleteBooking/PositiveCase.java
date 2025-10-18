package scenario.deleteBooking;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apitest.base.BaseTest;
import com.apitest.model.request.RequestCreateBooking;
import com.apitest.model.response.ResponseCreateBoking;
import com.apitest.utils.Helper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import apiengine.BooksColectionAPI;
import io.restassured.response.Response;

public class PositiveCase extends BaseTest {
    // @Test
    // createbooking -> deletebooking
    @Test
    public void CreateBookings() throws JsonMappingException, JsonProcessingException {
        System.out.println("Create Booking");
        System.out.println("Base URI: " + BaseTest.BaseURI);

        RequestCreateBooking bookingData = Helper.findByUseCase(
                "booking_data.json",
                "add_booking_data2",
                RequestCreateBooking.class);
        String requestBody = Helper.convertToJson(bookingData);

        Response response = BooksColectionAPI.createBookingAPI(requestBody);
        ResponseCreateBoking bookingResponse = Helper.convertResponseToObject(response, ResponseCreateBoking.class);

        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertNotNull(bookingResponse.bookingId, "Booking ID is null");
        Assert.assertEquals(bookingResponse.booking.firstName, "Jane", "Firstname is not Jane");
        Assert.assertEquals(bookingResponse.booking.lastName, "Smith", "Lastname is not Smith");

        // SIMPAN bookingId untuk test selanjutnya
        bookingId = bookingResponse.bookingId;
        System.out.println("Booking ID Created and Saved: " + bookingId);
        System.out.println("Create Booking Selesai");
    }

    @Test(dependsOnMethods = { "CreateBookings" })
    public void DeleteBooking() {
        System.out.println("Delete Booking");

        Response response = BooksColectionAPI.deleteBookingAPI(bookingId);
        Assert.assertEquals(response.statusCode(), 201, "Status code is not 201");

        String responseBody = response.getBody().asString();
        System.out.println("Delete Response Body: '" + responseBody + "'");

        Assert.assertTrue(responseBody.isEmpty() || responseBody.equals("Created"),
                "Unexpected response body for delete");

        System.out.println("Delete Booking Success - Status: " + response.statusCode());
    }

    @Test
    public void DeleteBooking_WithAdditionalNeeds() {
        System.out.println("Positive Test: Delete Booking with Additional Needs");

        // First create special booking
        String createBody = """
                {
                "firstname": "Special",
                "lastname": "Delete",
                "totalprice": 200,
                "depositpaid": true,
                "bookingdates": {
                "checkin": "2024-01-01",
                "checkout": "2024-01-05"
                },
                "additionalneeds": "Special request"
                }
                """;

        Response createResponse = BooksColectionAPI.createBookingAPI(createBody);
        int specialBookingId = createResponse.jsonPath().getInt("bookingid");

        Response deleteResponse = BooksColectionAPI.deleteBookingAPI(specialBookingId);

        Assert.assertEquals(deleteResponse.statusCode(), 201);
        System.out.println("Special booking with additional needs deleted");
    }
}
