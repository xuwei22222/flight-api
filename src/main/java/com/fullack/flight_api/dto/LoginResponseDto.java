package com.fullack.flight_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
	private String token;
	private UserInfoDto user;

    public LoginResponseDto(UserInfoDto user, String token) {
        this.token = token;
        this.user = user;
    }
}
