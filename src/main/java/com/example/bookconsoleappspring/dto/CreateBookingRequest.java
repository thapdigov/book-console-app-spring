package com.example.bookconsoleappspring.dto;

public record CreateBookingRequest(
        Integer flightId,
        Integer passengerId
) {
}
