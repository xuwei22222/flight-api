package com.fullack.flight_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullack.flight_api.dto.FlightDetilResponseDto;
import com.fullack.flight_api.dto.FlightListRequestDto;
import com.fullack.flight_api.dto.FlightListResponseDto;
import com.fullack.flight_api.http.ResponseMessage;
import com.fullack.flight_api.service.FlightService;

@RestController
@RequestMapping("/api")
public class FlightController {
	private final FlightService flightService;

	@Autowired
	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}

	@GetMapping("/flights")
	public ResponseEntity<ResponseMessage<FlightListResponseDto>> flightList(
			FlightListRequestDto flightListRequestDto) {
		FlightListResponseDto flightListResponseDto = flightService.getFlightList(flightListRequestDto);		
		return ResponseEntity.ok(ResponseMessage.success(flightListResponseDto));
	}

	@GetMapping("/flights/{id}")
	public ResponseEntity<ResponseMessage<FlightDetilResponseDto>> flighDetil(@PathVariable("id") long id) {
		FlightDetilResponseDto flightDetilResponseDto = flightService.getFlightDetil(id);

		return ResponseEntity.ok(ResponseMessage.success(flightDetilResponseDto));
	}
}
