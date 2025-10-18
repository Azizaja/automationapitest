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
        // ambil data dari file JSON menggunakan Helper
        RequestCreateBooking bookingData = Helper.findByUseCase(
                "booking_data.json",
                "add_booking_data1",
                RequestCreateBooking.class);

        // Convert ke JSON string untuk request API
        String requestBody = Helper.convertToJson(bookingData);
        System.out.println("Request Body: " + requestBody);

        Response response = BooksColectionAPI.createBookingAPI(requestBody);

        // deserialize response ke ResponseCreateBooking class
        ResponseCreateBoking bookingResponse = Helper.convertResponseToObject(response, ResponseCreateBoking.class);

        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertNotNull(bookingResponse.bookingId, "Booking ID is null");
        Assert.assertEquals(bookingResponse.booking.firstName, "John", "Firstname is not John");
        Assert.assertEquals(bookingResponse.booking.lastName, "Doe", "Lastname is not Doe");

        System.out.println("Create Booking Selesai");
    }

    // another create booking
    @Test
    public void CreateBookings2() throws JsonMappingException, JsonProcessingException {
        System.out.println("Create Booking 2");
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
        System.out.println("Create Booking 2 Selesai");
    }

    @Test
    public void CreateBookings3() throws JsonMappingException, JsonProcessingException {
        System.out.println("Create Booking 3");
        System.out.println("Base URI: " + BaseTest.BaseURI);

        RequestCreateBooking bookingData = Helper.findByUseCase(
                "booking_data.json",
                "add_booking_data3",
                RequestCreateBooking.class);
        String requestBody = Helper.convertToJson(bookingData);

        Response response = BooksColectionAPI.createBookingAPI(requestBody);
        ResponseCreateBoking bookingResponse = Helper.convertResponseToObject(response, ResponseCreateBoking.class);

        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertNotNull(bookingResponse.bookingId, "Booking ID is null");
        Assert.assertEquals(bookingResponse.booking.firstName, "Alice", "Firstname is not Alice");
        Assert.assertEquals(bookingResponse.booking.lastName, "Johnson", "Lastname is not Johnson");
        System.out.println("Create Booking 3 Selesai");
    }

}