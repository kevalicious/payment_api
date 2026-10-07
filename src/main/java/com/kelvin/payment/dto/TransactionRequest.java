package com.kelvin.payment.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

public record TransactionRequest(
	@NotNull Long customerId,
    @NotNull @DecimalMin("0.01") BigDecimal amount,
    @NotBlank @Size(min = 3, max = 3) String currency		
){}
