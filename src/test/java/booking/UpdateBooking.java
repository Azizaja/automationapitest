package booking;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class UpdateBooking extends BaseTest {

    @Test
    public void UpdateBookings() {
        System.out.println("Update Booking");

        // Pastikan bookingId dan token sudah tersedia
        if (bookingId == 0) {
            throw new RuntimeException("Booking ID belum di-set. Pastikan CreateBooking dijalankan terlebih dahulu.");
        }
        
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token belum di-set. Pastikan Auth dijalankan terlebih dahulu.");
        }
        
        String requestBody = "{\r\n" + //
                        "    \"firstname\" : \"James\",\r\n" + //
                        "    \"lastname\" : \"Brown\",\r\n" + //
                        "    \"totalprice\" : 111,\r\n" + //
                        "    \"depositpaid\" : true,\r\n" + //
                        "    \"bookingdates\" : {\r\n" + //
                        "        \"checkin\" : \"2018-01-01\",\r\n" + //
                        "        \"checkout\" : \"2019-01-01\"\r\n" + //
                        "    },\r\n" + //
                        "    \"additionalneeds\" : \"Breakfast\"\r\n" + //
                        "}";
        // Update booking with id 1 as example
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/" + bookingId)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .body(requestBody)
                .when()
                .put();
        // response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertEquals(response.jsonPath().getString("firstname"), "James", "Firstname is not James");
        System.out.println("Update Booking Selesai");
    }

}
