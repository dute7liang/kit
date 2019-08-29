package com.scxx.web;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: zl
 * @Date: 2019-8-28 09:31
 */
@SpringBootApplication(scanBasePackages = {"com.scxx"})
@MapperScan("com.scxx.*.repository.dao")
@EnableTransactionManagement
@Slf4j
public class ScxxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScxxApplication.class, args);

	}
}
