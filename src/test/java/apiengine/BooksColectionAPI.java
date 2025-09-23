package apiengine;

import static io.restassured.RestAssured.given;

import com.apitest.base.BaseTest;

import io.restassured.response.Response;

public class BooksColectionAPI extends BaseTest {
    // public BooksColectionAPI() {
    // }

    // create booking
    public static Response createBookingAPI(String requestBody) {
        Response response = given()
                .baseUri(BaseURI)
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();
        return response;
    }

    // get booking
    public static Response getBooksFromCollectionsAPI() {
        int idBooking = 1;
        Response response = given()
                .baseUri(BaseURI)
                .basePath("/booking/" + idBooking)
                .header("Content-Type", "application/json")
                .when()
                .get();
        return response;
    }


    // update booking
    public static Response updateBookingAPI(String requestBody) {
        int idBooking = BaseTest.getBookingId();
        String token = BaseTest.token;
        // String token = TokenManager.getToken();

        if (idBooking == 0) {
            throw new IllegalStateException("Booking ID is not set. Please create a booking first.");
        }
        if (token == null || token.isEmpty()) {
            throw new IllegalStateException("Token is not set. Please authenticate first.");
        }

        System.out.println("=== UPDATE API DEBUG ===");
        System.out.println("Booking ID: " + idBooking);
        System.out.println("Token: " + token);

        Response response = given()
                .baseUri(BaseURI)
                .basePath("/booking/" + idBooking)
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token)
                .body(requestBody)
                .when()
                .put();

        return response;
    }

    // delete booking
    public static Response deleteBookingAPI(String requestBody) {
        int idBooking = BaseTest.getBookingId();
        String token = BaseTest.token;
        // String token = TokenManager.getToken();

        if (idBooking == 0) {
            throw new IllegalStateException("Booking ID is not set. Please create a booking first.");
        }
        if (token == null || token.isEmpty()) {
            throw new IllegalStateException("Token is not set. Please authenticate first.");
        }

        System.out.println("=== DELETE API DEBUG ===");
        System.out.println("Booking ID: " + idBooking);
        System.out.println("Token: " + token);

        Response response = given()
                .baseUri(BaseURI)
                .basePath("/booking/" + idBooking)
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token)
                .when()
                .delete();

        return response;
    }

}
