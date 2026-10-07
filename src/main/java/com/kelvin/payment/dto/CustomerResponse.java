package com.kelvin.payment.dto;

import java.time.LocalDateTime;

import com.kelvin.payment.entity.Customer;

public record CustomerResponse(
	 Long id, String firstName, String lastName,
	 String email, String phoneNumber, LocalDateTime createdAt
){
	public static CustomerResponse from(Customer c) {
        return new CustomerResponse(c.getId(), c.getFirstName(), c.getLastName(),
                c.getEmail(), c.getPhoneNumber(), c.getCreatedAt());
    }

}
