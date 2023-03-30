package com.expensetracker.project.service;

import org.springframework.stereotype.Service;
import com.expensetracker.project.repositorys.*;
import com.expensetracker.project.models.*;
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
    
    public Employee findEmployee(int id){
        return employeeRepository.findById(id).get();
    }
}