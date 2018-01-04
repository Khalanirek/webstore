package com.packt.domain.repository;

import com.packt.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CustomerRepository {
    List<Customer> getAllCustomers();
}
