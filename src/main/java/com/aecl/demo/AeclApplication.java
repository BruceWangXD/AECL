package com.aecl.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement  // <tx:annotation-driven />
@MapperScan("com.aecl.demo.dao")
public class AeclApplication {

	public static void main(String[] args) {
		SpringApplication.run(AeclApplication.class, args);
	}

}
