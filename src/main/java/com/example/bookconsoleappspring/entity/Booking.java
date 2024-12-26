package com.example.bookconsoleappspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Booking {
    @Id
    @SequenceGenerator(name = "flight_seq", allocationSize = 1)
    @GeneratedValue(generator = "flight_seq")
    private Integer bookingId;
    private Integer flightId;
    private Integer passengerId;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
