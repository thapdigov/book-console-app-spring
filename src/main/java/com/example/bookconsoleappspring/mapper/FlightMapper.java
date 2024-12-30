package com.example.bookconsoleappspring.mapper;

import com.example.bookconsoleappspring.dto.CreateFlightResponse;
import com.example.bookconsoleappspring.dto.FlightDto;
import com.example.bookconsoleappspring.entity.Flight;
import org.springframework.stereotype.Service;

@Service
public class FlightMapper {

    public FlightDto toFlightDto(Flight flight) {
        return new FlightDto(
                flight.getFlightID(),
                flight.getFrom(),
                flight.getTo(),
                flight.getTime(),
                flight.getAvailableSeats()
        );
    }

    public CreateFlightResponse toCreateFlightResponse(Flight flight) {
        return new CreateFlightResponse(
                flight.getFlightID(),
                flight.getFrom(),
                flight.getTo(),
                flight.getTime(),
                flight.getAvailableSeats()
        );
    }
}
