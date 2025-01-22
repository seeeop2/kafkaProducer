package com.example.kafkaproducer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "fastcampus", groupId = "spring") // "fastcampus" 토픽과 "spring" 그룹 ID에 대한 리스너 설정
    public void consumer (String message) {
        System.out.println(String.format("Subscribed: %s", message)); // 수신한 메시지를 콘솔에 출력
    }
}
