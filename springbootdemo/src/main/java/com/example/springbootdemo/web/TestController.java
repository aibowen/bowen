package com.example.springbootdemo.web;

//import com.baomidou.mybatisplus.plugins.Page;
import com.example.springbootdemo.domain.HcfFundTest;
import com.example.springbootdemo.service.HcfFundTestService;
import com.example.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-06-27
 */
@RestController
@RequestMapping("/api/test")
@Slf4j
//@Transactional(rollbackFor = Exception.class)
public class TestController {

    @Autowired
    private HcfFundTestService hcfFundTestService;

    @RequestMapping("/hello")
    String home() {
        return "hello springboot";
    }

    @RequestMapping("/aop")
    String testAop() {
        return "test aop";
    }

//    @GetMapping("/select/page")
//    public ResponseEntity selectPage(@RequestParam(value = "page")int page,
//                                     @RequestParam(value = "size")int size) {
//        Page page1 = PageUtil.getPage(page, size);
////        Page page2 = hcfFundTestService.selectByPage(page1);
//        Page<HcfFundTest> page2 = hcfFundTestService.selectByPage(page1);
//        log.info("总页数：" + page2.getPages());
//        HttpHeaders httpHeaders = PageUtil.getTotalHeader(page2);
//        return new ResponseEntity(page2.getRecords(), httpHeaders, HttpStatus.OK);
//    }

//    @PostMapping("/add")
//    public Integer insert(@RequestBody HcfFundTest hcfFundTest) {
//        return hcfFundTestService.insert(hcfFundTest);
//    }

//    @PutMapping("/update")
//    public Integer update(@RequestBody List<HcfFundTest> hcfFundTests) {
//        return hcfFundTestService.batchUpdate(hcfFundTests);
//    }
}
