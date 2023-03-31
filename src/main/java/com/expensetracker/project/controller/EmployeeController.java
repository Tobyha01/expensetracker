package com.expensetracker.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


import com.expensetracker.project.models.Employee;
import com.expensetracker.project.repositorys.EmployeeRepository;
import com.expensetracker.project.exception.ResourceNotFoundException;

@RestController
public class EmployeeController {

  @Autowired
  EmployeeRepository employeeRepository;

  @GetMapping("/employee/all")
  public ResponseEntity<List<Employee>> getAllEmployees(Long id) {
    try{
      List<Employee> employees = new ArrayList<Employee>();
      employeeRepository.findAll().forEach(employees::add);

      if (employees.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    catch(Exception error){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }  

  }

  @GetMapping("/employee/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
    Employee employee = employeeRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Not found employee with id: " + id));

    return new ResponseEntity<>(employee, HttpStatus.OK);
  }

  @PostMapping("/employee/add")
  public ResponseEntity<Employee> createEmployee(@RequestBody Employee newEmployee) {
    try {
      return new ResponseEntity<>(employeeRepository.save(newEmployee), HttpStatus.CREATED);
    }
    catch(Exception error){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/employee/update/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployeeData) {
    Employee employeeData = employeeRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Not found Employee with id: " + id));
    
    employeeData.setUsername(newEmployeeData.getUsername());
    employeeData.setEmail(newEmployeeData.getEmail());
    // oldEmployee.setPublished(employee.isPublished());
    
    return new ResponseEntity<>(employeeRepository.save(employeeData), HttpStatus.OK);
  }

  @DeleteMapping("/employee/delete/{id}")
  public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
    employeeRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Not found employee with id: " +id));

    employeeRepository.deleteById(id);
    
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/employee/delete/all")
  public ResponseEntity<HttpStatus> deleteAllEmployees() {
    try{
      List<Employee> employees = new ArrayList<Employee>();
      employeeRepository.findAll().forEach(employees::add);

      if (employees.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      employeeRepository.deleteAll(employees);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    catch(Exception error){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }  
  }
}