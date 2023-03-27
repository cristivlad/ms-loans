package com.example.msloans.controller;

import com.example.msloans.config.LoansServiceConfig;
import com.example.msloans.model.Customer;
import com.example.msloans.model.Loans;
import com.example.msloans.model.Properties;
import com.example.msloans.repository.LoansRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoansController {

    private static final Logger logger = LoggerFactory.getLogger(LoansController.class);
    private final LoansRepository loansRepository;
    private final LoansServiceConfig loansConfig;

    public LoansController(LoansRepository loansRepository, LoansServiceConfig loansConfig) {
        this.loansRepository = loansRepository;
        this.loansConfig = loansConfig;
    }

    @PostMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestHeader("tmx-correlation-id") String correlationId, @RequestBody Customer customer) {
        logger.info("getLoansDetails started");
        List<Loans> byCustomerIdOrderByCreateDt = loansRepository.findByCustomerIdOrderByCreateDt(customer.getCustomerId());
        logger.info("getLoansDetails ended");
        return byCustomerIdOrderByCreateDt;
    }

    @GetMapping("/loans/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(loansConfig.getMsg(), loansConfig.getBuildVersion(),
                loansConfig.getMailDetails(), loansConfig.getActiveBranches());
        return ow.writeValueAsString(properties);
    }
}
