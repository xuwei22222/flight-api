package com.fullack.flight_api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullack.flight_api.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	@Query("select f from Flight f join fetch f.departure join fetch f.destination where f.departure.city = :from and f.destination.city = :to and f.departureDate = :date order by f.flightId limit 10 offset :num")
	Optional<List<Flight>> findFlights(@Param("from") String from, @Param("to") String to,
			@Param("date") LocalDate date, @Param("num") int num);

	Optional<Flight> findById(Long id);
	
	@Query("select COUNT(f) from Flight f join f.departure join f.destination where f.departure.city = :from and f.destination.city = :to and f.departureDate = :date")
	Optional<Long> getCount(@Param("from") String from, @Param("to") String to,
			@Param("date") LocalDate date);
}