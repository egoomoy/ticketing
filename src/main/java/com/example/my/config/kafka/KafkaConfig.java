package com.example.my.config.kafka;

import com.google.common.collect.ImmutableMap;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.Map;
import java.util.UUID;

@EnableKafka
@Configuration
public class KafkaConfig {
    @Value("${kafka.url}")
    private String KAFKA_URLS;
    private final int MY_CONCURRENCY = 1;

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        return kafkaTemplate;
    }

    /**
     * 카프카 pub을 위한 bean producerConfigs를 주입한다.
     *
     * @return
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        DefaultKafkaProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(producerConfigs(), null, new StringSerializer());
        return producerFactory;
    }

    /**
     * 카프카 pub 기본 컨피그 설정
     * BOOTSTRAP_SERVERS_CONFIG 서비스IP:PORT
     * ACKS_CONFIG : 0(브로커 적재 여부 확인 안함), 1(리더 파티션에 적재 여부 확인), all(-1, 팔로워에도 적재 됐는지 확인)
     * KEY_SERIALIZER_CLASS_CONFIG 키 데이터 타입
     * VALUE_SERIALIZER_CLASS_CONFIG 값 데이터 타입
     * ENABLE_IDEMPOTENCE_CONFIG 멱등성은 pid/seq로 다른 객체임을 인식 시키는 것을 말한다.
     * 같은 데이터를 수동 처리하기 위해 눌렀을 때 바로 전 큐에 같은 데이터가 있어도 "다른 객체"로 인식시키기 위함
     * TYPE_MAPPINGS => 모듈간 통신에 문제되는 사항 수정
     *
     * @return
     */
    @Bean
    public Map<String, Object> producerConfigs() {
        return ImmutableMap.<String, Object>builder()
                .put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URLS)// kafka server ip & port
                .put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
                .put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class)// Object json parser
                .put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, UUID.randomUUID().toString()) //  Producer가 여러 메시지를 전송할 때, 이를 원자적으로 처리하고자 할 때 사용
                .put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5")
                .put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true) // 멱등성 프로듀서 (pid/seq를 같이 데이터를 전달함) ack 실패 시 중복 배제
                .build();
    }

    @Bean(name = "KafkaListenerContainerFactory") // 컨슘할 때 KafkaListener에서 containerFactory의 이름 명시적으로 입력
    public ConcurrentKafkaListenerContainerFactory<String, String> KafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        return ImmutableMap.<String, Object>builder()
                .put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URLS)
                .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
                .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
                .put(ConsumerConfig.GROUP_ID_CONFIG, "cms-module")
                .put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
                .put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "5000")
                .put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000")
                .put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true)
                .put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed")
                .build();
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), null, new StringDeserializer());
    }
}



