package com.example.bookconsoleappspring.dto;

import java.time.LocalDateTime;

public record BookingDto(
        int bookingId,
        String fullName,
        String from,
        String to,
        LocalDateTime time
) {
}
