package com.example.bookconsoleappspring.repository;

import com.example.bookconsoleappspring.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    @Query("SELECT f FROM Flight f WHERE f.time BETWEEN :now AND :tomorrow")
    List<Flight> findFlightsForNext24Hours(@Param("now") LocalDateTime now, @Param("tomorrow") LocalDateTime tomorrow);


}
