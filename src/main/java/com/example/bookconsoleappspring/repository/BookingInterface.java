package com.example.bookconsoleappspring.repository;

import com.example.bookconsoleappspring.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingInterface extends JpaRepository<Booking, Integer> {
}
