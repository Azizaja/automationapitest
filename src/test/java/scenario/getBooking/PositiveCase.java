package scenario.getBooking;

import org.testng.annotations.Test;

import com.apitest.base.BaseTest;

import apiengine.BooksColectionAPI;

public class PositiveCase extends BaseTest {
    public BooksColectionAPI getBookingAPI;

    @Test
    public void GetBookings() {
        System.out.println("Get Booking");
        BooksColectionAPI.getBooksFromCollectionsAPI()
                .then()
                .log().all();
        System.out.println("Get Booking Selesai");
    }


}
