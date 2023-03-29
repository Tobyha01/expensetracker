package com.expensetracker.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.expensetracker.project.models.*;
import com.expensetracker.project.repositorys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    public List<Expense> findallExpense(){
        return expenseRepository.findAll();
    }

    public Expense findExpense(Long id){
        return expenseRepository.findById(id).get();
    }

    @Autowired
    ExpenseRepository expenseRepository;
    public ResponseEntity<List<Expense>> getAllExpenseByEmployeeId(@PathVariable(value = "employeeId") int employeeId) {
        List<Expense> employeeExpenses = expenseRepository.findByEmployeeId(employeeId);
        return new ResponseEntity<>(employeeExpenses, HttpStatus.OK);
    }
}