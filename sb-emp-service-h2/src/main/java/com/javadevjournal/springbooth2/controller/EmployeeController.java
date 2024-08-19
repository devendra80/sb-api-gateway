package com.javadevjournal.springbooth2.controller;

import com.javadevjournal.springbooth2.model.Employee;
import com.javadevjournal.springbooth2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

  @Autowired
  EmployeeService employeeService;

  @GetMapping("/employees")
  private List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  @GetMapping("/employees/{id}")
  private Employee getEmployeeById(@PathVariable("id") int id) {
    return employeeService.getEmployeeById(id);
  }

  @PostMapping("/employees")
  private ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
    try{
      employeeService.saveOrUpdate(employee);
    } catch (Exception exception) {
      return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<String>("New employee created with id: "+employee.getId(), HttpStatus.CREATED);
  }

  @PutMapping("/employees/{id}")
  private ResponseEntity<String> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
    try{
      Employee existingEmployee = employeeService.getEmployeeById(id);
      if(existingEmployee != null){
        employee.setId(id);
        employeeService.saveOrUpdate(employee);
        return new ResponseEntity<String>("Employee updated with id: "+id, HttpStatus.OK);
      } else {
        return new ResponseEntity<String>("Employee not found with id: "+id, HttpStatus.NOT_FOUND);
      }
    } catch (Exception exception) {
      return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/employees/{id}")
  private ResponseEntity<String> deleteById(@PathVariable("id") int id) {
    try{
      employeeService.delete(id);
    } catch (Exception exception) {
      return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<String>("Employee deleted with id: "+id, HttpStatus.OK);
  }
}