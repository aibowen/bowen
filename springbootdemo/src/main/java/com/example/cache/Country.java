package com.example.cache;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-08-20 09:54
 */
@Data
@SuppressWarnings("serial")
public class Country implements Serializable {
    private final String code;

    public Country(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Country country = (Country) o;
        return this.code.equals(country.getCode());
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }
}
