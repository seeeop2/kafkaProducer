package com.example.kafkaproducer.service;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreamService {

    private static final Serde<String> STRING_SERDE = Serdes.String(); // 문자열을 위한 Serde 정의

    @Autowired // StreamsBuilder를 자동으로 주입
    public void buildPipeline(StreamsBuilder sb) {
        KStream<String, String> myStream = sb.stream("fastcampus", Consumed.with(STRING_SERDE, STRING_SERDE)); // "fastcampus" 토픽에서 KStream을 생성하고, 문자열 키와 값을 사용
        myStream.print(Printed.toSysOut()); // 수신한 메시지를 콘솔에 출력
        myStream.filter((key, value) -> value.contains("freeClass")).to("freeClassList"); // "freeClass"가 포함된 메시지를 필터링하고, 결과를 "freeClassList" 토픽으로 전송
    }
}
