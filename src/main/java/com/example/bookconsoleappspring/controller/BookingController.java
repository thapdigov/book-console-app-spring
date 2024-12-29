package com.example.bookconsoleappspring.controller;

import com.example.bookconsoleappspring.dto.BookingDto;
import com.example.bookconsoleappspring.dto.CreateBookingRequest;
import com.example.bookconsoleappspring.service.BookingService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<BookingDto> getAll() {
        return bookingService.getAll();
    }

    @GetMapping("/{id}")
    public BookingDto getById(@PathVariable int id) {
        return bookingService.getById(id);
    }

    @PostMapping
    public BookingDto createBooking(@RequestBody CreateBookingRequest request) {
        return bookingService.createBooking(request);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable int id) {
        bookingService.deleteById(id);
    }
}
