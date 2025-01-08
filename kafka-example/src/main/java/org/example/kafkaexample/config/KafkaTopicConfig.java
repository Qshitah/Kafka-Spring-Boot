package org.example.kafkaexample.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Configuration class to define Kafka topics in a Spring Kafka application.
 * <p>
 * This class contains a bean definition for creating a Kafka topic named "topic1".
 * The topic is created using the {@link TopicBuilder} utility to configure its properties.
 * </p>
 *
 * <p>
 * The {@link KafkaTopicConfig} class provides the necessary configuration for Spring Kafka to
 * manage topics, and it is typically used when setting up Kafka topics in an application.
 * </p>
 *
 * @see NewTopic
 * @see TopicBuilder
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * Creates a new Kafka topic named "topic1".
     *
     * <p>
     * This bean method is used by the Spring context to configure and create the topic
     * when the application starts. The topic is defined with basic properties using the
     * {@link TopicBuilder}.
     * </p>
     *
     * @return a {@link NewTopic} representing the created Kafka topic.
     */
    @Bean
    public NewTopic newTopic(){
        return TopicBuilder.name("topic1").build();
    }
}
