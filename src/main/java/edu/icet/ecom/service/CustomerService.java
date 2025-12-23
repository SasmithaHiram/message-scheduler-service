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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private String message;

    private final RestTemplate restTemplate;

    @Value("${sms.api.url}")
    private String smsApiUrl;
    @Value("${sms.api.token}")
    private String smsApiToken;
    @Value("${sms.sender_id}")
    private String smsSenderId;

    @Value("${sms.auth.header:Authorization}")
    private String smsAuthHeader;
    @Value("${sms.auth.prefix:}")
    private String smsAuthPrefix;
    @Value("${sms.api.token.location:header}")
    private String smsApiTokenLocation;
    @Value("${sms.api.token.key:token}")
    private String smsApiTokenKey;

    List<Customer> getSampleCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Gihan", "940753333435"));
        customers.add(new Customer("Sasmitha", "940714839984"));
        customers.add(new Customer("Udayanga", "940774409158"));
        customers.add(new Customer("Vishmitha", "940753521146"));
        customers.add(new Customer("Mallika", "940779998575"));
        return customers;
    }

    @Scheduled(cron = "0 1 0 25 12 *", zone = "Asia/Colombo")
    public void sendChristmasMessages() {
        getSampleCustomers().forEach(customer -> {
            message = "Merry Christmas" + customer.getName() + "!\n"
                    + "Wishing you joy, peace, and happiness.\n"
                    + "Warm regards,\n"
                    + "Sasmitha";
            sendMessage(customer.getPhoneNumber(), message);
        });
    }

    @Scheduled(cron = "0 1 0 1 1 *", zone = "Asia/Colombo")
    public void sendNewYearMessages() {
        getSampleCustomers().forEach(customer -> {
            message = "Happy New Year, " + customer.getName() +
                    "!\nBest wishes,\nSasmitha";
            sendMessage(customer.getPhoneNumber(), message);
        });
    }

    void sendMessage(String phoneNumber, String message) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        // Normalize phone number to a simple international format expected by the SMS API
        String normalizedPhone = normalizePhoneNumber(phoneNumber);

        Map<String, String> body = new HashMap<>();
        body.put("recipient", normalizedPhone);
        body.put("sender_id", smsSenderId);
        body.put("type", "plain");
        body.put("message", message);

        String targetUrl = smsApiUrl;

        // Place token according to configuration: header|query|body
        if ("body".equalsIgnoreCase(smsApiTokenLocation)) {
            body.put(smsApiTokenKey, smsApiToken);
        } else if ("query".equalsIgnoreCase(smsApiTokenLocation)) {
            // append token as query param
            targetUrl = smsApiUrl + (smsApiUrl.contains("?") ? "&" : "?") + smsApiTokenKey + "=" + smsApiToken;
        } else {
            // default: header
            String headerValue = (smsAuthPrefix != null && !smsAuthPrefix.isBlank()) ? (smsAuthPrefix + " " + smsApiToken) : smsApiToken;
            httpHeaders.set(smsAuthHeader, headerValue);
        }

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, httpHeaders);

        try {
            restTemplate.postForEntity(targetUrl, request, Void.class);
            log.info("Sent SMS to {}: {}", normalizedPhone, message);
        } catch (HttpClientErrorException ex) {
            // Log HTTP error response to help debug 401/403 issues
            log.error("Failed to send SMS to {}: status={}, responseBody={}", normalizedPhone, ex.getStatusCode(), ex.getResponseBodyAsString());
        } catch (Exception ex) {
            log.error("Unexpected error when sending SMS to {}: {}", normalizedPhone, ex.getMessage(), ex);
        }
    }

    private String normalizePhoneNumber(String phone) {
        if (phone == null) return phone;
        phone = phone.trim();
        // If starts with +, remove the plus
        if (phone.startsWith("+")) {
            return phone.substring(1);
        }
        // If starts with single 0 (local format), convert to Sri Lanka country code (94)
        if (phone.startsWith("0") && phone.length() > 1) {
            return "94" + phone.substring(1);
        }
        return phone;
    }

}
