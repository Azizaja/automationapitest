package scenario.getBooking;

import org.testng.annotations.Test;

import apiengine.BooksColectionAPI;

public class PositiveCase {
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
