package apiengine;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class BooksColectionAPI {
    // public BooksColectionAPI() {
    // }

    //create booking
    public static Response createBookingAPI(String requestBody) {
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();
        return response;
    }

    //get booking
    public static Response getBooksFromCollectionsAPI() {
        int idBooking = 1; 
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/" + idBooking)
                .header("Content-Type", "application/json")
                .when()
                .get();
        return response;
    }

}
