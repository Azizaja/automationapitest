package scenario.deleteBooking;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apitest.base.BaseTest;

import apiengine.BooksColectionAPI;
import io.restassured.response.Response;

public class PositiveCase extends BaseTest {
    // @Test(dependsOnGroups = {"bookingFlow"})
    @Test
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
        Response response = BooksColectionAPI.deleteBookingAPI(requestBody);
        Assert.assertEquals(response.statusCode(), 201, "Status code is not 201");
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Booking ID to be deleted: " + bookingId);

    }
}
