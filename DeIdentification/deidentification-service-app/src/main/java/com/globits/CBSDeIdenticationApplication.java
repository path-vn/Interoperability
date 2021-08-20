package com.globits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan
@EnableScheduling
@EnableAutoConfiguration(exclude = {RestClientAutoConfiguration.class}) 
public class CBSDeIdenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CBSDeIdenticationApplication.class, args);
	}

}
