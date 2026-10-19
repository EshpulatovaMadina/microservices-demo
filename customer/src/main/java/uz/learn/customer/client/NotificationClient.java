package uz.learn.customer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.learn.customer.dto.NotificationRequest;

@FeignClient(name = "notification-service")
public interface NotificationClient {

    @PostMapping(path = "api/v1/notifications")
    void sendNotification(@RequestBody NotificationRequest notificationRequest);
}