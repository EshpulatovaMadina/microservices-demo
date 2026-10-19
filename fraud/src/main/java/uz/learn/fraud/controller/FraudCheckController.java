package uz.learn.fraud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.learn.fraud.service.FraudService;

@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudCheckController {

    private static final Logger log = LoggerFactory.getLogger(FraudCheckController.class);
    private final FraudService fraudService;

    public FraudCheckController(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @GetMapping(path = "{customerId}")
    public boolean isFraudster(
            @PathVariable(name = "customerId") Integer customerId
    ) {
        log.info("Fraud check request for customer {}", customerId);
        return fraudService.isFraudulentCustomer(customerId);
    }
}