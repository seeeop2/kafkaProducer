package com.example.kafkaproducer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration // Spring의 설정 클래스를 나타냄
@EnableKafkaStreams // Kafka Streams 기능을 사용하기 위해 활성화
@EnableKafka // Kafka 기능을 사용하기 위해 활성화
public class KafkaConfig {

    @Value("${kafka.bootstrap.servers}") // env.properties에서 Kafka 서버의 주소를 주입
    private String bootstrapServers;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME) // Kafka Streams 설정을 Spring 컨테이너에 등록
    public KafkaStreamsConfiguration myKStreamConfig() {
        Map<String, Object> myKStreamConfig = new HashMap<>(); // Kafka Streams 설정을 위한 Map 생성
        myKStreamConfig.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream-test"); // 애플리케이션 ID 설정
        myKStreamConfig.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); // Kafka 서버 설정
        myKStreamConfig.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName()); // 기본 키 Serde 설정
        myKStreamConfig.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName()); // 기본 값 Serde 설정
        myKStreamConfig.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 3); // 스트림 처리에 사용할 스레드 수 설정
        return new KafkaStreamsConfiguration(myKStreamConfig); // 설정을 기반으로 KafkaStreamsConfiguration 객체 반환
    }

//    @Bean
//    public KafkaTemplate<String, Object> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory()); // producerFactory를 사용하여 KafkaTemplate 생성
//    }
//
//    @Bean
//    public ProducerFactory<String, Object> producerFactory() {
//        Map<String, Object> myConfig = new HashMap<>(); // Kafka Producer 설정을 위한 Map 생성
//        myConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); // Kafka 서버 설정
//        myConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // 키 직렬화 설정
//        myConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // 값 직렬화 설정
//
//        return new DefaultKafkaProducerFactory<>(myConfig); // 설정을 기반으로 ProducerFactory 생성
//    }
//
//    @Bean
//    public ConsumerFactory<String, Object> consumerFactory() {
//        Map<String, Object> myConfig = new HashMap<>(); // Kafka Producer 설정을 위한 Map 생성
//        myConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); // Kafka 서버 설정
//        myConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); // 키 역직렬화 설정
//        myConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); // 값 역직렬화 설정
//
//        return new DefaultKafkaConsumerFactory<>(myConfig); // 설정을 기반으로 ConsumerFactory 생성
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Object> myFactory = new ConcurrentKafkaListenerContainerFactory<>(); // 리스너 컨테이너 팩토리 생성
//        myFactory.setConsumerFactory(consumerFactory()); // 생성한 ConsumerFactory를 설정
//        return myFactory; // 리스너 컨테이너 팩토리 반환
//    }
}
