package com.fullack.flight_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullack.flight_api.dto.BookingAddRequestDto;
import com.fullack.flight_api.dto.BookingDetilResponseDto;
import com.fullack.flight_api.dto.BookingListRequestDto;
import com.fullack.flight_api.dto.BookingListResponseDto;
import com.fullack.flight_api.dto.BookingResponseDto;
import com.fullack.flight_api.http.ResponseMessage;
import com.fullack.flight_api.service.BookingService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class BookingController {
	private final BookingService bookingService;

	@Autowired
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@GetMapping("/bookings")
	public ResponseEntity<ResponseMessage<BookingListResponseDto>> bookingtList(HttpServletRequest request,
			BookingListRequestDto bookingListRequestDto) {
		String authorizationHeader = request.getHeader("Authorization");
		String token = authorizationHeader.substring(7);
		List<BookingResponseDto> bookingResponseDtoList = bookingService.getBookingList(token, bookingListRequestDto);
		BookingListResponseDto bookingListResponseDto = new BookingListResponseDto();
		bookingListResponseDto.setBookingResponseDto(bookingResponseDtoList);
		return ResponseEntity.ok(ResponseMessage.success(bookingListResponseDto));
	}

	@GetMapping("/bookings/{id}")
	public ResponseEntity<ResponseMessage<BookingDetilResponseDto>> bookingDetil(@PathVariable("id") long id) {
		BookingDetilResponseDto bookingResponseDto = bookingService.getBookingDetil(id);
		return ResponseEntity.ok(ResponseMessage.success(bookingResponseDto));
	}

	@PostMapping("/bookings")
	public ResponseEntity<ResponseMessage<String>> bookingtList(
			@RequestBody BookingAddRequestDto bookingAddRequestDto) {
		boolean result = bookingService.addBookingDetil(bookingAddRequestDto);

		if (result) {
			ResponseMessage<String> message = ResponseMessage.success("Booking fligth success");
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage.error(400, "Booking fligth failed"));
	}
}
