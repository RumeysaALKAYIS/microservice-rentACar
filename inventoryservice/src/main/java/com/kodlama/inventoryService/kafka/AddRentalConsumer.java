package com.kodlama.inventoryService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlama.common.events.RentalCreatedEvent;
import com.kodlama.inventoryService.business.abstracts.CarService;
import com.kodlama.inventoryService.business.requeses.updates.UpdateStateCarRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddRentalConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddRentalConsumer.class);
	private CarService carService;

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "add")
	//${spring.kafka.consumer.group-id}
	public void consume(RentalCreatedEvent event) {
		LOGGER.info(String.format("Rental Create event received in stock service  => %s", event.toString()));

		UpdateStateCarRequest carRequest = new UpdateStateCarRequest();
		carRequest.setId(event.getCarId());
		carRequest.setState(1);
		this.carService.updateStateCar(carRequest);

	}

}