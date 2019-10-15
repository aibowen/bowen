package com.hand.prod.common.utils;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.http.HttpHeaders;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-06-27 12:00
 */
public final class PageUtil {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";

    private PageUtil() {

    }

    public static Page getPage(int page, int size) {
        return new Page(page + 1, size);
    }

    public static HttpHeaders getTotalHeader(Page<?> page) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("x-total-count", "" + page.getTotal());
        return httpHeaders;
    }
}
