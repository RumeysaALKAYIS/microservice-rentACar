package com.kodlama.rentalservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;

@FeignClient(value = "stock", url = "http://localhost:9010/stock/api/cars")
public interface CarClient {

	@RequestMapping(method =RequestMethod.GET,value ="/checkIfCar/{id}")
	@Headers(value = "Content-Type: application/json")
	 void checkIfCar(@PathVariable String id);
	
}

