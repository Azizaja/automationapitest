import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class RestAdv {

    /*
     * User Account
     * '{
     * "username" : "admin",
     * "password" : "password123"
     * }'
     */
    String token;
    int bookingId;

    @BeforeClass
    public void GenerateToken() {
        System.out.println("Generate Token Auth");
        String requestBody = "{\r\n" + //
                "    \"username\" : \"admin\",\r\n" + //
                "    \"password\" : \"password123\"\r\n" + //
                "}";
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/auth")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();
        token = response.jsonPath().getString("token");
        System.out.println("berhasil generate token: " + token);
    }

    @Test(priority = 0)
    public void GetBooking() {
        // https://restful-booker.herokuapp.com/booking
        System.out.println("Get Booking");
        given()
                .baseUri("https://restful-booker.herokuapp.com/booking/1")
                .when()
                .get()
                .then()
                .log().all();
        System.out.println("Get Booking Selesai");
    }

    // @Test(dependsOnMethods = {"GenerateToken"})
    @Test(priority = 1)
    public void CreateBooking(){
        // https://restful-booker.herokuapp.com/booking
        System.out.println("Create Booking");
        String requestBody = "{\r\n" + //
                        "    \"firstname\" : \"Jim\",\r\n" + //
                        "    \"lastname\" : \"Brown\",\r\n" + //
                        "    \"totalprice\" : 111,\r\n" + //
                        "    \"depositpaid\" : true,\r\n" + //
                        "    \"bookingdates\" : {\r\n" + //
                        "        \"checkin\" : \"2018-01-01\",\r\n" + //
                        "        \"checkout\" : \"2019-01-01\"\r\n" + //
                        "    },\r\n" + //
                        "    \"additionalneeds\" : \"Breakfast\"\r\n" + //
                        "}";
        Response response = given()
                        .baseUri("https://restful-booker.herokuapp.com/booking")
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .when()
                        .post();
        // response.then().log().all(); 
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertNotNull(response.jsonPath().getString("bookingid"), "Booking ID is null");
        Assert.assertEquals(response.jsonPath().getString("booking.firstname"), "Jim", "Firstname is not Jim");

          // SIMPAN bookingId untuk test selanjutnya
        bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("Booking ID yang dibuat: " + bookingId);
        System.out.println("Create Booking Selesai");
    }

    @Test(priority = 2)
    public void UpdateBooking() {
        System.out.println("Update Booking");
        String requestBody = "{\r\n" + //
                        "    \"firstname\" : \"James\",\r\n" + //
                        "    \"lastname\" : \"Brown\",\r\n" + //
                        "    \"totalprice\" : 111,\r\n" + //
                        "    \"depositpaid\" : true,\r\n" + //
                        "    \"bookingdates\" : {\r\n" + //
                        "        \"checkin\" : \"2018-01-01\",\r\n" + //
                        "        \"checkout\" : \"2019-01-01\"\r\n" + //
                        "    },\r\n" + //
                        "    \"additionalneeds\" : \"Breakfast\"\r\n" + //
                        "}";
        // Update booking with id 1 as example
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/" + bookingId)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .body(requestBody)
                .when()
                .put();
        // response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertEquals(response.jsonPath().getString("firstname"), "James", "Firstname is not James");
        System.out.println("Update Booking Selesai");
    }

    @Test(priority = 3)
    public void DeleteBooking() {
        System.out.println("Delete Booking");
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/" + bookingId)
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token)
                .when()
                .delete();
        // response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201, "Status code is not 201");
        System.out.println("Delete Booking Selesai");
    }

    //Test GetBooking after DeleteBooking to verify if the booking is deleted
    @Test(priority = 4)
    public void GetBookingAfterDelete() {
        System.out.println("Get Booking After Delete");
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/" + bookingId)
                .when()
                .get();
        // response.then().log().all();
        Assert.assertEquals(response.statusCode(), 404, "Status code is not 404");
        System.out.println("Get Booking After Delete Selesai");
    }

}
