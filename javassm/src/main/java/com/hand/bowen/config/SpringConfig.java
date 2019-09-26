package com.hand.bowen.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-09-26 10:01
 */
@Configuration
@ComponentScan(basePackages = "com.hand.bowen", useDefaultFilters = true, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)})
public class SpringConfig {
}
