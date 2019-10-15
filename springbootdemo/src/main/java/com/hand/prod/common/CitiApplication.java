package com.hand.prod.common;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import com.hand.prod.banner.MyBanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 *
 *
 * @author: bowen.wei@hand-china.com
 * @date: 2019-08-20 16:29
 * @version： 1.0
 */
@SpringBootApplication
@ComponentScan("com.hand.prod")
@MapperScan("com.hand.prod.**.persistence")
//不用每次输入username/password
@EnableAutoConfiguration(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@Slf4j
public class CitiApplication implements CommandLineRunner {

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication();
		springApplication.setBanner(new MyBanner());

		springApplication.run(CitiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try(Connection connection = dataSource.getConnection()) {
			log.info(String.format("connection schema:%s", connection.getSchema()));
		}
	}
}
