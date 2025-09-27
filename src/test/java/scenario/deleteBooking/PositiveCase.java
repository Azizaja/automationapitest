package scenario.deleteBooking;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apitest.base.BaseTest;
import com.apitest.model.response.ResponseCreateBoking;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import apiengine.BooksColectionAPI;
import io.restassured.response.Response;

public class PositiveCase extends BaseTest {
    // @Test
    //createbooking -> deletebooking
    @Test
    public void CreateBookings() throws JsonMappingException, JsonProcessingException {
        System.out.println("Create Booking");
        System.out.println("Base URI: " + BaseTest.BaseURI);
        
        String requestBody = """
            {
                "firstname": "Jim",
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
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseCreateBoking bookingResponse = objectMapper.readValue(response.asString(), ResponseCreateBoking.class);
        response.then().log().all();
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertNotNull(bookingResponse.bookingId, "Booking ID is null");
        Assert.assertEquals(bookingResponse.booking.firstName, "Jim", "Firstname is not Jim");
        Assert.assertEquals(bookingResponse.booking.lastName, "Brown", "Lastname is not Brown");
        
        // SIMPAN bookingId ke BaseTest untuk test selanjutnya
        bookingId = bookingResponse.bookingId;
        System.out.println("Booking ID Created and Saved: " + bookingId);
        System.out.println("Create Booking Selesai");
    }

    @Test(dependsOnMethods = {"CreateBookings"})
    public void DeleteBooking() {
        System.out.println("Delete Booking");
        String requestBody = """
            {
                "firstname": "Jim",
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
        Response response = BooksColectionAPI.deleteBookingAPI(requestBody, bookingId);
        Assert.assertEquals(response.statusCode(), 201, "Status code is not 201");
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Booking ID to be deleted: " + bookingId);
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
        
        Response deleteResponse = BooksColectionAPI.deleteBookingAPI(createBody, specialBookingId);
        
        Assert.assertEquals(deleteResponse.statusCode(), 201);
        System.out.println("Special booking with additional needs deleted");
    }
}
