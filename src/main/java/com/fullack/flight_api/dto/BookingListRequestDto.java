package com.fullack.flight_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingListRequestDto {
	private String status;
	private int page;
	private int size;

}
