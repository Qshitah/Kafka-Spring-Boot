package org.example.kafkaexample.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class to set up Kafka consumer settings in a Spring Kafka application.
 * <p>
 * This class defines the necessary configuration for consuming messages from Kafka topics.
 * It configures consumer properties such as bootstrap servers, key/value deserializers,
 * and provides a factory for creating consumer instances. It also exposes a
 * {@link KafkaListenerContainerFactory} to handle Kafka message listeners.
 * </p>
 *
 * <p>
 * The {@link KafkaConsumerConfig} class is used to configure Kafka consumer-related
 * beans that can be injected into other parts of the application to listen to messages
 * from Kafka topics.
 * </p>
 *
 * @see ConsumerConfig
 * @see KafkaListenerContainerFactory
 * @see ConsumerFactory
 */
@Configuration
public class KafkaConsumerConfig {

    /**
     * The Kafka bootstrap servers configuration value.
     * <p>
     * This value is injected from the application's properties file (e.g.,
     * application.properties) and defines the Kafka cluster's address.
     * </p>
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Configures the Kafka consumer properties such as the bootstrap servers and
     * deserializers for the key and value.
     * <p>
     * This method returns a {@link Map} containing the necessary configuration
     * settings required by the Kafka consumer. The bootstrap servers and the key/value
     * deserializers are included in the map.
     * </p>
     *
     * @return a map containing Kafka consumer configurations.
     */
    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    /**
     * Defines the Kafka consumer factory responsible for creating consumer instances.
     * <p>
     * This bean creates a {@link DefaultKafkaConsumerFactory} with the configurations
     * defined in the {@link #consumerConfig()} method, which will be used to instantiate
     * Kafka consumers for consuming messages.
     * </p>
     *
     * @return a {@link ConsumerFactory} for creating Kafka consumer instances.
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    /**
     * Defines the Kafka listener container factory responsible for managing
     * the concurrent consumption of messages from Kafka topics.
     * <p>
     * This bean creates a {@link ConcurrentKafkaListenerContainerFactory} with the consumer
     * factory set. It provides a factory for concurrent Kafka message listeners that will
     * consume messages from Kafka topics asynchronously.
     * </p>
     *
     * @param consumerFactory the consumer factory used to create Kafka consumer instances.
     * @return a {@link KafkaListenerContainerFactory} instance for handling Kafka listeners.
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> factory(
            ConsumerFactory<String, String> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
