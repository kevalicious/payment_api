package com.kelvin.payment.dto;

import jakarta.validation.constraints.*;

public record CustomerRequest(
	@NotBlank String firstName,
	@NotBlank String lastName,
	@NotBlank @Email String email,
	@Pattern(regexp = "^\\+?[0-9]{9,15}$", message = "must be a valid phone number") String phoneNumber		
){}
