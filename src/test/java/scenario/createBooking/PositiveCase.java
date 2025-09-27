package scenario.createBooking;

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

    // @Test(groups = {"bookingFlow"})
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

        // contoh assert sebelumnya menggunakan jsonpath
        // Assert.assertNotNull(response.jsonPath().getInt("bookingid"), "Booking ID is null");
        // Assert.assertEquals(response.jsonPath().getString("booking.firstname"), "Jim", "Firstname is not Jim");
        Assert.assertNotNull(bookingResponse.bookingId, "Booking ID is null");
        Assert.assertEquals(bookingResponse.booking.firstName, "Jim", "Firstname is not Jim");
        Assert.assertEquals(bookingResponse.booking.lastName, "Brown", "Lastname is not Brown");
        
        // SIMPAN bookingId ke BaseTest untuk test selanjutnya
        int bookingId = bookingResponse.bookingId;
        // BaseTest.setBookingId(bookingId);
        System.out.println("Booking ID Created and Saved: " + bookingId);
        System.out.println("Create Booking Selesai");
    }

    //another create booking
    @Test
    public void CreateBookings2() throws JsonMappingException, JsonProcessingException {
        System.out.println("Create Booking 2");
        System.out.println("Base URI: " + BaseTest.BaseURI);
        
        String requestBody = """
            {
                "firstname": "Michael",
                "lastname": "Smith",
                "totalprice": 150,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2023-10-01",
                    "checkout": "2023-10-10"
                },
                "additionalneeds": "Lunch"
            }
            """;
            
        Response response = BooksColectionAPI.createBookingAPI(requestBody);
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseCreateBoking bookingResponse = objectMapper.readValue(response.asString(), ResponseCreateBoking.class);
        response.then().log().all();
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");

        // contoh assert sebelumnya menggunakan jsonpath
        // Assert.assertNotNull(response.jsonPath().getInt("bookingid"), "Booking ID is null");
        // Assert.assertEquals(response.jsonPath().getString("booking.firstname"), "Jim", "Firstname is not Jim");
        Assert.assertNotNull(bookingResponse.bookingId, "Booking ID is null");
        Assert.assertEquals(bookingResponse.booking.firstName, "Michael", "Firstname is not Michael");
        Assert.assertEquals(bookingResponse.booking.lastName, "Smith", "Lastname is not Smith");
        
        // SIMPAN bookingId ke BaseTest untuk test selanjutnya
        int bookingId = bookingResponse.bookingId;
        // BaseTest.setBookingId(bookingId);
        System.out.println("Booking ID Created and Saved: " + bookingId);
        System.out.println("Create Booking 2 Selesai");
    }

    @Test(dependsOnMethods = { "CreateBookings" })
    public void GetBookings() {
        System.out.println("Get Booking" + bookingId);
        BooksColectionAPI.getBooksFromCollectionsAPI(bookingId)
            .then()
            .log().all();
        System.out.println("Get Booking Selesai");
    }
    
}