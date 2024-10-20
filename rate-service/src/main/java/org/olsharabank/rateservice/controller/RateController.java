package org.olsharabank.rateservice.controller;

import lombok.RequiredArgsConstructor;
import org.olsharabank.rateservice.model.Valute;
import org.olsharabank.rateservice.service.RateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.base.path}")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @GetMapping
    public ResponseEntity<Valute> getRates(
            @RequestParam("date") String date,
            @RequestParam("code") String code
    ) {
        Valute valute = rateService.getRateResponse(date, code);
        return ResponseEntity.ok(valute);
    }
}
