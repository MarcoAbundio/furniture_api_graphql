package com.api.furniture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FurnitureApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurnitureApplication.class, args);
	}

}
