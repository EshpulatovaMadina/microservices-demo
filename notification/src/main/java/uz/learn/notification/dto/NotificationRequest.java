package uz.learn.notification.dto;

public record NotificationRequest(
        Integer recipientId,
        String recipientEmail,
        String message
) {}