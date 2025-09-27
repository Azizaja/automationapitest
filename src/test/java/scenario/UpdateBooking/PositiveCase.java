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

    @Test(dependsOnMethods = { "CreateBookings" })
    public void UpdateBooking() {
        System.out.println("Update Booking");

        // Debug info - pastikan bookingId dan token sudah ada
        // System.out.println("Booking ID from BaseTest: " + BaseTest.getBookingId());
        System.out.println("Token from BaseTest: " + BaseTest.token);

        String requestBody = """
                {
                    "firstname": "James",
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

        Response response = BooksColectionAPI.updateBookingAPI(requestBody, bookingId);

        // Debug response
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertEquals(response.jsonPath().getString("firstname"), "James", "Firstname is not James");
        System.out.println("Update Booking Selesai");
    }
}