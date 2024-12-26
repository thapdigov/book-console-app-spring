package com.example.bookconsoleappspring.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record GetByIdException(
        String message,
        HttpStatus status,
        LocalDateTime time
) {
}
