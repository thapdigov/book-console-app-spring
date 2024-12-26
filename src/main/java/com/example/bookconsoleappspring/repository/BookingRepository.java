package com.example.bookconsoleappspring.repository;

import com.example.bookconsoleappspring.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
