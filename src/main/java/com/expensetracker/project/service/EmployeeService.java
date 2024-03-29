package com.expensetracker.project.service;

import com.expensetracker.project.models.*;
import com.expensetracker.project.repositorys.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ExpenseRepository expenseRepository;
    
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }
    
    public Employee findEmployee(long id){
        return employeeRepository.findById(id).get();
    }
}