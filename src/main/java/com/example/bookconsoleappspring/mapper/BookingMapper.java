package com.example.bookconsoleappspring.mapper;

import com.example.bookconsoleappspring.dto.BookingDto;
import com.example.bookconsoleappspring.entity.Booking;
import com.example.bookconsoleappspring.entity.Flight;
import com.example.bookconsoleappspring.entity.Passenger;
import com.example.bookconsoleappspring.repository.FlightRepository;
import com.example.bookconsoleappspring.repository.PassengerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookingMapper {

    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;

    public BookingMapper(PassengerRepository passengerRepository, FlightRepository flightRepository) {
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
    }


    public BookingDto toBookingDto(Booking booking) {
        String fullname = passengerRepository
                .findById(booking.getPassengerId()).map(Passenger::getFullName)
                .orElseThrow(EntityNotFoundException::new);

        Flight flight = flightRepository.findById(booking.getFlightId())
                .orElseThrow(EntityNotFoundException::new);

        return new BookingDto(
                booking.getBookingId(),
                fullname,
                flight.getFrom(),
                flight.getTo(),
                flight.getTime()
        );
    }
}
