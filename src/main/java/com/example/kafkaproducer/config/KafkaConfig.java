package com.example.kafkaproducer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration // Spring의 설정 클래스를 나타냄
@EnableKafka // Kafka 기능을 사용하기 위해 활성화
public class KafkaConfig {

    @Value("${kafka.bootstrap.servers}") // env.properties에서 Kafka 서버의 주소를 주입
    private String bootstrapServers;

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory()); // producerFactory를 사용하여 KafkaTemplate 생성
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> myConfig = new HashMap<>(); // Kafka Producer 설정을 위한 Map 생성
        myConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); // Kafka 서버 설정
        myConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // 키 직렬화 설정
        myConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // 값 직렬화 설정

        return new DefaultKafkaProducerFactory<>(myConfig); // 설정을 기반으로 ProducerFactory 생성
    }
}
