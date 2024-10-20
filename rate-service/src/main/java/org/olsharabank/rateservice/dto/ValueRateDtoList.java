package org.olsharabank.rateservice.dto;

import org.olsharabank.rateservice.model.Valute;

import java.util.List;

public record ValueRateDtoList(
        List<Valute> list
) {
}
