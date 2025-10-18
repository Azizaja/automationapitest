package scenario.integrationTest;

// import org.testng.Assert;
// import org.testng.annotations.Test;

// import com.apitest.base.BaseTest;
// import com.apitest.model.request.RequestCreateBooking;
// import com.apitest.model.response.ResponseCreateBoking;
// import com.apitest.utils.Helper;

// import apiengine.BooksColectionAPI;
// import io.restassured.response.Response;


// public class CreatePartialUpdateGetDeleteIntegrationTest extends BaseTest {

//     @Test(priority = 1)
//     public void testCreateBooking() {
//         System.out.println("=== Scenario 3: Create Booking ===");
        
//         RequestCreateBooking bookingData = Helper.findByUseCase(
//             "booking_data.json", "add_booking_data3", RequestCreateBooking.class);
//         String requestBody = Helper.convertToJson(bookingData);
        
//         Response response = BooksCollectionAPI.createBookingAPI(requestBody);
//         ResponseCreateBooking bookingResponse = Helper.convertResponseToObject(response, ResponseCreateBooking.class);
        
//         Assert.assertEquals(response.statusCode(), 200, "Create booking failed");
//         bookingId = bookingResponse.bookingId;
        
//         // ✅ Token sudah tersedia dari BaseTest (di-set di @BeforeMethod)
//         System.out.println("Booking created with ID: " + bookingId);
//     }

//     @Test(priority = 2, dependsOnMethods = "testCreateBooking")
//     public void testPartialUpdateBooking() {
//         System.out.println("=== Scenario 3: Partial Update Booking ===");
        
//         String partialUpdateBody = """
//             {
//                 "firstname": "PartiallyUpdated",
//                 "additionalneeds": "Lunch"
//             }
//             """;
        
//         // ✅ Token sudah tersedia dari BaseTest
//         Response response = BooksCollectionAPI.partialUpdateBookingAPI(bookingId, partialUpdateBody, token);
//         ResponseCreateBooking bookingResponse = Helper.convertResponseToObject(response, ResponseCreateBooking.class);
        
//         Assert.assertEquals(response.statusCode(), 200, "Partial update booking failed");
//         Assert.assertEquals(bookingResponse.booking.firstName, "PartiallyUpdated", "Firstname not updated");
        
//         System.out.println("Booking partially updated successfully");
//     }

//     @Test(priority = 3, dependsOnMethods = "testPartialUpdateBooking")
//     public void testVerifyPartialUpdate() {
//         System.out.println("=== Scenario 3: Verify Partial Update ===");
        
//         Response response = BooksCollectionAPI.getBookingAPI(bookingId);
//         ResponseCreateBooking bookingResponse = Helper.convertResponseToObject(response, ResponseCreateBooking.class);
        
//         Assert.assertEquals(response.statusCode(), 200, "Get booking after partial update failed");
//         Assert.assertEquals(bookingResponse.booking.firstName, "PartiallyUpdated", "Partial update not persisted");
//         Assert.assertEquals(bookingResponse.booking.lastName, "Johnson", "Lastname should remain unchanged");
        
//         System.out.println("Partial update verified successfully");
//     }

//     @Test(priority = 4, dependsOnMethods = "testVerifyPartialUpdate")
//     public void testDeleteBooking() {
//         System.out.println("=== Scenario 3: Delete Booking ===");
        
//         // ✅ Token sudah tersedia dari BaseTest
//         Response response = BooksCollectionAPI.deleteBookingAPI(bookingId, token);
        
//         Assert.assertEquals(response.statusCode(), 201, "Delete booking failed");
        
//         // Verify booking is deleted
//         Response getResponse = BooksCollectionAPI.getBookingAPI(bookingId);
//         Assert.assertEquals(getResponse.statusCode(), 404, "Booking should not exist after deletion");
        
//         System.out.println("Booking deleted and verified successfully");
//     }
// }
