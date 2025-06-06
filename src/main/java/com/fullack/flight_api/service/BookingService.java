package com.fullack.flight_api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullack.flight_api.config.JwtUtil;
import com.fullack.flight_api.dto.BookingAddRequestDto;
import com.fullack.flight_api.dto.BookingDetilResponseDto;
import com.fullack.flight_api.dto.BookingListRequestDto;
import com.fullack.flight_api.dto.BookingResponseDto;
import com.fullack.flight_api.dto.FlightResponseDto;
import com.fullack.flight_api.entity.Booking;
import com.fullack.flight_api.entity.Flight;
import com.fullack.flight_api.entity.User;
import com.fullack.flight_api.exception.ResourceNotFoundException;
import com.fullack.flight_api.repository.BookingRepository;
import com.fullack.flight_api.repository.UserRepository;
import com.fullack.flight_api.util.SetResponseDto;
import com.fullack.flight_api.util.Util;

@Service
public class BookingService {
	private final BookingRepository bookingRepository;
	private final SetResponseDto setResponseDto;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;

	@Autowired
	public BookingService(BookingRepository bookingRepository, SetResponseDto setResponseDto,
			UserRepository userRepository, JwtUtil jwtUtil) {
		this.bookingRepository = bookingRepository;
		this.setResponseDto = setResponseDto;
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
	}

	public List<BookingResponseDto> getBookingList(String token, BookingListRequestDto bookingListRequestDto) {
		User user = userRepository.findByEmail(jwtUtil.extractEmail(token))
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		
		List<Booking> bookingList = bookingRepository
				.getBookList(Booking.Status.valueOf(bookingListRequestDto.getStatus()), 
						user.getUserId(), bookingListRequestDto.getPage() * 10)
				.orElseThrow(() -> new ResourceNotFoundException("Booking List not found"));

		List<BookingResponseDto> bookingResponseDtoList = new ArrayList<BookingResponseDto>();
		for (Booking booking : bookingList) {
			BookingResponseDto bookingResponseDto = new BookingResponseDto();
			setResponseDto.setBookingResponseDto(bookingResponseDto, booking);

			FlightResponseDto flightResponseDto = new FlightResponseDto();
			setResponseDto.setFlightResponseDto(flightResponseDto, booking);

			bookingResponseDto.setFlightResponseDto(flightResponseDto);

			bookingResponseDtoList.add(bookingResponseDto);
		}
		return bookingResponseDtoList;
	}

	public BookingDetilResponseDto getBookingDetil(long id) {
		Booking booking = bookingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
		BookingDetilResponseDto bookingDetilResponseDto = new BookingDetilResponseDto();
		BookingResponseDto bookingResponseDto = new BookingResponseDto();
		setResponseDto.setBookingResponseDto(bookingResponseDto, booking);

		FlightResponseDto flightResponseDto = new FlightResponseDto();
		setResponseDto.setFlightResponseDto(flightResponseDto, booking);

		bookingResponseDto.setFlightResponseDto(flightResponseDto);

		bookingDetilResponseDto.setBookingResponseDto(bookingResponseDto);
		return bookingDetilResponseDto;
	}

	public boolean addBookingDetil(BookingAddRequestDto bookingAddRequest) {
		User user = new User();
		user.setUserId(bookingAddRequest.getUserId());
		Flight flight = new Flight();
		flight.setFlightId(bookingAddRequest.getFlightId());
		Booking booking = new Booking();
		booking.setReference(bookingAddRequest.getReference());
		booking.setStatus(Booking.Status.UPCOMING);
		booking.setBookingTime(LocalDateTime.now());
		booking.setFlightType(Booking.FlightType.valueOf(bookingAddRequest.getFlightType()));
		booking.setTotalPrice(Util.stringToBigDecimal(bookingAddRequest.getTotalPrice()));
		booking.setUser(user);
		booking.setFlight(flight);

		bookingRepository.save(booking);
		return true;
	}

}
