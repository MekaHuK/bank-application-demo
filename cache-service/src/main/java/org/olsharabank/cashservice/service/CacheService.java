package org.olsharabank.cashservice.service;

import org.olsharabank.cashservice.dto.ValueRateDtoList;
import org.olsharabank.cashservice.dto.ValuteValueDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class CacheService {

    @Value("${jedis.ttl}")
    private long jedisTtl;

    private final Jedis jedis;

    public CacheService() {
        this.jedis = new Jedis("redis", 6379);
    }

    public ValuteValueDto getCache(String key) {
        return new ValuteValueDto(jedis.get(key));
    }

    public void setCache(ValueRateDtoList valueRateList){
        valueRateList.list().stream()
                .forEach(valuteRate -> {
                    String key = valuteRate.date() + valuteRate.code();
                    jedis.set(key, valuteRate.nominal());
                    jedis.expire(key, jedisTtl);
                });
    }
}
