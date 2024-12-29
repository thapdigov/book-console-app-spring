package com.example.bookconsoleappspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "passengers")
public class Passenger {
    @Id
    @SequenceGenerator(name = "passenger_seq", allocationSize = 1)
    @GeneratedValue(generator = "passenger_seq")
    private Integer passengerID;
    private String name;
    private String surname;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    @JsonIgnore
    public String getFullName() {
        return name + " " + surname;
    }
}
