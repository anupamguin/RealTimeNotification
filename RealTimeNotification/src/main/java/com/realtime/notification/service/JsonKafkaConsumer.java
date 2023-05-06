package com.realtime.notification.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.realtime.notification.model.NotificationMessage;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class JsonKafkaConsumer {

	
	private static final Logger logger = LoggerFactory.getLogger(JsonKafkaConsumer.class.getName());

	private final Sinks.Many<List<NotificationMessage>> postSink = Sinks.many().multicast().onBackpressureBuffer();

	@KafkaListener(topics = "anupam_notification", groupId = "anupamGroup")
	public void consume(NotificationMessage msg) {
		logger.info("Json Data Recived : " + msg.toString());
		postSink.tryEmitNext(Arrays.asList(msg));
	}

	public Flux<ServerSentEvent<List<NotificationMessage>>> streamPosts(int userId) {
	    return postSink.asFlux()
	            .map(posts -> posts.stream()
	                    .filter(post -> post.getUserId() == userId)
	                    .collect(Collectors.toList()))
	            
	            .filter(filteredPosts -> !filteredPosts.isEmpty())
	            
	            .map(filteredPosts -> ServerSentEvent.<List<NotificationMessage>>builder()
	                    .event("post-list-event")
	                    .data(filteredPosts)
	                    .build());
	}



/*
	private static final Logger logger = LoggerFactory.getLogger(JsonKafkaConsumer.class.getName());

    private final ConcurrentMap<Integer, Sinks.Many<List<NotificationMessage>>> userSinks = new ConcurrentHashMap<>();

    @KafkaListener(topics = "anupam_notification", groupId = "anupamGroup")
    public void consume(NotificationMessage msg) {
        logger.info("Json Data Recived : " + msg.toString());
        Sinks.Many<List<NotificationMessage>> userSink = userSinks.computeIfAbsent(msg.getUserId(), k -> Sinks.many().multicast().onBackpressureBuffer());
        userSink.tryEmitNext(Arrays.asList(msg));
    }

    public Flux<ServerSentEvent<List<NotificationMessage>>> streamPosts(Integer userId) {
        Sinks.Many<List<NotificationMessage>> userSink = userSinks.computeIfAbsent(userId, k -> Sinks.many().multicast().onBackpressureBuffer());
        return userSink.asFlux()
                .map(notificationMessages -> ServerSentEvent.<List<NotificationMessage>>builder()
                        .event("post-list-event")
                        .data(notificationMessages)
                        .build());
    }
    
  */
}
