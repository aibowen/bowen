package com.example.springbootdemo.service;

//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.baomidou.mybatisplus.plugins.Page;
import com.example.exception.MyException;
import com.example.springbootdemo.domain.HcfFundTest;
import com.example.springbootdemo.persistence.HcfFundTestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-06-27
 */
@Service
@Slf4j
public class HcfFundTestService {

//    @Autowired
//    private HcfFundTestMapper hcfFundTestMapper;
//
//    public Page<HcfFundTest> selectByPage(Page page) {
////        return hcfFundTestMapper.selectByPage(page.getCurrent()-1, page.getSize());
//        Integer count = hcfFundTestMapper.selectCount(new EntityWrapper<HcfFundTest>());
//        log.info("总记录数：" + count);
//        List<HcfFundTest> list = hcfFundTestMapper.selectByPage(page);
//        Page page1 = new Page();
//        page1.setRecords(list);
////        page1.setTotal(count);
//        return page1;
//    }
//
//    public Integer insert(HcfFundTest hcfFundTest) {
//        Integer result = hcfFundTestMapper.insert(hcfFundTest);
//        return result;
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    public Integer update(HcfFundTest hcfFundTest) {
//        Integer result = null;
//
//        if ("千与千寻".equals(hcfFundTest.getName())) {
//            log.info("请勿重复更新");
//            throw new MyException("请勿重复更新");
//        } else {
//            result = hcfFundTestMapper.updateById(hcfFundTest);
//        }
//        return result;
//    }
//
//    public Integer batchUpdate(List<HcfFundTest> list) {
//        list.forEach(this::update);
//        return 0;
//    }

}
