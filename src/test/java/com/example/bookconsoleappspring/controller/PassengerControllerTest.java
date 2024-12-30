package com.example.bookconsoleappspring.controller;

import com.example.bookconsoleappspring.dto.CreatePassengerDto;
import com.example.bookconsoleappspring.dto.PassengerDto;
import com.example.bookconsoleappspring.service.PassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PassengerControllerTest {

    @InjectMocks
    private PassengerController passengerController;

    @Mock
    private PassengerService passengerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPassenger() {
        List<PassengerDto> mockPassengers = new ArrayList<>();
        mockPassengers.add(new PassengerDto(1, "Saleh", "Samadli"));
        when(passengerService.getAllPassenger()).thenReturn(mockPassengers);

        List<PassengerDto> result = passengerController.getAllPassenger();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(passengerService, times(1)).getAllPassenger();
    }

    @Test
    void testGetPassengerById() {
        PassengerDto mockPassenger = new PassengerDto(1, "Saleh", "Samadli");
        when(passengerService.getById(1)).thenReturn(mockPassenger);

        PassengerDto result = passengerController.getAllPassenger(1);

        assertNotNull(result);
        verify(passengerService, times(1)).getById(1);
    }

    @Test
    void testCreatePassenger() {
        CreatePassengerDto request = new CreatePassengerDto("Saleh", "Samadli");
        PassengerDto mockPassenger = new PassengerDto(1, "Saleh", "Samadli");
        when(passengerService.createPassenger(any(CreatePassengerDto.class))).thenReturn(mockPassenger);

        PassengerDto result = passengerController.createPassenger(request);

        assertNotNull(result);
        verify(passengerService, times(1)).createPassenger(request);
    }

    @Test
    void testDeletePassenger() {
        int passengerId = 1;

        passengerController.deletePassenger(passengerId);

        verify(passengerService, times(1)).deleteById(passengerId);
    }
}