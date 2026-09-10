package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO (Copilot Chat): in-memory list store.
 * addCustomer: reject null/blank id; reject duplicate; store and return.
 * findByCustomerId; updateStatus.
 * No Spring / JPA.
 */
public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();

    public Customer addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        String customerId = customer.getCustomerId();

        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("customerId cannot be null or blank");
        }

        boolean duplicate = customers.stream()
                .anyMatch(existing -> existing.getCustomerId().equals(customerId));

        if (duplicate) {
            throw new IllegalStateException(
                    "Customer with customerId " + customerId + " already exists"
            );
        }

        customers.add(customer);
        return customer;
    }

    public Optional<Customer> findByCustomerId(String customerId) {
        if (customerId == null || customerId.isBlank()) {
            return Optional.empty();
        }

        return customers.stream()
                .filter(customer -> customer.getCustomerId().equals(customerId))
                .findFirst();
    }

    public Customer updateStatus(String customerId, CustomerStatus status) {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("customerId cannot be null or blank");
        }

        if (status == null) {
            throw new IllegalArgumentException("status cannot be null");
        }

        Customer customer = customers.stream()
                .filter(existing -> existing.getCustomerId().equals(customerId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Customer with customerId " + customerId + " does not exist"
                ));

        customer.setStatus(status);
        return customer;
    }
}