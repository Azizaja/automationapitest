package com.apitest.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

// konsep serialization yaitu ubah objek Java menjadi JSON untuk request body
// sesuai dengan struktur JSON request body yang dikirim ke API
// ubah JSON property menjadi atribut di kelas ini
// gunakan anotasi @JsonProperty untuk mapping nama property JSON ke atribut Java

public class RequestCreateBooking {
    @JsonProperty("firstname")
    public String firstName;

    @JsonProperty("lastname")
    public String lastName;

    @JsonProperty("totalprice")
    public int totalPrice;
    @JsonProperty("depositpaid")
    public boolean depositPaid;
    @JsonProperty("bookingdates")
    public BookingDates bookingDates;
    @JsonProperty("additionalneeds")
    public String additionalNeeds;

    // Getter and Setter methods 
    public static class BookingDates {
        @JsonProperty("checkin")
        public String checkIn;
        @JsonProperty("checkout")
        public String checkOut;
        // Getter and Setter methods 
    }

    // Constructor
    
}
