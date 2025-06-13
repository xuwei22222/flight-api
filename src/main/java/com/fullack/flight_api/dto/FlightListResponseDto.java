package com.fullack.flight_api.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightListResponseDto {
	private List<FlightResponseDto> flightResponseDto;
	private long count;
}
