package com.kodlama.rentalservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlama.common.events.RentalUptadetEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalUpdateProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RentalUpdateProducer.class);

	private NewTopic topic;

	private KafkaTemplate<String, RentalUptadetEvent> kafkaTemplate;

	public void sendMessageUpdate(RentalUptadetEvent rentalUptadetEvent) {
		LOGGER.info(String.format("Rental uptadet event => %s", rentalUptadetEvent.toString()));

		Message<RentalUptadetEvent> message = MessageBuilder.withPayload(rentalUptadetEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		kafkaTemplate.send(message);
	}

}
