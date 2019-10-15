package com.hand.prod.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-08-20 10:04
 */
@Component
@CacheConfig(cacheNames = "countries")
@Slf4j
public class CountryRepository {
    @Cacheable
    public Country findByCode(String code) {
        log.info("loading country with code " + code);
        return new Country(code);
    }
}
