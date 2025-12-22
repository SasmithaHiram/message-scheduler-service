package edu.icet.ecom.controller;

import edu.icet.ecom.model.Customer;
import edu.icet.ecom.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers(@RequestParam(value = "name", required = false) String name) {
        List<Customer> all = customerService.getCustomers();
        if (name == null || name.isBlank()) return all;
        return all.stream().filter(c -> c.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

}
