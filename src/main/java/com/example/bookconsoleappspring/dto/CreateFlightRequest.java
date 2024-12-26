package com.example.bookconsoleappspring.dto;

public record CreateFlightRequest(
        String from,
        String to,
        int totalSeats,
        int year,
        int month,
        int day,
        int hour,
        int minute
) {
}
