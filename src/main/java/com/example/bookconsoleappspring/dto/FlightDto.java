package com.example.bookconsoleappspring.dto;

import java.time.LocalDateTime;

public record FlightDto(
        Integer flightID,
        String from,
        String to,
        LocalDateTime time,
        Integer availableSeats
) {
}
