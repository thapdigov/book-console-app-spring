package com.example.bookconsoleappspring.mapper;

import com.example.bookconsoleappspring.dto.PassengerDto;
import com.example.bookconsoleappspring.entity.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class PassengerMapperTest {

    @InjectMocks
    private PassengerMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void toPassengerDto() {
        Passenger mockPassenger = new Passenger();
        mockPassenger.setPassengerID(1);
        mockPassenger.setName("Semmed");
        mockPassenger.setSurname("Kosayev");

        PassengerDto passengerDto = mapper.toPassengerDto(mockPassenger);

        assertNotNull(passengerDto);
        assertEquals(mockPassenger.getPassengerID(), passengerDto.passengerId());
        assertEquals(mockPassenger.getName(), passengerDto.name());
        assertEquals(mockPassenger.getSurname(), passengerDto.surname());
    }

}