package org.olsharabank.rateservice.service;

import lombok.RequiredArgsConstructor;
import org.olsharabank.rateservice.dto.ValueRateDtoList;
import org.olsharabank.rateservice.dto.ValuteValueDto;
import org.olsharabank.rateservice.exception.RateNotFoundException;
import org.olsharabank.rateservice.feign.cache.CacheClient;
import org.olsharabank.rateservice.feign.cbr.CbrClient;
import org.olsharabank.rateservice.dto.ValCursDto;
import org.olsharabank.rateservice.model.Valute;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RateService {

    private final CbrClient cbrClient;
    private final CacheClient cacheClient;

    public Valute getRateResponse(String date, String code) {
        Valute valute = findRateByDateAndCode(date, code);
        if (valute == null) {
            throw new RateNotFoundException("Rate not found for the given date and code");
        }
        return valute;
    }

    private Valute findRateByDateAndCode(String date, String code){
        ValuteValueDto cachedData = cacheClient.getCache(date+code);

        if (cachedData.value() != null) {
            return new Valute(date, code, cachedData.value());
        }

        ValCursDto dtos = cbrClient.getRate(date.replace(".", "/"));

        List<Valute> valutes = dtos.getValutes().stream()
                .map(dto -> new Valute(dtos.getDate(), dto.getCharCode(), dto.getValue()))
                .collect(Collectors.toUnmodifiableList());

        if (!valutes.isEmpty()) {
            cacheClient.setCache(new ValueRateDtoList(valutes));
        }

        return valutes.stream()
                .filter(val -> val.code().equals(code))
                .findFirst()
                .orElse(null);
    }
}
