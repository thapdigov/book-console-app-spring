package com.example.bookconsoleappspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Flight {
    @Id
    @SequenceGenerator(name = "flight_seq", allocationSize = 1)
    @GeneratedValue(generator = "flight_seq")
    private Integer flightID;
    private String from;
    private String to;
    private LocalDateTime time;
    private Integer totalSeats;
    private Integer availableSeats;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
