package scenario.deleteBooking;

import org.testng.annotations.Test;

import apiengine.BooksColectionAPI;
import io.restassured.response.Response;

public class PositiveCase {
    @Test(dependsOnGroups = {"bookingFlow"})
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
        Response response = BooksColectionAPI.createBookingAPI(requestBody);
        int bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("Booking ID to be deleted: " + bookingId);

    }
}
