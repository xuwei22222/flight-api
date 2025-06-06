package com.fullack.flight_api.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "flight_id", nullable = false)
	private Flight flight;

	@Column(nullable = false)
	private String reference;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;

	public enum Status {
		UPCOMING, PAST
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private FlightType flightType;

	public enum FlightType {
		DEPARTURE, RETURN
	}

	@Column(nullable = false)
	private LocalDateTime bookingTime;

	@Column(name = "total_price", precision = 10, scale = 2, nullable = false)
	private BigDecimal totalPrice;
}
