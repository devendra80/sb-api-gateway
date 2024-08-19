package org.dev.customer.service;

import org.dev.customer.model.Customer;
import org.dev.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<Customer> getAllCustomers() {
    return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());
  }

  public Customer getCustomerById(int id) {
    return customerRepository.findById(id).orElse(null);
}

  public void saveOrUpdate(Customer customer) {
    customerRepository.save(customer);
  }

  public void delete(int id) {
    customerRepository.deleteById(id);
  }
}