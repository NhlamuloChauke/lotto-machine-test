package com.example.lottomachinetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.lottomachinetest.entity")
@EnableJpaRepositories(basePackages = "com.example.lottomachinetest.repository")
public class LottoMachineTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LottoMachineTestApplication.class, args);
	}

}
