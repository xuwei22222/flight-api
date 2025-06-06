package com.fullack.flight_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
	private long userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String country;
    private String phone;
}
