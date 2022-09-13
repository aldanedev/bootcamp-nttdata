package com.nttdata.accountbanksservice.accounts.infrastructure.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class TopicProducer {

  @Value("${topic.name}")
  private String topicName;

  private final KafkaTemplate<String, String> kafkaTemplate;

  public void send(String message) {
    log.info("sending message='{}' to topic='{}'", message, topicName);
    kafkaTemplate.send(topicName, message);
  }
}
