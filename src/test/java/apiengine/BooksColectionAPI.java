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
                .basePath("/booking")
                .body(requestBody)
                .when()
                .post();
        return response;
    }

    //create booking 
    // public static <T> Response createBookingAPI(T requestBody) {
    //     Response response = given()
    //             .basePath("/booking")
    //             .body(requestBody)
    //             .when()
    //             .post();
    //     return response;
    // }

    // get booking
    public static Response getBooksFromCollectionsAPI(int idBooking) {
        // int idBooking = 1;
        // int idBooking = BaseTest.getBookingId();
        Response response = given()
                .basePath("/booking/" + idBooking)
                .when()
                .get();
        return response;
    }


    // update booking
    public static Response updateBookingAPI(String requestBody, int bookingId) {
        // int idBooking = BaseTest.getBookingId();
        String token = BaseTest.token;        

        System.out.println("=== UPDATE API DEBUG ===");
        // System.out.println("Booking ID: " + idBooking);
        System.out.println("Token: " + token);

        Response response = given()
                .basePath("/booking/" + bookingId)
                .header("Cookie", "token=" + token)
                .body(requestBody)
                .when()
                .put();

        return response;
    }

    // delete booking
    public static Response deleteBookingAPI(String requestBody, int idBooking) {
        // int idBooking = BaseTest.getBookingId();
        String token = BaseTest.token;

        System.out.println("=== DELETE API DEBUG ===");
        // System.out.println("Booking ID: " + idBooking);
        System.out.println("Token: " + token);

        Response response = given()
                .basePath("/booking/" + idBooking)
                .header("Cookie", "token=" + token)
                .when()
                .delete();

        return response;
    }

    // delete booking without token
    public static Response deleteBookingWithoutToken(int idBooking) {
        Response response = given()
                .basePath("/booking/" + idBooking)
                .when()
                .delete();

        return response;
    }

    // delete booking with id
    public static Response deleteBookingWithId(int idBooking) {
        String token = BaseTest.token;

        Response response = given()
                .basePath("/booking/" + idBooking)
                .header("Cookie", "token=" + token)
                .when()
                .delete();

        return response;
    }

}
