package com.realtime.notification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime.notification.model.NotificationMessage;
import com.realtime.notification.service.JsonKafkaConsumer;
import com.realtime.notification.service.JsonKafkaProducer;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private JsonKafkaProducer jsonKafkaProducer;

	@Autowired
	private JsonKafkaConsumer jsonKafkaConsumer;

	@CrossOrigin
	@PostMapping("/publish")
	public ResponseEntity<?> publish(@RequestBody NotificationMessage message) {
		jsonKafkaProducer.sendMessage(message);

		return ResponseEntity.ok("Json Message send to the Kafka Topic");
	}

	@CrossOrigin
	@GetMapping("/{userID}")
	public Flux<ServerSentEvent<List<NotificationMessage>>> streamLastMessage(@PathVariable int userID) {
		return jsonKafkaConsumer.streamPosts(userID);
	}
}
