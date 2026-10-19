package uz.learn.notification.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import uz.learn.notification.dto.NotificationRequest;

@Component
public class NotificationConsumer {

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(
            topics = "notificationTopic",
            groupId = "notificationId"
    )
    public void consume(String payload) {
        try {
            NotificationRequest notificationRequest = objectMapper.readValue(payload, NotificationRequest.class);
            log.info("Consumed notification successfully: {}", notificationRequest);

        } catch (Exception e) {
            log.error("Failed to convert message to NotificationRequest: {}", payload, e);
        }
    }
}