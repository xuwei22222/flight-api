package com.fullack.flight_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullack.flight_api.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	Optional<List<Booking>> findByUser_userId(Long id);

	Optional<Booking> findById(Long id);

	@Query("select b from Booking b join b.flight join b.user where b.status = :status and b.user.userId = :userId order by b.bookingId limit 10 offset :num")
	Optional<List<Booking>> getBookList(@Param("status") Booking.Status status , @Param("userId") Long userId,
			@Param("num") int num);
}