package com.microservice.example.forex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableFeignClients("com.microservice.example.forex")
@EnableDiscoveryClient
public class SpringCurrencyConversionMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCurrencyConversionMicroserviceApplication.class, args);
	}

	@Bean  
	//creating a sampler called always sampler  
	public Sampler defaultSampler()  
	{  
	return Sampler.ALWAYS_SAMPLE;  
	
	}  
}
