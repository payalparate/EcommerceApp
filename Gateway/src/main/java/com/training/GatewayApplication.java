package com.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author payal.parate
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
