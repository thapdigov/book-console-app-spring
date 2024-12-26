package com.example.bookconsoleappspring.repository;

import com.example.bookconsoleappspring.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}
