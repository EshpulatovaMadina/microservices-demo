package uz.learn.customer.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import uz.learn.customer.dto.NotificationRequest;
import uz.learn.customer.entity.Customer;
import uz.learn.customer.repository.CustomerRepository;
import uz.learn.customer.client.FraudClient;
import uz.learn.customer.dto.CustomerRegistrationRequest;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final FraudClient fraudClient;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CustomerService(CustomerRepository repository, FraudClient fraudClient, KafkaTemplate<String, Object> kafkaTemplate) {
        this.repository = repository;
        this.fraudClient = fraudClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());

        repository.saveAndFlush(customer);

        boolean isFraudster = fraudClient.isFraudster(customer.getId());

        if (isFraudster) {
            throw new RuntimeException("User is fraudster!!!");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hello %s, congrats you are registered now!", customer.getFirstName())
        );

        kafkaTemplate.send("notificationTopic", notificationRequest).whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Xabar muvaffaqiyatli yuborildi: " + result.getRecordMetadata());
            } else {
                System.err.println("Xabar yuborishda xatolik: " + ex.getMessage());
            }
        });
    }
}