package com.example.bookconsoleappspring.mapper;

import com.example.bookconsoleappspring.dto.BookingDto;
import com.example.bookconsoleappspring.entity.Booking;
import com.example.bookconsoleappspring.entity.Flight;
import com.example.bookconsoleappspring.entity.Passenger;
import com.example.bookconsoleappspring.repository.FlightRepository;
import com.example.bookconsoleappspring.repository.PassengerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BookingMapperTest {

    @InjectMocks
    private BookingMapper mapper;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private FlightRepository flightRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toBookingDto() {
        Booking mockBooking = new Booking();
        mockBooking.setBookingId(1);
        mockBooking.setFlightId(1);
        mockBooking.setPassengerId(1);

        Passenger mockPassenger = new Passenger();
        mockPassenger.setName("Semmed");
        mockPassenger.setSurname("Kosayev");
        Optional<Passenger> optionalMockPassenger = Optional.of(mockPassenger);

        Flight mockFlight = new Flight();
        mockFlight.setFrom("Baku");
        mockFlight.setTo("London");
        mockFlight.setTime(LocalDateTime.now().plusDays(1));

        when(passengerRepository.findById(1)).thenReturn(optionalMockPassenger);
        when(flightRepository.findById(1)).thenReturn(Optional.of(mockFlight));

        BookingDto bookingDto = mapper.toBookingDto(mockBooking);

        assertNotNull(bookingDto);
        assertEquals(mockBooking.getBookingId(), bookingDto.bookingId());
        assertEquals(mockPassenger.getFullName(), bookingDto.fullName());
        assertEquals(mockFlight.getFrom(), bookingDto.from());
        assertEquals(mockFlight.getTo(), bookingDto.to());
        assertEquals(mockFlight.getTime(), bookingDto.time());
    }
}