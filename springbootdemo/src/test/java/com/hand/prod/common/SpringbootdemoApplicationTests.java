package com.hand.prod.common;

import com.hand.prod.common.persistence.HcfFundTestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {

	@Autowired
	private HcfFundTestMapper hcfFundTestMapper;

	@Test
	public void contextLoads() {
//		List<HcfFundTest> list = hcfFundTestMapper.selectList(null);
//		list.forEach(System.out::println);
	}

}
