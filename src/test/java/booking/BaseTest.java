package booking;

public class BaseTest {
    protected static String token;
    protected static int bookingId;
    
    public static void setToken(String authToken) {
        token = authToken;
    }
    
    public static void setBookingId(int id) {
        bookingId = id;
    }
    
    public static String getToken() {
        return token;
    }
    
    public static int getBookingId() {
        return bookingId;
    }
}