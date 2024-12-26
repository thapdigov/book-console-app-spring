package com.example.bookconsoleappspring.mapper;

import com.example.bookconsoleappspring.dto.PassengerDto;
import com.example.bookconsoleappspring.entity.Passenger;
import org.springframework.stereotype.Service;

@Service
public class PassengerMapper {

    public PassengerDto toPassengerDto(Passenger passenger) {
        return new PassengerDto(
                passenger.getPassengerID(),
                passenger.getName(),
                passenger.getSurname()
        );
    }
}
