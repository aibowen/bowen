package com.example.springbootdemo;

//import org.mybatis.spring.annotation.MapperScan;
import com.example.banner.MyBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author: bowen.wei@hand-china.com
 * @date: 2019-08-20 16:29
 * @version： 1.0
 */
@RestController
@SpringBootApplication
//@MapperScan("com.example.springbootdemo.persistence")
//不用每次输入username/password
//@EnableAutoConfiguration(exclude = {
//		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//})
public class SpringbootdemoApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication();
		springApplication.setBanner(new MyBanner());

		springApplication.run(SpringbootdemoApplication.class, args);
	}

}
