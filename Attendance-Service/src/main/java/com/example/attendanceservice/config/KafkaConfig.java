package com.example.attendanceservice.config;

import com.example.events.FacultyCreatedEvent;
import com.example.events.LeaveEvent;
import com.example.events.LowAttendanceAlert;
import com.example.events.RegisterRequest;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic lowAttendanceTopic(){
        return new NewTopic("lowAttendance",1,(short) 1);
    }
    @Bean
    public NewTopic leaveTopic(){
        return new NewTopic("leave",1,(short) 1);
    }
    @Bean
    public NewTopic attendanceTopic(){
        return new NewTopic("sendAttendance",1,(short) 1);
    }
    @Bean
    public ProducerFactory<String,Object> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);

    }

    @Bean
    public KafkaTemplate<String,Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, FacultyCreatedEvent> consumerFactory() {

        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "attendance-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        JsonDeserializer<FacultyCreatedEvent> deserializer = new JsonDeserializer<>(FacultyCreatedEvent.class, false);

        deserializer.addTrustedPackages("com.example.events");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer
        );
    }

    @Bean(name = "kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, FacultyCreatedEvent> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, FacultyCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
