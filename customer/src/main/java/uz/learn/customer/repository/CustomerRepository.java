package uz.learn.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.learn.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}