package com.example.bookconsoleappspring.controller;

import com.example.bookconsoleappspring.dto.CreatePassengerDto;
import com.example.bookconsoleappspring.dto.PassengerDto;
import com.example.bookconsoleappspring.mapper.PassengerMapper;
import com.example.bookconsoleappspring.service.PassengerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    private final PassengerService passengerService;
    private final PassengerMapper passengerMapper;

    public PassengerController(PassengerService passengerService, PassengerMapper passengerMapper) {
        this.passengerService = passengerService;
        this.passengerMapper = passengerMapper;
    }


    @GetMapping
    public List<PassengerDto> getAllPassenger() {
        return passengerService.getAllPassenger();
    }

    @GetMapping("/{id}")
    public PassengerDto getAllPassenger(@PathVariable int id) {
        return passengerService.getById(id);
    }

    @PostMapping
    public PassengerDto createPassenger(@RequestBody CreatePassengerDto request) {
        return passengerService.createPassenger(request);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable int id) {
        passengerService.deleteById(id);
    }
}
