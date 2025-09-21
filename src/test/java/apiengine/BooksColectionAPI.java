package apiengine;

import io.restassured.response.Response;

public class BooksColectionAPI {

    public static Response addBooksToCollectionsAPI(String requestBody) {
        Response response = io.restassured.RestAssured.given()
                .baseUri("https://bookstore.toolsqa.com/BookStore/v1/Books")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();
        return response;
    }

}
