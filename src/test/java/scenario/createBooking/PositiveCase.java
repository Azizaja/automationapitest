package scenario.createBooking;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apitest.base.BaseTest;

import apiengine.BooksColectionAPI;
import io.restassured.response.Response;

public class PositiveCase {

    @Test(groups = {"bookingFlow"})
    public void CreateBookings() {
        System.out.println("Create Booking");
        
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

        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertNotNull(response.jsonPath().getString("bookingid"), "Booking ID is null");
        
        // SIMPAN bookingId ke BaseTest untuk test selanjutnya
        int bookingId = response.jsonPath().getInt("bookingid");
        BaseTest.setBookingId(bookingId);
        System.out.println("Booking ID Created and Saved: " + bookingId);
        System.out.println("Create Booking Selesai");
    }
}