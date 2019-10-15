package com.hand.prod.starter;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-08-21 9:36
 */
public class MyStarter {
    private String prefix;
    private String suffix;

    public MyStarter(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String wrap(String word) {
        return prefix + word + suffix;
    }
}
