package com.example.kafkaproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    String topicName = "fastcampus"; // 메시지를 전송할 Kafka 토픽 이름

    private KafkaTemplate<String, Object> kafkaTemplate; // KafkaTemplate 인스턴스 변수

    @Autowired
    public Producer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // 메시지를 지정된 토픽에 전송하는 메소드
    public void pub(String msg) {
        kafkaTemplate.send(topicName, msg); // KafkaTemplate을 사용하여 메시지를 전송
    }

}
