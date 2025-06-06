package com.fullack.flight_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingAddRequestDto {
	private long userId;
	private long flightId;
	private String reference;
	private String flightType;
	private String totalPrice;
}
