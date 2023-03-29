package com.expensetracker.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.expensetracker.project.models.Employee;
// import com.expensetracker.project.service.EmployeeService;
import com.expensetracker.project.repositorys.EmployeeRepository;

import com.expensetracker.project.models.Expense;
import com.expensetracker.project.service.ExpenseService;
import com.expensetracker.project.repositorys.ExpenseRepository;

// import com.expensetracker.project.exception.ResourceNotFoundException;

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
            return new ResponseEntity<>(expenseService.findallExpense(), HttpStatus.OK);
        }
        catch(Error error){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/tutorials/{tutorialId}/comments")
    // public ResponseEntity<List<Comment>> getAllCommentsByTutorialId(@PathVariable(value = "tutorialId") int tutorialId) {    
    //     Tutorial tutorial = tutorialRepository.findById(tutorialId)
    //     .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

    //     List<Comment> comments = new ArrayList<Comment>();
    //     comments.addAll(tutorial.getComments());

    //     return new ResponseEntity<>(comments, HttpStatus.OK);
    // }


    @GetMapping("/employee/{employeeId}/expense")
    public ResponseEntity<List<Expense>> getAllExpenseByEmployeeId(@PathVariable(value = "employeeId") int employeeId){
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            // .orElseThrow(() -> new ResourceNotFoundException("Not found Employee with id = " + employeeId));
            
            List<Expense> expenses = new ArrayList<Expense>(employee.get().expenses);
            
            return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

            // if(!employeeRepository.existsById(employeeId)){
            //     throw new ResourceNotFoundException("Not found Employee with id = " + employeeId);
            //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            // }
            

    @GetMapping("expense/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable int id){
        try{
            Optional<Expense> expenseData = expenseRepository.findById(id);

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
            Optional<Expense> oldExpenseData = expenseRepository.findById(id);

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

            if(expenseRepository.findById(id).isPresent()){
                expenseRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Error error){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}