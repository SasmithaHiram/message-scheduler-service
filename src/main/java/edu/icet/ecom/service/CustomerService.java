package edu.icet.ecom.service;

import edu.icet.ecom.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final RestTemplate restTemplate;

    @Value("${sms.api.url}")
    private String smsApiUrl;
    @Value("${sms.api.token}")
    private String smsApiToken;
    @Value("${sms.sender_id}")
    private String smsSenderId;

    List<Customer> getSampleCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Gihan", "940753333435"));
        customers.add(new Customer("Sasmitha", "940714839984"));
        return customers;
    }

    public void sendChristmasMessages() {
        String message = "Happy New Year - Sasmitha Scheduled Test Message";
        getSampleCustomers().forEach(customer -> {
            sendMessage(customer.getPhoneNumber(), message+customer.getName());
        });
    }

    @Scheduled(cron = "0 0 9 1 1 ?", zone = "Asia/Colombo")
    public void sendNewYearMessages() {
    }

    void sendMessage(String phoneNumber, String message) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + smsApiToken);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("recipient", phoneNumber);
        body.put("sender_id", smsSenderId);
        body.put("type", "plain");
        body.put("message", message);

                new org.springframework.http.HttpEntity<>(body, httpHeaders);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, httpHeaders);

        restTemplate.postForEntity(smsApiUrl, request, Void.class);
        log.info("Sent SMS to {}: {}", phoneNumber, message);
    }
}


