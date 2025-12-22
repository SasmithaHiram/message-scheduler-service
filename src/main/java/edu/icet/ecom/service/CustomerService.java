package edu.icet.ecom.service;

import edu.icet.ecom.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
        customers.add(new Customer("Sas", "94714839984"));
        return customers;
    }

    @Scheduled(cron = "0 20 17 22 12 ?", zone = "Asia/Colombo")
    public void sendChristmasMessages() {
        String message = "Merry Christmas";
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
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("recipient", phoneNumber);
        body.add("sender_id", smsSenderId);
        body.add("type", "plain");
        body.add("message", message);

        org.springframework.http.HttpEntity<LinkedMultiValueMap<String, String>> request =
                new org.springframework.http.HttpEntity<>(body, httpHeaders);

        restTemplate.postForEntity(smsApiUrl, request, Void.class);
    }
}


