package negative;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GetBooking {
    @Test
    public void GetBooking_NonExistentID() {
        System.out.println("Negative Test: Non-Existent Booking ID");
        int nonExistentId = 999999;

        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/" + nonExistentId)
                .when()
                .get();

        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 404, "Should return 404 for non-existent booking");
        System.out.println("Non-existent booking ID test passed");
    }

    @Test
    public void GetBooking_InvalidIDFormat() {
        System.out.println("Negative Test: Invalid ID Format");

        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/invalid_id")
                .when()
                .get();

        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 404, "Should return 404 for invalid ID format");
        System.out.println("Invalid ID format test passed");
    }

}
