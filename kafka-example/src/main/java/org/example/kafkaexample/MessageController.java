package org.example.kafkaexample;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling HTTP requests related to message publishing to Kafka.
 * <p>
 * This class exposes a RESTful API endpoint for publishing messages to a Kafka topic.
 * It accepts a message from the HTTP request body and sends it to a Kafka topic using
 * the {@link KafkaTemplate}. The message is sent asynchronously to the configured
 * Kafka topic (in this case, "topic1").
 * </p>
 */
@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Constructs a {@link MessageController} with the given KafkaTemplate.
     *
     * @param kafkaTemplate the KafkaTemplate used for sending messages to Kafka.
     */
    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * API endpoint for publishing a message to a Kafka topic.
     * <p>
     * This method listens to POST requests at "/api/v1/messages". It extracts the message
     * from the request body and sends it to the Kafka topic "topic1".
     * </p>
     *
     * @param messageRequest the request body containing the message to be sent to Kafka.
     */
    @PostMapping
    public void publish(@RequestBody MessageRequest messageRequest) {
        kafkaTemplate.send("topic1", messageRequest.message());
    }
}
