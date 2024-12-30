package com.example.bookconsoleappspring.mapper;

import com.example.bookconsoleappspring.dto.CreateFlightResponse;
import com.example.bookconsoleappspring.dto.FlightDto;
import com.example.bookconsoleappspring.entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightMapperTest {

    @InjectMocks
    private FlightMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toFlightDto() {
        Flight mockFlight = new Flight();
        mockFlight.setFlightID(1);
        mockFlight.setFrom("Baku");
        mockFlight.setTo("London");
        mockFlight.setTime(LocalDateTime.now().plusDays(1));
        mockFlight.setAvailableSeats(150);

        FlightDto flightDto = mapper.toFlightDto(mockFlight);

        assertNotNull(flightDto);
        assertEquals(mockFlight.getFlightID(), flightDto.flightID());
        assertEquals(mockFlight.getFrom(), flightDto.from());
        assertEquals(mockFlight.getTo(), flightDto.to());
        assertEquals(mockFlight.getTime(), flightDto.time());
        assertEquals(mockFlight.getAvailableSeats(), flightDto.availableSeats());
    }

    @Test
    void toCreateFlightResponse() {
        Flight mockFlight = new Flight();
        mockFlight.setFlightID(1);
        mockFlight.setFrom("Baku");
        mockFlight.setTo("London");
        mockFlight.setTime(LocalDateTime.now().plusDays(1));
        mockFlight.setAvailableSeats(150);

        CreateFlightResponse createFlightResponse = mapper.toCreateFlightResponse(mockFlight);

        assertNotNull(createFlightResponse);
        assertEquals(mockFlight.getFlightID(), createFlightResponse.flightID());
        assertEquals(mockFlight.getFrom(), createFlightResponse.from());
        assertEquals(mockFlight.getTo(), createFlightResponse.to());
        assertEquals(mockFlight.getTime(), createFlightResponse.time());
        assertEquals(mockFlight.getAvailableSeats(), createFlightResponse.availableSeats());
    }
}