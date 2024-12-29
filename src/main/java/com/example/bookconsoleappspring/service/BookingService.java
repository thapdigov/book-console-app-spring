package com.example.bookconsoleappspring.service;

import com.example.bookconsoleappspring.dto.BookingDto;
import com.example.bookconsoleappspring.dto.CreateBookingRequest;
import com.example.bookconsoleappspring.entity.Booking;
import com.example.bookconsoleappspring.mapper.BookingMapper;
import com.example.bookconsoleappspring.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    public List<BookingDto> getAll() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toBookingDto)
                .toList();
    }

    public BookingDto getById(int id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toBookingDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public BookingDto createBooking(CreateBookingRequest request) {
        Booking booking = new Booking();

        booking.setFlightId(request.flightId());
        booking.setPassengerId(request.passengerId());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdateAt(LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toBookingDto(savedBooking);
    }

    public void deleteById(int id) {
        bookingRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        bookingRepository.deleteById(id);
    }
}
