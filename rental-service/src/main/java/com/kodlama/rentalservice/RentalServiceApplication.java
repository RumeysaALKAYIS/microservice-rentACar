package com.kodlama.rentalservice;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.kodlama.common.utilities.mapping.ModelMapperManager;
import com.kodlama.common.utilities.mapping.ModelMapperService;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients	
public class RentalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalServiceApplication.class, args);
	}
	
	@Bean 
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	@Bean 
	public ModelMapperService getModelMApperService(ModelMapper modelMapper) {
		return new ModelMapperManager(modelMapper);
		
	}

}
