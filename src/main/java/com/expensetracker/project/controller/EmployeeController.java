package com.expensetracker.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.expensetracker.project.models.Employee;
import com.expensetracker.project.repositorys.EmployeeRepository;
import com.expensetracker.project.service.EmployeeService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private EmployeeService employeeService;
    
  @GetMapping("/employee/all")
    public ResponseEntity<List<Employee>> findAll() {
      try{
        List<Employee> employeeList = new ArrayList<>();

        if(employeeList.isEmpty()){
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        employeeRepository.findAll().forEach(employeeList::add);
        return new ResponseEntity<>(employeeService.findAllEmployees(), HttpStatus.OK);
      }

      catch(Error error){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    
    }

  @GetMapping("/employee/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
    try{
      Optional<Employee> employeeData = employeeRepository.findById(id);
  
      if(employeeData.isEmpty()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);

    }
    
    catch(Error error){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  
  }

  @PostMapping("/employee/add")
  public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
    try{
      return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.OK);
    }
    catch(Error error){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/employee/update/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployeeData){
    try{
      Optional<Employee> oldEmployeeData = employeeRepository.findById(id);

      if(oldEmployeeData.isPresent()){
        Employee updatedEmployeeData = oldEmployeeData.get();
        updatedEmployeeData.setUsername(newEmployeeData.getUsername());
        updatedEmployeeData.setEmail(newEmployeeData.getEmail());
        return new ResponseEntity<>(employeeRepository.save(updatedEmployeeData), HttpStatus.OK);
      }

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    catch(Error error){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @DeleteMapping("/employee/delete/{id}")
  public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id){
    try{
      
      if(employeeRepository.findById(id).isPresent()){
        employeeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
      }

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    catch(Error error){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  } 
}