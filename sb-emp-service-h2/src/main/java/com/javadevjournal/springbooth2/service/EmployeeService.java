package com.javadevjournal.springbooth2.service;

import com.javadevjournal.springbooth2.model.Employee;
import com.javadevjournal.springbooth2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  public List<Employee> getAllEmployees() {
    List<Employee> employees = new ArrayList<Employee>();
    employeeRepository.findAll().forEach(employee -> employees.add(employee));
    return employees;
  }

  public Employee getEmployeeById(int id) {
    return employeeRepository.findById(id).orElse(null);
}

  public void saveOrUpdate(Employee employee) {
    employeeRepository.save(employee);
  }

  public void delete(int id) {
    employeeRepository.deleteById(id);
  }
}