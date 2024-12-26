package com.example.bookconsoleappspring.dto;

public record CreateBookingRequest(
        Integer bookingId,
        Integer flightId,
        Integer passengerId
) {
}
