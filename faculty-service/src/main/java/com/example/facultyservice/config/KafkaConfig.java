package com.example.facultyservice.config;

import com.example.events.CourseEvent;
import com.example.events.DepartmentEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.support.serializer.JsonSerializer;import com.example.events.RegisterRequest;
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

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic facultyCourseTopic(){
        return new NewTopic("facultyCourse",1,(short) 1);
    }
    @Bean
    public NewTopic paySlipTopic(){
        return new NewTopic("sendPaySlip",1,(short) 1);
    }
    @Bean
    public NewTopic facultyCreatedTopic(){
        return new NewTopic("sendFacultyCreated",1,(short) 1);
    }
    @Bean
    public NewTopic sendOfficeHoursTopic(){
        return new NewTopic("sendOfficeHours",1,(short) 1);
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
    @Bean
    public ConsumerFactory<String, DepartmentEvent> departmentConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "faculty-department-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        JsonDeserializer<DepartmentEvent> deserializer = new JsonDeserializer<>(DepartmentEvent.class);
        deserializer.addTrustedPackages("com.example.events");
        deserializer.setUseTypeMapperForKey(true);
        deserializer.setUseTypeHeaders(false);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer
        );
    }
    @Bean
    public ConsumerFactory<String, CourseEvent> coursetConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "faculty-course-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        JsonDeserializer<CourseEvent> deserializer = new JsonDeserializer<>(CourseEvent.class);
        deserializer.addTrustedPackages("com.example.events");
        deserializer.setUseTypeMapperForKey(true);
        deserializer.setUseTypeHeaders(false);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer
        );
    }
    @Bean
    public ConsumerFactory<String, RegisterRequest> registerRequestConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "faculty-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        JsonDeserializer<RegisterRequest> deserializer = new JsonDeserializer<>(RegisterRequest.class);
        deserializer.addTrustedPackages("com.example.events");
        deserializer.setUseTypeMapperForKey(true);
        deserializer.setUseTypeHeaders(false);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer
        );
    }

    @Bean(name = "departmentKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String,DepartmentEvent> departmentKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String,DepartmentEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(departmentConsumerFactory());
        return factory;
    }
    @Bean(name = "registerKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String,RegisterRequest> registerKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String,RegisterRequest> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(registerRequestConsumerFactory());
        return factory;
    }
    @Bean(name = "courseKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String,CourseEvent> courseKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String,CourseEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(coursetConsumerFactory());
        return factory;
    }
}

