package com.example.bookconsoleappspring.service;

import com.example.bookconsoleappspring.dto.BookingDto;
import com.example.bookconsoleappspring.dto.CreateBookingRequest;
import com.example.bookconsoleappspring.entity.Booking;
import com.example.bookconsoleappspring.mapper.BookingMapper;
import com.example.bookconsoleappspring.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingMapper bookingMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Booking booking = new Booking();
        BookingDto bookingDto = new BookingDto(1, "Saleh Samadli", "Baku", "London", LocalDateTime.now());
        when(bookingRepository.findAll()).thenReturn(List.of(booking));
        when(bookingMapper.toBookingDto(booking)).thenReturn(bookingDto);

        List<BookingDto> result = bookingService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookingRepository, times(1)).findAll();
        verify(bookingMapper, times(1)).toBookingDto(booking);
    }

    @Test
    void testGetByIdFound() {
        Booking booking = new Booking();
        BookingDto bookingDto = new BookingDto(1, "Saleh Samadli", "Baku", "London", LocalDateTime.now());
        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));
        when(bookingMapper.toBookingDto(booking)).thenReturn(bookingDto);

        BookingDto result = bookingService.getById(1);

        assertNotNull(result);
        verify(bookingRepository, times(1)).findById(1);
        verify(bookingMapper, times(1)).toBookingDto(booking);
    }

    @Test
    void testGetByIdNotFound() {
        when(bookingRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bookingService.getById(1));
        verify(bookingRepository, times(1)).findById(1);
    }

    @Test
    void testCreateBooking() {
        CreateBookingRequest request = new CreateBookingRequest(1, 1);
        Booking booking = new Booking();
        Booking savedBooking = new Booking();
        BookingDto bookingDto = new BookingDto(1, "Saleh Samadli", "Baku", "London", LocalDateTime.now());

        when(bookingMapper.toBookingDto(savedBooking)).thenReturn(bookingDto);
        when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);

        BookingDto result = bookingService.createBooking(request);

        assertNotNull(result);
        verify(bookingRepository, times(1)).save(any(Booking.class));
        verify(bookingMapper, times(1)).toBookingDto(savedBooking);
    }

    @Test
    void testDeleteByIdFound() {
        when(bookingRepository.findById(1)).thenReturn(Optional.of(new Booking()));

        bookingService.deleteById(1);

        verify(bookingRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteByIdNotFound() {
        when(bookingRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bookingService.deleteById(1));
        verify(bookingRepository, times(1)).findById(1);
    }
}