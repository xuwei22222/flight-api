package com.fullack.flight_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDto {
	private long bookingId;
	private FlightResponseDto flightResponseDto;
	private RegisterRequestDto user;
	private String reference;
	private String status;
	private String bookingTime;
	private String flightType;
	private String totalPrice;
}
