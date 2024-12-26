package com.example.bookconsoleappspring.service;

import com.example.bookconsoleappspring.dto.CreateFlightRequest;
import com.example.bookconsoleappspring.dto.CreateFlightResponse;
import com.example.bookconsoleappspring.dto.FlightDto;
import com.example.bookconsoleappspring.entity.Flight;
import com.example.bookconsoleappspring.mapper.FlightMapper;
import com.example.bookconsoleappspring.repository.FlightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    public List<FlightDto> getAll() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toFlightDto)
                .toList();
    }


    public List<FlightDto> getAllWithin24Hours() {
        return flightRepository
                .findFlightsForNext24Hours(LocalDateTime.now(), LocalDateTime.now().plusDays(1))
                .stream().map(flightMapper::toFlightDto)
                .toList();
    }

    public FlightDto getById(int id) {
        return flightRepository.findById(id)
                .map(flightMapper::toFlightDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public CreateFlightResponse save(CreateFlightRequest request) {
        Flight flight = new Flight();
        LocalDateTime time = LocalDateTime.of(
                request.year(),
                request.month(),
                request.day(),
                request.hour(),
                request.minute()
        );

        flight.setFrom(request.from());
        flight.setTo(request.to());
        flight.setTime(time);
        flight.setCreatedAt(LocalDateTime.now());
        flight.setUpdateAt(LocalDateTime.now());
        flight.setTotalSeats(request.totalSeats());
        flight.setAvailableSeats(request.totalSeats());

        Flight savedFlight = flightRepository.save(flight);
        return flightMapper.toCreateFlightResponse(savedFlight);
    }

    public void deleteFlight(int id) {
        flightRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        flightRepository.deleteById(id);
    }
}
