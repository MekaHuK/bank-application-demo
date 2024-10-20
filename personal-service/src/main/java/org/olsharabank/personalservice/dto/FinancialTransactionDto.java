package org.olsharabank.personalservice.dto;

import java.util.UUID;

public record FinancialTransactionDto(
        UUID userId,
        Long value
) {
}
