package scenario.getBooking;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class PositiveCase {
      @Test
    public void GetBookings() {
        System.out.println("Get Booking");
        given()
                // .baseUri("https://restful-booker.herokuapp.com/booking/" + bookingId)
                .baseUri("https://restful-booker.herokuapp.com/booking/1")
                .when()
                .get()
                .then()
                .log().all();
        System.out.println("Get Booking Selesai");
    }


}
