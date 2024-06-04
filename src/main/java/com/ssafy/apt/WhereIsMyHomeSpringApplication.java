package com.ssafy.apt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ssafy"})
public class WhereIsMyHomeSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereIsMyHomeSpringApplication.class, args);
	}

}
