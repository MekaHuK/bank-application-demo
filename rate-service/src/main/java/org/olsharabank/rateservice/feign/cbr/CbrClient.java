package org.olsharabank.rateservice.feign.cbr;

import org.olsharabank.rateservice.dto.ValCursDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "rate-client", url = "${cbr.url}", configuration = CbrFeignConfig.class)
public interface CbrClient {

    @GetMapping("${cbr.path}")
    ValCursDto getRate(@RequestParam("date_req") String date);
}
