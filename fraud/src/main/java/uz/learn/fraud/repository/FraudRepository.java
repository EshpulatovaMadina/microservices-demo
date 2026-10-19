package uz.learn.fraud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.learn.fraud.entity.Fraud;

public interface FraudRepository extends JpaRepository<Fraud, Integer> {
}