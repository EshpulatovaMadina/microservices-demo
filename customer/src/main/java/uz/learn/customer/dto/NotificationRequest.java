package uz.learn.customer.dto;

public record NotificationRequest(
        Integer recipientId,
        String recipientEmail,
        String message
) {}