package com.fullack.flight_api.util;

import org.springframework.stereotype.Component;

import com.fullack.flight_api.dto.BookingResponseDto;
import com.fullack.flight_api.dto.FlightResponseDto;
import com.fullack.flight_api.dto.UserInfoDto;
import com.fullack.flight_api.entity.Booking;
import com.fullack.flight_api.entity.Flight;
import com.fullack.flight_api.entity.User;

@Component
public class SetResponseDto {
	public void setFlightResponseDto(FlightResponseDto flightResponseDto, Flight flight) {
		flightResponseDto.setId(flight.getFlightId());
		flightResponseDto.setFlightNumber(flight.getFlightNumber());
		flightResponseDto.setDeparture(flight.getDeparture().getCity());
		flightResponseDto.setDestination(flight.getDestination().getCity());
		flightResponseDto.setDepartureDate(Util.dateToString(flight.getDepartureDate()));
		flightResponseDto.setDepartureTime(Util.timeToString(flight.getDepartureTime()));
		flightResponseDto.setDestinationDate(Util.dateToString(flight.getDestinationDate()));
		flightResponseDto.setDestinationTime(Util.timeToString(flight.getDestinationTime()));
		flightResponseDto.setTaxes(flight.getTaxes().toString());
		flightResponseDto.setPrice(flight.getPrice().toString());
	}

	public void setFlightResponseDto(FlightResponseDto flightResponseDto, Booking booking) {
		flightResponseDto.setId(booking.getFlight().getFlightId());
		flightResponseDto.setFlightNumber(booking.getFlight().getFlightNumber());
		flightResponseDto.setDeparture(booking.getFlight().getDeparture().getCity());
		flightResponseDto.setDestination(booking.getFlight().getDestination().getCity());
		flightResponseDto.setDepartureDate(Util.dateToString(booking.getFlight().getDepartureDate()));
		flightResponseDto.setDepartureTime(Util.timeToString(booking.getFlight().getDepartureTime()));
		flightResponseDto.setDestinationDate(Util.dateToString(booking.getFlight().getDestinationDate()));
		flightResponseDto.setDestinationTime(Util.timeToString(booking.getFlight().getDestinationTime()));
		flightResponseDto.setTaxes(booking.getFlight().getTaxes().toString());
		flightResponseDto.setPrice(booking.getFlight().getPrice().toString());
	}

	public void setBookingResponseDto(BookingResponseDto bookingResponseDto, Booking booking) {
		bookingResponseDto.setBookingId(booking.getBookingId());
		bookingResponseDto.setReference(booking.getReference());
		bookingResponseDto.setBookingTime(Util.dateTimeToString(booking.getBookingTime()));
		bookingResponseDto.setStatus(booking.getStatus().toString());
		bookingResponseDto.setFlightType(booking.getFlightType().toString());
		bookingResponseDto.setTotalPrice(booking.getTotalPrice().toString());
	}
	
	public void setUserInfoDto(UserInfoDto userInfoDto, User user) {
		userInfoDto.setUserId(user.getUserId());
		userInfoDto.setEmail(user.getEmail());
		userInfoDto.setFirstName(user.getFirstName());
		userInfoDto.setLastName(user.getLastName());
		userInfoDto.setCountry(user.getCountry());
		userInfoDto.setPhone(user.getPhone());
	}
}
