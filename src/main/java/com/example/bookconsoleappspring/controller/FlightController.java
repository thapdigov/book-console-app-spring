package com.example.bookconsoleappspring.controller;

import com.example.bookconsoleappspring.dto.CreateFlightRequest;
import com.example.bookconsoleappspring.dto.CreateFlightResponse;
import com.example.bookconsoleappspring.dto.FlightDto;
import com.example.bookconsoleappspring.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<FlightDto> getAllFlight(@RequestParam boolean within24hours) {
        if (within24hours) {
            return flightService.getAllWithin24Hours();
        }

        return flightService.getAll();
    }

    @GetMapping("{id}")
    public FlightDto getById(@PathVariable int id) {
        return flightService.getById(id);
    }

    @PostMapping
    public ResponseEntity<CreateFlightResponse> createFlight(@RequestBody CreateFlightRequest request) {
        CreateFlightResponse savedFlight = flightService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(savedFlight);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFLight(@PathVariable int id) {
        flightService.deleteFlight(id);
    }
}
