package org.example.kafkaexample.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class to set up Kafka producer settings in a Spring Kafka application.
 * <p>
 * This class defines the necessary configuration for producing messages to Kafka topics
 * using Spring Kafka. It configures producer properties such as bootstrap servers,
 * key/value serializers, and provides a factory for creating producer instances. It
 * also exposes a {@link KafkaTemplate} to send messages to Kafka.
 * </p>
 *
 * <p>
 * The {@link KafkaProducerConfig} class is used to configure Kafka producer-related
 * beans that can be injected into other parts of the application to send messages to
 * Kafka topics.
 * </p>
 *
 * @see ProducerConfig
 * @see KafkaTemplate
 * @see ProducerFactory
 */
@Configuration
public class KafkaProducerConfig {

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
     * Configures the Kafka producer properties such as the bootstrap servers and
     * serializers for the key and value.
     * <p>
     * This method returns a {@link Map} containing the necessary configuration
     * settings required by the Kafka producer. The bootstrap servers and the key/value
     * serializers are included in the map.
     * </p>
     *
     * @return a map containing Kafka producer configurations.
     */
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    /**
     * Defines the Kafka producer factory responsible for creating producer instances.
     * <p>
     * This bean creates a {@link DefaultKafkaProducerFactory} with the configurations
     * defined in the {@link #producerConfig()} method, which will be used to instantiate
     * Kafka producers for sending messages.
     * </p>
     *
     * @return a {@link ProducerFactory} for creating Kafka producer instances.
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    /**
     * Defines a {@link KafkaTemplate} used to send messages to Kafka topics.
     * <p>
     * This bean provides a higher-level abstraction over the Kafka producer. It allows
     * for easy sending of messages to Kafka topics through simple method calls. It uses
     * the {@link ProducerFactory} to create the Kafka producer.
     * </p>
     *
     * @param producerFactory the producer factory that creates Kafka producers.
     * @return a {@link KafkaTemplate} instance for sending messages.
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(
            ProducerFactory<String, String> producerFactory
    ) {
        return new KafkaTemplate<>(producerFactory);
    }
}
