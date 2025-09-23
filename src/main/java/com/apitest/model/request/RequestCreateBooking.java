package com.apitest.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

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
