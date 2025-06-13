package com.fullack.flight_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightListRequestDto {
    private String from;
    private String to;
    private String date;
    private int page; 
}
