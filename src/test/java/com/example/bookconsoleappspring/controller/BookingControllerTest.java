package com.example.bookconsoleappspring.controller;

import com.example.bookconsoleappspring.dto.BookingDto;
import com.example.bookconsoleappspring.dto.CreateBookingRequest;
import com.example.bookconsoleappspring.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;

    @Mock
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        List<BookingDto> mockBookings = new ArrayList<>();
        mockBookings.add(new BookingDto(1, "Saleh Samadli", "Baku", "London", LocalDateTime.now()));
        when(bookingService.getAll()).thenReturn(mockBookings);

        List<BookingDto> result = bookingController.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookingService, times(1)).getAll();
    }

    @Test
    void testGetById() {
        BookingDto mockBooking = new BookingDto(1, "Saleh Samadli", "Baku", "London", LocalDateTime.now());
        when(bookingService.getById(1)).thenReturn(mockBooking);

        BookingDto result = bookingController.getById(1);

        assertNotNull(result);
        verify(bookingService, times(1)).getById(1);
    }

    @Test
    void testCreateBooking() {
        CreateBookingRequest request = new CreateBookingRequest(1, 1);
        BookingDto mockBooking = new BookingDto(1, "Saleh Samadli", "Baku", "London", LocalDateTime.now());
        when(bookingService.createBooking(any(CreateBookingRequest.class))).thenReturn(mockBooking);

        BookingDto result = bookingController.createBooking(request);

        assertNotNull(result);
        verify(bookingService, times(1)).createBooking(request);
    }

    @Test
    void testDeleteBooking() {
        int bookingId = 1;

        bookingController.deleteBooking(bookingId);

        verify(bookingService, times(1)).deleteById(bookingId);
    }
}