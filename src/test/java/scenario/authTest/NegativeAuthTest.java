package scenario.authTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class NegativeAuthTest {
    @Test
    public void testInvalidCredentials() {
        System.out.println("Test Invalid Credentials");
        String requestBody = """
                {
                    "username" : "invalid_user",
                    "password" : "wrong_password"
                }""";

        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/auth");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
        System.out.println("Response for invalid credentials: " + response.asString());
        System.out.println("Test Invalid Credentials Completed");
    }

    @Test
    public void testMissingPassword() {
        System.out.println("Test Missing Password");
        String requestBody = """
                {
                    "username" : "admin"
                }""";

        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/auth");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
        System.out.println("Response for missing password: " + response.asString());
        System.out.println("Test Missing Password Completed");
    }

    @Test
    public void testEmptyUsername() {
        System.out.println("Test Empty Username");
        String requestBody = """
                {
                    "username" : "",
                    "password" : "password123"
                }""";

        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/auth");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
        System.out.println("Response for empty username: " + response.asString());
        System.out.println("Test Empty Username Completed");
    }

    @Test
    public void testNoCredentials() {
        System.out.println("Test No Credentials");

        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Content-Type", "application/json")
                .when()
                .post("/auth");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
        System.out.println("Response for no credentials: " + response.asString());
        System.out.println("Test No Credentials Completed");
    }

    @Test
    public void testSQLInjection() {
        System.out.println("Test SQL Injection");
        String requestBody = """
                {
                    "username" : "admin' OR '1'='1",
                    "password" : "password123"
                }""";

        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/auth");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
        System.out.println("Response for SQL injection attempt: " + response.asString());
        System.out.println("Test SQL Injection Completed");
    }
}
