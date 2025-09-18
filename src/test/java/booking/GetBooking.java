package booking;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class GetBooking extends CreateBooking{

    @Test
    public void GetBookings() {
        // https://restful-booker.herokuapp.com/booking
        System.out.println("Get Booking");
        given()
                .baseUri("https://restful-booker.herokuapp.com/booking/" + bookingId)
                .when()
                .get()
                .then()
                .log().all();
        System.out.println("Get Booking Selesai");
    }

}
