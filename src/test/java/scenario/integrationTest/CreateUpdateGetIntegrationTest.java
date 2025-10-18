package scenario.integrationTest;

// import org.testng.Assert;
// import org.testng.annotations.Test;

// import com.apitest.base.BaseTest;
// import com.apitest.model.request.RequestCreateBooking;
// import com.apitest.model.response.ResponseCreateBoking;
// import com.apitest.utils.Helper;

// import apiengine.BooksColectionAPI;
// import io.restassured.response.Response;

// public class CreateUpdateGetIntegrationTest extends BaseTest {

//     @Test(priority = 1)
//     public void testCreateBooking() {
//         System.out.println("=== Scenario 2: Create Booking ===");
        
//         RequestCreateBooking bookingData = Helper.findByUseCase(
//             "booking_data.json", "add_booking_data2", RequestCreateBooking.class);
//         String requestBody = Helper.convertToJson(bookingData);
        
//         Response response = BooksCollectionAPI.createBookingAPI(requestBody);
//         ResponseCreateBooking bookingResponse = Helper.convertResponseToObject(response, ResponseCreateBooking.class);
        
//         Assert.assertEquals(response.statusCode(), 200, "Create booking failed");
//         bookingId = bookingResponse.bookingId;
//         System.out.println("Booking created with ID: " + bookingId);
//     }

//     @Test(priority = 2, dependsOnMethods = "testCreateBooking")
//     public void testUpdateBooking() {
//         System.out.println("=== Scenario 2: Update Booking ===");
        
//         // ✅ Token sudah tersedia dari BaseTest
//         RequestCreateBooking updateData = Helper.findByUseCase(
//             "booking_data.json", "update_booking_data", RequestCreateBooking.class);
//         String updateBody = Helper.convertToJson(updateData);
        
//         Response response = BooksCollectionAPI.updateBookingAPI(bookingId, updateBody, token);
//         ResponseCreateBooking bookingResponse = Helper.convertResponseToObject(response, ResponseCreateBooking.class);
        
//         Assert.assertEquals(response.statusCode(), 200, "Update booking failed");
//         Assert.assertEquals(bookingResponse.booking.firstName, "UpdatedJohn", "Firstname not updated");
        
//         System.out.println("Booking updated successfully");
//     }

//     @Test(priority = 3, dependsOnMethods = "testUpdateBooking")
//     public void testVerifyUpdate() {
//         System.out.println("=== Scenario 2: Verify Update ===");
        
//         Response response = BooksCollectionAPI.getBookingAPI(bookingId);
//         ResponseCreateBooking bookingResponse = Helper.convertResponseToObject(response, ResponseCreateBooking.class);
        
//         Assert.assertEquals(response.statusCode(), 200, "Get booking after update failed");
//         Assert.assertEquals(bookingResponse.booking.firstName, "UpdatedJohn", "Update not persisted");
        
//         System.out.println("Update verified successfully");
        
//         // Cleanup
//         BooksCollectionAPI.deleteBookingAPI(bookingId, token);
//     }
// }