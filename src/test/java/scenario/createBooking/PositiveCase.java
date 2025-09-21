package scenario.createBooking;

import org.testng.Assert;
import org.testng.annotations.Test;

import apiengine.BooksColectionAPI;
import io.restassured.response.Response;

public class PositiveCase {
     public BooksColectionAPI createBookingAPI;

    @Test
    public void CreateBookings() {
        System.out.println("Create Booking");
        String requestBody = "{\r\n" + //
                        "    \"firstname\" : \"Jim\",\r\n" + //
                        "    \"lastname\" : \"Brown\",\r\n" + //
                        "    \"totalprice\" : 111,\r\n" + //
                        "    \"depositpaid\" : true,\r\n" + //
                        "    \"bookingdates\" : {\r\n" + //
                        "        \"checkin\" : \"2018-01-01\",\r\n" + //
                        "        \"checkout\" : \"2019-01-01\"\r\n" + //
                        "    },\r\n" + //
                        "    \"additionalneeds\" : \"Breakfast\"\r\n" + //
                        "}";
        Response response = BooksColectionAPI.createBookingAPI(requestBody);

        response.then().log().all();
        // bookingId = response.jsonPath().getInt("bookingid");
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertNotNull(response.jsonPath().getString("bookingid"), "Booking ID is null");
        Assert.assertEquals(response.jsonPath().getString("booking.firstname"), "Jim", "Firstname is not Jim");
        System.out.println("Create Booking Selesai");
    }

}
