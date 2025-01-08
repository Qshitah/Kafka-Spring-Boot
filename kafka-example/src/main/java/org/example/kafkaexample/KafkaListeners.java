package org.example.kafkaexample;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Component responsible for handling Kafka message consumption.
 * <p>
 * This class contains methods annotated with {@link KafkaListener} to consume messages
 * from specified Kafka topics. The messages are processed asynchronously as they are received
 * from the Kafka topic(s). The listeners in this class are triggered when messages are available
 * in the configured Kafka topics.
 * </p>
 *
 * <p>
 * In this case, the listener method consumes messages from the Kafka topic named "topic1"
 * and processes the data.
 * </p>
 */
@Component
public class KafkaListeners {

    /**
     * Kafka listener method that listens to messages from the "topic1" Kafka topic.
     * <p>
     * This method is triggered whenever a new message is produced to the "topic1" topic.
     * The data received from Kafka is passed as a string parameter, which is then printed
     * to the console.
     * </p>
     *
     * @param data the message received from Kafka topic "topic1".
     */
    @KafkaListener(topics = "topic1", groupId = "groupId")
    void listener(String data){
        System.out.println("Listener received " + data);
    }
}
