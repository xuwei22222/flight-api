package com.fullack.flight_api.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BookingListResponseDto {
	private List<BookingResponseDto> bookingResponseDto;
}
