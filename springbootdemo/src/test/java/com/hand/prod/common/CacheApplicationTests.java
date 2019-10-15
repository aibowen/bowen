package com.hand.prod.common;

import com.hand.prod.cache.Country;
import com.hand.prod.cache.CountryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-08-20 09:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void validCache() {
        Cache countries = this.cacheManager.getCache("countries");
        assertThat(countries).isNotNull();
        countries.clear();
        assertThat(countries.get("BE")).isNotNull();
        Country be = this.countryRepository.findByCode("BE");
        assertThat((Country) countries.get("BE").get()).isEqualTo(be);
    }
}
