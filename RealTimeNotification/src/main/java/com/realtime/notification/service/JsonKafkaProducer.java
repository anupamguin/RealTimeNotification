package com.realtime.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.realtime.notification.model.NotificationMessage;

@Service
public class JsonKafkaProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, NotificationMessage> kafkaTemplate;

	public void sendMessage(NotificationMessage data) {

		LOGGER.info("Message Send : "+data.toString());

		Message<NotificationMessage> message = MessageBuilder
				.withPayload(data)
				.setHeader(KafkaHeaders.TOPIC, "anupam_notification")
				.build();
		kafkaTemplate.send(message);
	}
}
