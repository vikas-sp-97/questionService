package com.example.questionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
//@EnableDiscoveryClient
//@EnableSwagger2
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
public class TestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestServiceApplication.class, args);
	}

}
