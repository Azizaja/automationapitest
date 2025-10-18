package scenario.authTest;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class AuthPositiveTest {

    @Test
    public void LoginAuth() {
        System.out.println("Get Auth Token");

        String requestBody = """
                {
                   "username" : "admin",
                   "password" : "password123"
                }""";

        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/auth")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();
        String token = response.jsonPath().getString("token");
        System.out.println("Token: " + token);
        System.out.println("Get Auth Token Selesai");
    }

}
