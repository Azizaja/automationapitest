package convert_data;

import com.apitest.model.request.RequestCreateBooking;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData {

    public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
        String requestBody = """
            {
                "firstname": "Jim",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
            """;

        ObjectMapper objectMapper = new ObjectMapper();
        RequestCreateBooking requestCreateBooking = objectMapper.readValue(requestBody, RequestCreateBooking.class);
        System.out.println("--- Convert JSON String to Java Object ---");
        System.out.println("First Name: " + requestCreateBooking.firstName);
        System.out.println("Last Name: " + requestCreateBooking.lastName);
        System.out.println("Total Price: " + requestCreateBooking.totalPrice);
        System.out.println("Deposit Paid: " + requestCreateBooking.depositPaid);
        System.out.println("Check-in Date: " + requestCreateBooking.bookingDates.checkIn);
        System.out.println("Check-out Date: " + requestCreateBooking.bookingDates.checkOut);
        System.out.println("Additional Needs: " + requestCreateBooking.additionalNeeds);
    }
}
