package com.globant.discounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DiscountController extends SpringBootServletInitializer {

	@Bean
	public RestTemplate rest(RestTemplateBuilder builder) {
		return builder.build();
	}
	  
	public static void main(String[] args) {
		SpringApplication.run(DiscountController.class, args);
	}

	@Override 
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) { 
		return application.sources(DiscountController.class); 
	}
}
