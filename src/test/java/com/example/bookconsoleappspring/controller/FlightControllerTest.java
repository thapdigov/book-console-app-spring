package com.example.bookconsoleappspring.controller;

import com.example.bookconsoleappspring.dto.CreateFlightRequest;
import com.example.bookconsoleappspring.dto.CreateFlightResponse;
import com.example.bookconsoleappspring.dto.FlightDto;
import com.example.bookconsoleappspring.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FlightControllerTest {

    @InjectMocks
    private FlightController flightController;

    @Mock
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFlight() {
        List<FlightDto> mockFlights = new ArrayList<>();
        mockFlights.add(new FlightDto(1, "Baku", "London", LocalDateTime.now(), 100));
        when(flightService.getAll()).thenReturn(mockFlights);

        List<FlightDto> result = flightController.getAllFlight(false);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(flightService, times(1)).getAll();
    }

    @Test
    void testGetAllFlightWithin24Hours() {
        List<FlightDto> mockFlights = new ArrayList<>();
        mockFlights.add(new FlightDto(1, "Baku", "London", LocalDateTime.now(), 100));
        when(flightService.getAllWithin24Hours()).thenReturn(mockFlights);

        List<FlightDto> result = flightController.getAllFlight(true);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(flightService, times(1)).getAllWithin24Hours();
    }

    @Test
    void testGetById() {
        FlightDto mockFlight = new FlightDto(1, "Baku", "London", LocalDateTime.now(), 100);
        when(flightService.getById(1)).thenReturn(mockFlight);

        FlightDto result = flightController.getById(1);

        assertNotNull(result);
        verify(flightService, times(1)).getById(1);
    }

    @Test
    void testCreateFlight() {
        CreateFlightRequest request = new CreateFlightRequest("Baku", "London", 100, 2024, 12, 31, 22, 00);
        CreateFlightResponse mockResponse = new CreateFlightResponse(1, "Baku", "London", LocalDateTime.now(), 100);
        when(flightService.save(any(CreateFlightRequest.class))).thenReturn(mockResponse);

        ResponseEntity<CreateFlightResponse> result = flightController.createFlight(request);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(mockResponse, result.getBody());
        verify(flightService, times(1)).save(request);
    }

    @Test
    void testDeleteFlight() {
        int flightId = 1;

        flightController.deleteFLight(flightId);

        verify(flightService, times(1)).deleteFlight(flightId);
    }
}