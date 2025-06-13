package com.fullack.flight_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullack.flight_api.dto.CheckResponseDto;
import com.fullack.flight_api.dto.LoginRequestDto;
import com.fullack.flight_api.dto.LoginResponseDto;
import com.fullack.flight_api.dto.RegisterRequestDto;
import com.fullack.flight_api.dto.RegisterResponseDto;
import com.fullack.flight_api.http.ResponseMessage;
import com.fullack.flight_api.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/register")
	public ResponseEntity<ResponseMessage<RegisterResponseDto>> register(
			@RequestBody RegisterRequestDto registerRequestDto) {
		boolean result = authService.register(registerRequestDto);

		if (result) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseMessage.success(authService.getRegisterResposeDto(registerRequestDto)));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage.error(400, "Register failed"));
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseMessage<LoginResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto) {
		LoginResponseDto loginResponseDto = authService.login(loginRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success(loginResponseDto));
	}

	@GetMapping("/check")
	public ResponseEntity<ResponseMessage<CheckResponseDto>> check(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		String token = authorizationHeader.substring(7);
		CheckResponseDto checkResponseDto = new CheckResponseDto();
		checkResponseDto.setLoggedIn(authService.validateToken(token));
		return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success(checkResponseDto));
	}
}
