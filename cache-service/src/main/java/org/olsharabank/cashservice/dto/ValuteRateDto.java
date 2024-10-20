package org.olsharabank.cashservice.dto;

public record ValuteRateDto(
        String date,
        String code,
        String nominal
) {
}
