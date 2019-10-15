package com.hand.prod.common.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hand.prod.common.domain.HcfFundTest;
import com.hand.prod.common.service.HcfFundTestService;
import com.hand.prod.common.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/query")
    public String query(Model model) {
        List<HcfFundTest> testList = hcfFundTestService.selectList(new EntityWrapper<HcfFundTest>());
        model.addAttribute("testList", testList);
        return "hello";
    }

    @GetMapping("/select/page")
    public ResponseEntity<List<HcfFundTest>> selectPage(/*@RequestParam(value = "page")int page,
                                     @RequestParam(value = "size")int size*/) {
        Page page1 = PageUtil.getPage(3, 1);
//        Page page2 = hcfFundTestService.selectByPage(page1);
        Page<HcfFundTest> page2 = hcfFundTestService.selectByPage(page1);
        log.info("总页数：" + page2.getPages());
        HttpHeaders httpHeaders = PageUtil.getTotalHeader(page2);
        return new ResponseEntity(page2.getRecords(), httpHeaders, HttpStatus.OK);
    }

//    @PostMapping("/add")
//    public Integer insert(@RequestBody HcfFundTest hcfFundTest) {
//        return hcfFundTestService.insert(hcfFundTest);
//    }

//    @PutMapping("/update")
//    public Integer update(@RequestBody List<HcfFundTest> hcfFundTests) {
//        return hcfFundTestService.batchUpdate(hcfFundTests);
//    }
}
