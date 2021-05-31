package com.training;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author payal.parate
 *
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class ECommerceApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
