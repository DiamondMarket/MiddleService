package com.diamondmarket.middleService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "com.diamondmarket.middleService", "com.diamondmarket.middleService.api" })
public class MiddleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiddleServiceApplication.class, args);
	}
}
