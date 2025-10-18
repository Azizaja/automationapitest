package scenario.createBooking;

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

    @Test
    public void CreateBookings() throws JsonMappingException, JsonProcessingException {
        System.out.println("Create Booking");
        System.out.println("Base URI: " + BaseTest.BaseURI);

        // String requestBody = """
        // {
        // "firstname": "Jim",
        // "lastname": "Brown",
        // "totalprice": 111,
        // "depositpaid": true,
        // "bookingdates": {
        // "checkin": "2018-01-01",
        // "checkout": "2019-01-01"
        // },
        // "additionalneeds": "Breakfast"
        // }
        // """;

        // Response response = BooksColectionAPI.createBookingAPI(requestBody);

        RequestCreateBooking bookingData = Helper.findByUseCase(
                "add_booking_data.json",
                "add_booking_data1",
                RequestCreateBooking.class);

        // Convert ke JSON string untuk request API
        String requestBody = Helper.convertToJson(bookingData);
        System.out.println("Request Body: " + requestBody);

        Response response = BooksColectionAPI.createBookingAPI(requestBody);

        // sebelum deserialisasi
        // ObjectMapper objectMapper = new ObjectMapper();
        // ResponseCreateBoking bookingResponse =
        // objectMapper.readValue(response.asString(), ResponseCreateBoking.class);
        // response.then().log().all();
        // Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");

        // setelah deserialisasi menggunakan Helper
        ResponseCreateBoking bookingResponse = Helper.convertResponseToObject(response, ResponseCreateBoking.class);

        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");

        Assert.assertNotNull(bookingResponse.bookingId, "Booking ID is null");
        Assert.assertEquals(bookingResponse.booking.firstName, "John", "Firstname is not John");
        Assert.assertEquals(bookingResponse.booking.lastName, "Doe", "Lastname is not Doe");

        // contoh assert sebelumnya menggunakan jsonpath sebelum deserialisasi
        // Assert.assertNotNull(response.jsonPath().getInt("bookingid"), "Booking ID is
        // null");
        // Assert.assertEquals(response.jsonPath().getString("booking.firstname"),
        // "Jim", "Firstname is not Jim");

        System.out.println("Create Booking Selesai");
    }

    // another create booking
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
        ResponseCreateBoking bookingResponse = Helper.convertResponseToObject(response, ResponseCreateBoking.class);
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertNotNull(bookingResponse.bookingId, "Booking ID is null");
        Assert.assertEquals(bookingResponse.booking.firstName, "Michael", "Firstname is not Michael");
        Assert.assertEquals(bookingResponse.booking.lastName, "Smith", "Lastname is not Smith");

        System.out.println("Create Booking 2 Selesai");
    }

    @Test
    public void CreateBookings3() throws JsonMappingException, JsonProcessingException {
        System.out.println("Create Booking 3");
        System.out.println("Base URI: " + BaseTest.BaseURI);

        String requestBody = """
                {
                    "firstname": "Alice",
                    "lastname": "Johnson",
                    "totalprice": 200,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2023-11-01",
                        "checkout": "2023-11-15"
                    },
                    "additionalneeds": "Dinner"
                }
                """;

        Response response = BooksColectionAPI.createBookingAPI(requestBody);
        ResponseCreateBoking bookingResponse = Helper.convertResponseToObject(response, ResponseCreateBoking.class);
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertNotNull(bookingResponse.bookingId, "Booking ID is null");
        Assert.assertEquals(bookingResponse.booking.firstName, "Alice", "Firstname is not Alice");
        Assert.assertEquals(bookingResponse.booking.lastName, "Johnson", "Lastname is not Johnson");

        System.out.println("Create Booking 3 Selesai");
    }

}