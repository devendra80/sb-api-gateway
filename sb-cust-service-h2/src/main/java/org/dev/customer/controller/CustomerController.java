package org.dev.customer.controller;

import org.dev.customer.model.Customer;
import org.dev.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
  @Autowired
  CustomerService customerService;

  @GetMapping("/customers")
  public List<Customer> getAllCustomers() {
    return customerService.getAllCustomers();
  }

  @GetMapping("/customers/{id}")
  public Customer getCustomerById(@PathVariable("id") int id) {
    return customerService.getCustomerById(id);
  }

  @PostMapping("/customers")
  public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
    try {
      customerService.saveOrUpdate(customer);
    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body("New customer created with id: " + customer.getId());
  }

  @PutMapping("/customers/{id}")
  public ResponseEntity<String> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
    try {
      var existingCustomer = customerService.getCustomerById(id);
      if (existingCustomer != null) {
        customer.setId(id);
        customerService.saveOrUpdate(customer);
        return ResponseEntity.ok("Customer updated with id: " + id);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with id: " + id);
      }
    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @DeleteMapping("/customers/{id}")
  public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
    try {
      customerService.delete(id);
    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    return ResponseEntity.ok("Customer deleted with id: " + id);
  }
}