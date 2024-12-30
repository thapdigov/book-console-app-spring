package com.example.bookconsoleappspring.service;

import com.example.bookconsoleappspring.dto.CreateFlightRequest;
import com.example.bookconsoleappspring.dto.CreateFlightResponse;
import com.example.bookconsoleappspring.dto.FlightDto;
import com.example.bookconsoleappspring.entity.Flight;
import com.example.bookconsoleappspring.mapper.FlightMapper;
import com.example.bookconsoleappspring.repository.FlightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FlightServiceTest {

    @InjectMocks
    private FlightService flightService;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightMapper flightMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Flight flight = new Flight();
        FlightDto flightDto = new FlightDto(1, "Baku", "London", LocalDateTime.now(), 100);
        when(flightRepository.findAll()).thenReturn(List.of(flight));
        when(flightMapper.toFlightDto(flight)).thenReturn(flightDto);

        List<FlightDto> result = flightService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(flightRepository, times(1)).findAll();
        verify(flightMapper, times(1)).toFlightDto(flight);
    }

    @Test
    void testGetAllWithin24Hours() {
        Flight flight = new Flight();
        FlightDto flightDto = new FlightDto(1, "Baku", "London", LocalDateTime.now(), 100);
        when(flightRepository.findFlightsForNext24Hours(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(List.of(flight));
        when(flightMapper.toFlightDto(flight)).thenReturn(flightDto);

        List<FlightDto> result = flightService.getAllWithin24Hours();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(flightRepository, times(1)).findFlightsForNext24Hours(any(LocalDateTime.class), any(LocalDateTime.class));
        verify(flightMapper, times(1)).toFlightDto(flight);
    }

    @Test
    void testGetByIdFound() {
        Flight flight = new Flight();
        FlightDto flightDto = new FlightDto(1, "Baku", "London", LocalDateTime.now(), 100);
        when(flightRepository.findById(1)).thenReturn(Optional.of(flight));
        when(flightMapper.toFlightDto(flight)).thenReturn(flightDto);

        FlightDto result = flightService.getById(1);

        assertNotNull(result);
        verify(flightRepository, times(1)).findById(1);
        verify(flightMapper, times(1)).toFlightDto(flight);
    }

    @Test
    void testGetByIdNotFound() {
        // Arrange
        when(flightRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> flightService.getById(1));
        verify(flightRepository, times(1)).findById(1);
    }

    @Test
    void testSave() {
        CreateFlightRequest request = new CreateFlightRequest("Baku", "London", 200, 2024, 12, 30, 23, 59);
        Flight savedFlight = new Flight();
        CreateFlightResponse response = new CreateFlightResponse(1, "Baku", "London", LocalDateTime.now(), 100);

        when(flightMapper.toCreateFlightResponse(savedFlight)).thenReturn(response);
        when(flightRepository.save(any(Flight.class))).thenReturn(savedFlight);

        CreateFlightResponse result = flightService.save(request);

        assertNotNull(result);
        verify(flightRepository, times(1)).save(any(Flight.class));
        verify(flightMapper, times(1)).toCreateFlightResponse(savedFlight);
    }

    @Test
    void testDeleteFlightFound() {
        when(flightRepository.findById(1)).thenReturn(Optional.of(new Flight()));

        flightService.deleteFlight(1);

        verify(flightRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteFlightNotFound() {
        when(flightRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> flightService.deleteFlight(1));
        verify(flightRepository, times(1)).findById(1);
    }
}