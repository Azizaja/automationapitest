package auth;

import static io.restassured.RestAssured.given;
// import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.apitest.base.BaseTest;

import io.restassured.RestAssured;
// import org.testng.annotations.Test;
import io.restassured.response.Response;

public class AuthenticationTest extends BaseTest {
    String token;
    int bookingid;

    @Test
    public void setup() {
        RestAssured.baseURI = BaseURI;
        GenerateToken();
    }

    public void GenerateToken() {
        System.out.println("Get Auth Token");

        String requestBody = """
                {
                   "username" : "admin",
                   "password" : "password123"
                }""";

        Response response = given()
                // .baseUri("https://restful-booker.herokuapp.com/auth")
                // .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();
        token = response.jsonPath().getString("token");
        // BaseTest.setToken(token);
        System.out.println("Token: " + token);
        System.out.println("Get Auth Token Selesai");
    }

    

}
