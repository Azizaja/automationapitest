package auth;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
// import org.testng.annotations.Test;
import io.restassured.response.Response;

public class AuthenticationTest {
    String token;
    int bookingid;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        GenerateToken();
    }
    
    public void GenerateToken() {
        // prepare data for generate token
        // positive test case generate and valid token
        System.out.println("Generate Token Auth");
        String requestBody = """
                {
                   "username" : "admin",
                   "password" : "password123"
                }""";
        Response response = given()
                // .baseUri("https://restful-booker.herokuapp.com/auth")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();
        token = response.jsonPath().getString("token");
        System.out.println("berhasil generate token: " + token);
        System.out.println("Generate Token Auth Completed");
    }

    // @Test
    // // positive test case
    // public void testValidCredentials() {
    // System.out.println("Test Valid Credentials");
    // String requestBody = """
    // {
    // "username" : "admin",
    // "password" : "password123"
    // }""";

    // Response response = given()
    // .baseUri("https://restful-booker.herokuapp.com/auth")
    // .header("Content-Type", "application/json")
    // .body(requestBody)
    // .when()
    // .post();

    // System.out.println("Response for valid credentials: " + response.asString());
    // System.out.println("Test Valid Credentials Completed");
    // }

}
