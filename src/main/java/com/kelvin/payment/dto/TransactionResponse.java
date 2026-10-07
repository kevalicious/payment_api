package com.kelvin.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.kelvin.payment.entity.Transaction;
import com.kelvin.payment.entity.TransactionStatus;

public record TransactionResponse(
	 Long id, Long customerId, BigDecimal amount, String currency,
	 String reference, TransactionStatus status, LocalDateTime createdAt	
) {
	public static TransactionResponse from(Transaction t) {
        return new TransactionResponse(t.getId(), t.getCustomer().getId(), t.getAmount(),
                t.getCurrency(), t.getReference(), t.getStatus(), t.getCreatedAt());
    }

}
