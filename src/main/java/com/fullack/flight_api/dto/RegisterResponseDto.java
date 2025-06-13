package com.fullack.flight_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponseDto {
	private UserInfoDto user;
	private String token;

    public RegisterResponseDto(UserInfoDto user, String token) {
        this.user = user;
        this.token = token;
    }
}
