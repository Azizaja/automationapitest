package apiengine;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class BooksColectionAPI {
    // public BooksColectionAPI() {
    // }

    public static Response createBookingAPI(String requestBody) {
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();
        return response;
    }

    public static Response getBooksFromCollectionsAPI() {
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/1")
                .header("Content-Type", "application/json")
                .when()
                .get();
        return response;
    }

}
