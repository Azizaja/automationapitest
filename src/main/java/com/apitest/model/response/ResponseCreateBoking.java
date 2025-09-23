package com.apitest.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCreateBoking {

    // konsep deserialization yaitu ubah JSON response dari API menjadi objek Java untuk response body
    // sesuai dengan struktur JSON response dari API
    // ubah JSON property menjadi atribut di kelas ini
    // gunakan anotasi @JsonProperty untuk mapping nama property JSON ke atribut Java

    @JsonProperty("bookingid")
    public int bookingId;

    @JsonProperty("booking")
    public Booking booking;
    public static class Booking {
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
    }


}
