package com.example.bookconsoleappspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Passenger {
    @Id
    @SequenceGenerator(name = "flight_seq", allocationSize = 1)
    @GeneratedValue(generator = "flight_seq")
    private Integer passengerID;
    private String name;
    private String surname;

    @JsonIgnore
    public String fullName() {
        return name + surname;
    }
}
