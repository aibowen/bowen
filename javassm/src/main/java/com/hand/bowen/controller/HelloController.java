package com.hand.bowen.controller;

import com.hand.bowen.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-09-25
 */
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        return helloService.hello();
    }

    @GetMapping("/list")
    public List<String> list() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i <10 ; i++) {
            list.add("num:" + i);
        }
        return list;
    }
}
