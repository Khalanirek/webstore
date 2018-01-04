package com.packt.domain.repository.impl;

import com.packt.domain.Customer;
import com.packt.domain.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemmoryCustomerRepository implements CustomerRepository {

    private List<Customer> listOfCustomers = new ArrayList<Customer>();

    public InMemmoryCustomerRepository(){

        listOfCustomers.add(new Customer("Customer_1", "Piotr", "Polna 3", false));
        listOfCustomers.add(new Customer("Customer_2", "Michal", "Woloska 5", false));
        listOfCustomers.add(new Customer("Customer_3", "Marcin", "Rabatowa 15", false));
        listOfCustomers.add(new Customer("Customer_4", "Tomek", "Rybacka 21", false));
        listOfCustomers.add(new Customer("Customer_5", "Robert", "Lisia 8", false));

    }
    public List<Customer> getAllCustomers() {
        return listOfCustomers;
    }
}
