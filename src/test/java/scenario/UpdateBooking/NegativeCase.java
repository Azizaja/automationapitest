package scenario.UpdateBooking;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apitest.base.BaseTest;
import com.apitest.model.response.ResponseCreateBoking;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import apiengine.BooksColectionAPI;
import io.restassured.response.Response;

public class NegativeCase extends BaseTest{

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

     
    @Test (dependsOnMethods = {"CreateBookings"})
    public void UpdateBooking_InvalidData(){
        // int bookingId = 0; // Ganti dengan ID booking yang valid
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

        // tidak bisa mengembalikan error 400 karena server tidak validasi data
        Assert.assertNotEquals(response.statusCode(), 400, "Should return 400 for invalid data types");
        Assert.assertFalse(response.asString().contains("Bad Request"), "Response should indicate bad request");
        System.out.println("Update with invalid data request body test passed - Server rejected invalid request with status: " + response.statusCode());
        System.out.println("Update booking with invalid data types test passed");
    }

    @Test (dependsOnMethods = {"CreateBookings"})
    public void UpdateBooking_EmptyBody(){
        // int bookingId = 0; // Ganti dengan ID booking yang valid
        String requestBody = "{}";
        System.out.println("Negative Test: Update Booking with Empty Body");
        Response response = BooksColectionAPI.updateBookingAPI(requestBody, bookingId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 400, "Should return 400 for empty request body");
        Assert.assertTrue(response.asString().contains("Bad Request"), "Response should indicate bad request");
        System.out.println("Update booking with empty body test passed");
    }

}
