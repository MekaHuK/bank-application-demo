package org.olsharabank.cashservice.controller;

import org.olsharabank.cashservice.dto.ValueRateDtoList;
import org.olsharabank.cashservice.dto.ValuteValueDto;
import org.olsharabank.cashservice.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.base.path}")
public class CacheController {

    private final CacheService cacheService;

    @Autowired
    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping("/getCache")
    public ValuteValueDto getCache(@RequestParam("identifier") String identifier) {
        return cacheService.getCache(identifier);
    }

    @PostMapping(value = "/setCache", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity setCache(@RequestBody ValueRateDtoList valuteRateList) {
        cacheService.setCache(valuteRateList);
        return ResponseEntity.ok().build();
    }
}
