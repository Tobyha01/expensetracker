package com.expensetracker.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.expensetracker.project.repositorys.EmployeeRepository;

import com.expensetracker.project.models.Expense;
import com.expensetracker.project.service.ExpenseService;
import com.expensetracker.project.repositorys.ExpenseRepository;


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
public class ExpenseController {
    
    @Autowired
    EmployeeRepository employeeRepository;


    
    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    ExpenseService expenseService;
    
    @GetMapping("/expense/all")
    public ResponseEntity<List<Expense>> findAll(){
        try{
            List<Expense> expenseList = new ArrayList<>();
            expenseRepository.findAll().forEach(expenseList::add);

            if(expenseList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(expenseService.findAllExpenses(), HttpStatus.OK);
        }
        catch(Error error){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("expense/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable int id){
        try{
            Optional<Expense> expenseData = expenseRepository.findById((long) id);

            if(expenseData.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(expenseData.get(), HttpStatus.OK);
        }
        catch(Error error){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("expense/add")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense){
        try{
            return new ResponseEntity<>(expenseRepository.save(expense), HttpStatus.OK);
        }
        catch(Error error){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("expense/update/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable int id, @RequestBody Expense newExpenseData){
        try{
            Optional<Expense> oldExpenseData = expenseRepository.findById((long) id);

            if(oldExpenseData.isPresent()){
                Expense updatedExpenseData = oldExpenseData.get();
                updatedExpenseData.setNote(newExpenseData.getNote());
                updatedExpenseData.setCategory(newExpenseData.getCategory());
                updatedExpenseData.setAmount(newExpenseData.getAmount());
                return new ResponseEntity<>(expenseRepository.save(updatedExpenseData), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Error error){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("expense/delete/{id}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable int id){
        try{

            if(expenseRepository.findById((long) id).isPresent()){
                expenseRepository.deleteById((long) id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Error error){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}