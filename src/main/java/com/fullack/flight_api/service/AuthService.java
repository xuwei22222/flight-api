package com.fullack.flight_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fullack.flight_api.config.JwtUtil;
import com.fullack.flight_api.dto.LoginRequestDto;
import com.fullack.flight_api.dto.LoginResponseDto;
import com.fullack.flight_api.dto.RegisterRequestDto;
import com.fullack.flight_api.dto.RegisterResponseDto;
import com.fullack.flight_api.dto.UserInfoDto;
import com.fullack.flight_api.entity.User;
import com.fullack.flight_api.exception.InvalidCredentialsException;
import com.fullack.flight_api.exception.ResourceNotFoundException;
import com.fullack.flight_api.repository.UserRepository;
import com.fullack.flight_api.util.SetResponseDto;

@Service
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final SetResponseDto setResponseDto;
	private final JwtUtil jwtUtil;

	@Autowired
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, SetResponseDto setResponseDto,
			JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.setResponseDto = setResponseDto;
		this.jwtUtil = jwtUtil;
	}

	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		User user = userRepository.findByEmail(loginRequestDto.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return new LoginResponseDto(setUserInfoDto(user), 
				setToken(loginRequestDto.getPassword(), user));
	}

	public boolean register(RegisterRequestDto registerRequestDto) {
		User user = new User();
		user.setEmail(registerRequestDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
		user.setFirstName(registerRequestDto.getFirstName());
		user.setLastName(registerRequestDto.getLastName());
		user.setCountry(registerRequestDto.getCountry());
		user.setPhone(registerRequestDto.getPhone());

		userRepository.save(user);
		return true;
	}

	public RegisterResponseDto getRegisterResposeDto(RegisterRequestDto registerRequestDto) {
		User user = userRepository.findByEmail(registerRequestDto.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return new RegisterResponseDto(setUserInfoDto(user), 
				setToken(registerRequestDto.getPassword(), user));
	}

	public boolean validateToken(String token) {
		final String email = jwtUtil.extractEmail(token);
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		return email.equals(user.getEmail()) && !jwtUtil.isTokenExpired(token);
	}

	private UserInfoDto setUserInfoDto(User user) {
		UserInfoDto userInfoDto = new UserInfoDto();
		setResponseDto.setUserInfoDto(userInfoDto, user);
		return userInfoDto;
	}

	private String setToken(String loginPassword, User user) {
		if (!passwordEncoder.matches(loginPassword, user.getPassword())) {
			throw new InvalidCredentialsException("Invalid credentials");
		}
		String token = jwtUtil.generateToken(user.getEmail());
		return token;
	}
}
