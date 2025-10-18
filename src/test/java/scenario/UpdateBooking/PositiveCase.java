package scenario.UpdateBooking;

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

    // ada test createbooking -> updatebooking karena butuh bookingId
    // untuk setiap test case harus berdiri sendiri, maka dibuat test createbooking di sini
    // agar mudah dimanage dan menjalankan test case ini secara mandiri
    // jika menunggu create booking selesai bisa terlalu lama apabila terdapat lebih dari 1 test case
    // dan apabila create booking gagal maka test update booking juga akan gagal

    int bookingId;

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
    public void UpdateBooking() {
        System.out.println("Update Booking");

        // Debug info - pastikan bookingId dan token sudah ada
        // System.out.println("Booking ID from BaseTest: " + BaseTest.getBookingId());
        // System.out.println("Token from BaseTest: " + BaseTest.token);

        RequestCreateBooking bookingData = Helper.findByUseCase(
                "booking_data.json",
                "add_booking_data4",
                RequestCreateBooking.class);
        String requestBody = Helper.convertToJson(bookingData);

        Response response = BooksColectionAPI.updateBookingAPI(requestBody, bookingId);

        // Debug response
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertEquals(response.jsonPath().getString("firstname"), "James", "Firstname is not James");
        System.out.println("Update Booking Selesai");
    }

    @Test
    public void UpdateBooking_FullData() {
        System.out.println("Update Booking with Full Data");

        RequestCreateBooking bookingData = Helper.findByUseCase(
                "booking_data.json",
                "add_booking_data1",
                RequestCreateBooking.class);
        String requestBody = Helper.convertToJson(bookingData);

        Response createResponse = BooksColectionAPI.createBookingAPI(requestBody);
        int tempBookingId = createResponse.jsonPath().getInt("bookingid");

        Response response = BooksColectionAPI.updateBookingAPI(requestBody, tempBookingId);

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertEquals(response.jsonPath().getString("firstname"), "John", "Firstname is not John");
        Assert.assertEquals(response.jsonPath().getString("lastname"), "Doe", "Lastname is not Doe");
        System.out.println("Update Booking with Full Data Selesai");
    }
}