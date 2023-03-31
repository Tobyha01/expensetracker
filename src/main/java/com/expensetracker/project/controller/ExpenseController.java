package com.expensetracker.project.controller;

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

import com.expensetracker.project.models.Expense;
import com.expensetracker.project.service.ExpenseService;
import com.expensetracker.project.repositorys.ExpenseRepository;
import com.expensetracker.project.exception.ResourceNotFoundException;

import java.util.List;
import java.util.ArrayList;

@RestController
public class ExpenseController {
    
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    ExpenseService expenseService;
    
    @GetMapping("/expense/all")
    public ResponseEntity<List<Expense>> getAllExpenses(Long id){
        try{
            List<Expense> expenseList = new ArrayList<Expense>();
            expenseRepository.findAll().forEach(expenseList::add);

            if(expenseList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(expenseList, HttpStatus.OK);
        }
        catch(Error error){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/expense/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id){
        Expense expenseData = expenseRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Not found expense with id: " + id));

        return new ResponseEntity<>(expenseData, HttpStatus.OK);
    }
    
    @GetMapping("/employee/{employeeid}/expense")
    public ResponseEntity<List<Expense>> getAllExpenseByemployeeid(@PathVariable Long employeeid){

        Employee employee = employeeRepository.findById(employeeid)
          .orElseThrow(() -> new ResourceNotFoundException("Not found employee's expenses with id: " + employeeid));

            List<Expense> expenses = new ArrayList<Expense>();
            expenses.addAll(employee.getExpenses());
            
            if(expenses.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @PostMapping("/employee/{employeeid}/add/expense")
    public ResponseEntity<Expense> addEmployeeExpense(@PathVariable Long employeeid, @RequestBody Expense expenseRequest){
        
        Expense expense = employeeRepository.findById(employeeid).map(employee -> {
            employee.getExpenses().add(expenseRequest);
            return expenseRepository.save(expenseRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found employee with id: " +employeeid));

        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @PutMapping("/expense/update/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense newExpenseData){
        Expense expenseData = expenseRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Not found expense with id: " +id));
        
        expenseData.setNote(newExpenseData.getNote());  
        expenseData.setCategory(newExpenseData.getCategory());
        expenseData.setAmount(newExpenseData.getAmount());

        return new ResponseEntity<>(expenseRepository.save(expenseData), HttpStatus.OK);
    }

    @DeleteMapping("/expense/delete/{id}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable Long id){
        expenseRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Not found Expense with id: " +id));

        expenseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/employee/delete/{employeeid}/expenses")
    public ResponseEntity<List<Expense>> deleteAllEmployeeExpenses(@PathVariable Long employeeid){
        Employee employee = employeeRepository.findById(employeeid)
          .orElseThrow(() -> new ResourceNotFoundException("Not found employee with id : " +employeeid));
        employee.removeExpenses();
        employeeRepository.save(employee);

        if(employee.getExpenses().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/expense/delete/all")
    public ResponseEntity<HttpStatus> deleteAllExpenses (){
        try{
            List<Expense> expenses = new ArrayList<Expense>();
            expenseRepository.findAll().forEach(expenses::add);

            if(expenses.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            expenseRepository.deleteAll(expenses);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception error){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}