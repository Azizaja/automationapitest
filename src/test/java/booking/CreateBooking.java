package booking;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CreateBooking {
    int bookingId;

    @Test
    public void CreateBookings(){
        // https://restful-booker.herokuapp.com/booking
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
        Response response = given()
                        .baseUri("https://restful-booker.herokuapp.com/booking")
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .when()
                        .post();
        // response.then().log().all(); 
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertNotNull(response.jsonPath().getString("bookingid"), "Booking ID is null");
        Assert.assertEquals(response.jsonPath().getString("booking.firstname"), "Jim", "Firstname is not Jim");

          // SIMPAN bookingId untuk test selanjutnya
        bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("Booking ID yang dibuat: " + bookingId);
        System.out.println("Create Booking Selesai");
    }

}
