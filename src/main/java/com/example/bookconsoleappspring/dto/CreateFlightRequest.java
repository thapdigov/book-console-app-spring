package com.example.bookconsoleappspring.dto;

public record CreateFlightRequest(
        String from,
        String to,
        String year,
        String month,
        String day,
        String hour,
        String minute
) {
}
