package com.example.bookconsoleappspring.service;

import com.example.bookconsoleappspring.dto.CreatePassengerDto;
import com.example.bookconsoleappspring.dto.PassengerDto;
import com.example.bookconsoleappspring.entity.Passenger;
import com.example.bookconsoleappspring.mapper.PassengerMapper;
import com.example.bookconsoleappspring.repository.PassengerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PassengerServiceTest {

    @InjectMocks
    private PassengerService passengerService;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private PassengerMapper passengerMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPassenger() {
        Passenger passenger = new Passenger();
        PassengerDto passengerDto = new PassengerDto(1, "Saleh", "Samadli");
        when(passengerRepository.findAll()).thenReturn(List.of(passenger));
        when(passengerMapper.toPassengerDto(passenger)).thenReturn(passengerDto);

        List<PassengerDto> result = passengerService.getAllPassenger();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(passengerRepository, times(1)).findAll();
        verify(passengerMapper, times(1)).toPassengerDto(passenger);
    }

    @Test
    void testGetByIdFound() {
        Passenger passenger = new Passenger();
        passenger.setPassengerID(1);
        passenger.setName("Saleh");
        passenger.setSurname("Samadli");
        PassengerDto passengerDto = new PassengerDto(1, "Saleh", "Samadli");
        when(passengerRepository.findById(1)).thenReturn(Optional.of(passenger));
        when(passengerMapper.toPassengerDto(passenger)).thenReturn(passengerDto);

        PassengerDto result = passengerService.getById(1);

        assertNotNull(result);
        assertEquals("Saleh", result.name());
        verify(passengerRepository, times(1)).findById(1);
        verify(passengerMapper, times(1)).toPassengerDto(passenger);
    }

    @Test
    void testGetByIdNotFound() {
        when(passengerRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> passengerService.getById(1));
        verify(passengerRepository, times(1)).findById(1);
    }

    @Test
    void testCreatePassenger() {
        CreatePassengerDto request = new CreatePassengerDto("Saleh", "Samadli");
        Passenger passenger = new Passenger();
        passenger.setPassengerID(1);
        passenger.setName("Saleh");
        passenger.setSurname("Samadli");
        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);

        PassengerDto result = passengerService.createPassenger(request);

        assertNotNull(result);
        assertEquals("Saleh", result.name());
        assertEquals("Samadli", result.surname());
        verify(passengerRepository, times(1)).save(any(Passenger.class));
    }

    @Test
    void testDeleteByIdFound() {
        when(passengerRepository.findById(1)).thenReturn(Optional.of(new Passenger()));

        passengerService.deleteById(1);

        verify(passengerRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteByIdNotFound() {
        when(passengerRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> passengerService.deleteById(1));
        verify(passengerRepository, times(1)).findById(1);
    }
}