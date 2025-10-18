package com.apitest.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseGetBooking {
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

    public static class BookingDates {
        @JsonProperty("checkin")
        public String checkIn;

        @JsonProperty("checkout")
        public String checkOut;
    }
}