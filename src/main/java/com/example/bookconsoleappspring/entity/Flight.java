package com.example.bookconsoleappspring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "flights")
public class Flight {
    @Id
    @SequenceGenerator(name = "flight_seq", allocationSize = 1)
    @GeneratedValue(generator = "flight_seq")
    private Integer flightID;
    @Column(name = "_from")
    private String from;
    @Column(name = "_to")
    private String to;
    private LocalDateTime time;
    private Integer totalSeats;
    private Integer availableSeats;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
