package com.example.bookconsoleappspring.service;

import com.example.bookconsoleappspring.dto.CreatePassengerDto;
import com.example.bookconsoleappspring.dto.PassengerDto;
import com.example.bookconsoleappspring.entity.Passenger;
import com.example.bookconsoleappspring.mapper.PassengerMapper;
import com.example.bookconsoleappspring.repository.PassengerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    public PassengerService(PassengerRepository passengerRepository, PassengerMapper passengerMapper) {
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }

    public List<PassengerDto> getAllPassenger() {
        return passengerRepository.findAll().stream()
                .map(passengerMapper::toPassengerDto)
                .toList();
    }

    public PassengerDto getById(int id) {
        return passengerRepository.findById(id)
                .map(passengerMapper::toPassengerDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public PassengerDto createPassenger(CreatePassengerDto request) {
        Passenger passenger = new Passenger();

        passenger.setName(request.name());
        passenger.setSurname(request.surname());
        passenger.setCreatedAt(LocalDateTime.now());
        passenger.setUpdateAt(LocalDateTime.now());

        Passenger savedPassenger = passengerRepository.save(passenger);

        return new PassengerDto(
                savedPassenger.getPassengerID(),
                savedPassenger.getName(),
                savedPassenger.getSurname()
        );
    }

    public void deleteById(int id) {
        passengerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        passengerRepository.deleteById(id);
    }
}
