package com.fullack.flight_api.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "flight")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long flightId;

	@Column(unique = true, nullable = false)
	private String flightNumber;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "departure_airport_id")
	private Airport departure;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "destination_airport_id")
	private Airport destination;

	@Column(nullable = false)
	private LocalDate departureDate;

	@Column(unique = true, nullable = false)
	private LocalTime departureTime;
	
	@Column(nullable = false)
	private LocalDate destinationDate;

	@Column(unique = true, nullable = false)
	private LocalTime destinationTime;

	@Column(name = "taxes", precision = 10, scale = 2)
	private BigDecimal taxes;

	@Column(name = "price", precision = 10, scale = 2)
	private BigDecimal price;
}
