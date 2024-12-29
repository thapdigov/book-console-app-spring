package com.example.bookconsoleappspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "bookings")
public class Booking {
    @Id
    @SequenceGenerator(name = "booking_seq", allocationSize = 1)
    @GeneratedValue(generator = "booking_seq")
    private Integer bookingId;
    private Integer flightId;
    private Integer passengerId;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
