package com.fullack.flight_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightResponseDto {
    private long id;
    private String flightNumber;
    private String departure;
    private String destination;
    private String departureDate;
    private String departureTime;
    private String destinationDate;
    private String destinationTime;
    private String taxes;
    private String price;
}