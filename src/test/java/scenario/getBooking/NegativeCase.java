package scenario.getBooking;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apitest.base.BaseTest;

import io.restassured.response.Response;

public class NegativeCase extends BaseTest {
    @Test
    public void GetBooking_NonExistentID() {
        System.out.println("Negative Test: Non-Existent Booking ID");
        int nonExistentId = 999999;

        Response response = given()
                .baseUri(BaseURI)
                .basePath("/booking/" + nonExistentId)
                .when()
                .get();

        // response.then().log().all();
        Assert.assertEquals(response.statusCode(), 404, "Should return 404 for non-existent booking");
        System.out.println("Non-existent booking ID test passed");
    }

    @Test
    public void GetBooking_InvalidIDFormat() {
        System.out.println("Negative Test: Invalid ID Format");

        Response response = given()
                .baseUri(BaseURI)
                .basePath("/booking/invalid_id")
                .when()
                .get();

        // response.then().log().all();
        Assert.assertEquals(response.statusCode(), 404, "Should return 404 for invalid ID format");
        System.out.println("Invalid ID format test passed");
    }

     @Test
    public void GetBooking_ZeroID() {
        System.out.println("Negative Test: Zero Booking ID");
        
        Response response = given()
                .baseUri(BaseURI)
                .when()
                .get("/booking/0");  // ID = 0

        Assert.assertEquals(response.statusCode(), 404);
        System.out.println("✓ Zero booking ID test passed");
    }
    
    @Test
    public void GetBooking_NegativeID() {
        System.out.println("Negative Test: Negative Booking ID");
        
        Response response = given()
                .baseUri(BaseURI)
                .when()
                .get("/booking/-123");  // ID negatif

        Assert.assertEquals(response.statusCode(), 404);
        System.out.println("✓ Negative booking ID test passed");
    }
    
    @Test
    public void GetBooking_SpecialCharactersID() {
        System.out.println("Negative Test: Special Characters in ID");
        
        Response response = given()
                .baseUri(BaseURI)
                .when()
                .get("/booking/123!@#");  // Character khusus

        Assert.assertEquals(response.statusCode(), 404);
        System.out.println("✓ Special characters ID test passed");
    }

}
