package org.olsharabank.rateservice.feign.cache;

import org.olsharabank.rateservice.dto.ValueRateDtoList;
import org.olsharabank.rateservice.dto.ValuteValueDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cache-client", url = "${cache.url}", configuration = CacheFeignConfig.class)
public interface CacheClient {

    @GetMapping("/getCache")
    ValuteValueDto getCache(@RequestParam("identifier") String identifier);

    @PostMapping(value = "/setCache", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity setCache(@RequestBody ValueRateDtoList valuteRateList);
}
