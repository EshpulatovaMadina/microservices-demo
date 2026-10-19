package uz.learn.customer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "fraud-service")
public interface FraudClient {

    @GetMapping(path = "api/v1/fraud-check/{customerId}")
    boolean isFraudster(@PathVariable("customerId") Integer customerId1);
}
