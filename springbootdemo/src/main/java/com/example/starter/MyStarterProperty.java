package com.example.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-08-21 9:41
 */
@ConfigurationProperties("starter.mystarter")
@Data
public class MyStarterProperty {
    private String prefix;
    private String suffix;
}
