package com.fullack.flight_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullack.flight_api.dto.FlightDetilResponseDto;
import com.fullack.flight_api.dto.FlightListRequestDto;
import com.fullack.flight_api.dto.FlightListResponseDto;
import com.fullack.flight_api.dto.FlightResponseDto;
import com.fullack.flight_api.entity.Flight;
import com.fullack.flight_api.exception.ResourceNotFoundException;
import com.fullack.flight_api.repository.FlightRepository;
import com.fullack.flight_api.util.SetResponseDto;
import com.fullack.flight_api.util.Util;

@Service
public class FlightService {
	private final FlightRepository flightRepository;
	private final SetResponseDto setResponseDto;

	@Autowired
	public FlightService(FlightRepository flightRepository, SetResponseDto setResponseDto) {
		this.flightRepository = flightRepository;
		this.setResponseDto = setResponseDto;
	}

	public FlightListResponseDto getFlightList(FlightListRequestDto flightListRequestDto) {

		List<Flight> flights = flightRepository
				.findFlights(flightListRequestDto.getFrom(), flightListRequestDto.getTo(),
						Util.stringToDate(flightListRequestDto.getDate()), flightListRequestDto.getPage() * 20)
				.orElseThrow(() -> new ResourceNotFoundException("Flight List not found"));

		long count = flightRepository
				.getCount(flightListRequestDto.getFrom(), flightListRequestDto.getTo(),
						Util.stringToDate(flightListRequestDto.getDate()))
				.orElseThrow(() -> new ResourceNotFoundException("Flight Count not found"));
		
		List<FlightResponseDto> flightResponseDtoList = new ArrayList<FlightResponseDto>();
		for (Flight flight : flights) {
			FlightResponseDto flightResponseDto = new FlightResponseDto();
			setResponseDto.setFlightResponseDto(flightResponseDto, flight);
			flightResponseDtoList.add(flightResponseDto);
		}
		FlightListResponseDto flightListResponseDto = new FlightListResponseDto();
		flightListResponseDto.setFlightResponseDto(flightResponseDtoList);
		flightListResponseDto.setCount(count);
		return flightListResponseDto;
	}

	public FlightDetilResponseDto getFlightDetil(long id) {
		Flight flight = flightRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Flight List not found"));
		FlightDetilResponseDto flightDetilResponseDto = new FlightDetilResponseDto();
		FlightResponseDto flightResponseDto = new FlightResponseDto();
		setResponseDto.setFlightResponseDto(flightResponseDto, flight);
		flightDetilResponseDto.setFlightResponseDto(flightResponseDto);
		return flightDetilResponseDto;
	}
}
