package uz.learn.fraud.service;

import org.springframework.stereotype.Service;
import uz.learn.fraud.entity.Fraud;
import uz.learn.fraud.repository.FraudRepository;

import java.time.LocalDateTime;

@Service
public class FraudService {

    private final FraudRepository repository;

    public FraudService(FraudRepository repository) {
        this.repository = repository;
    }

    public boolean isFraudulentCustomer(Integer customerId) {
        repository.save(
                Fraud.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}