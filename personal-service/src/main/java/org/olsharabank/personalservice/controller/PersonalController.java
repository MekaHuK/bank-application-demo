package org.olsharabank.personalservice.controller;

import lombok.RequiredArgsConstructor;
import org.olsharabank.personalservice.dto.FinancialTransactionDto;
import org.olsharabank.personalservice.service.PersonalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.base.path}")
@RequiredArgsConstructor
public class PersonalController {

    private final PersonalService personalService;

    @PutMapping("/putMoney")
    public ResponseEntity<FinancialTransactionDto> putMoney(@RequestBody FinancialTransactionDto ftd){
        Long newAmount = personalService.putMoney(ftd.userId(), ftd.value());
        return ResponseEntity.ok(new FinancialTransactionDto(ftd.userId(), newAmount));
    }

    @PutMapping("/withdrawMoney")
    public ResponseEntity<FinancialTransactionDto> withdrawMoney(@RequestBody FinancialTransactionDto ftd) {
        Long newBalance = personalService.withdrawMoney(ftd.userId(), ftd.value());
        return ResponseEntity.ok(new FinancialTransactionDto(ftd.userId(), newBalance));
    }

    @PutMapping("/transferMoney")
    public ResponseEntity<FinancialTransactionDto> transferMoney(@RequestParam String phoneNumber, @RequestBody FinancialTransactionDto ftd) {
        Long newBalance = personalService.transferMoney(ftd.userId(), phoneNumber, ftd.value());
        return ResponseEntity.ok(new FinancialTransactionDto(ftd.userId(), newBalance));
    }

}
