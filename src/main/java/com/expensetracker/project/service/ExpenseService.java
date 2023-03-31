package com.expensetracker.project.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.expensetracker.project.models.*;
import com.expensetracker.project.repositorys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class ExpenseService{
    @Autowired
    ExpenseRepository expenseRepository;

    
    public List<Expense> findallExpense(){
        return expenseRepository.findAll();
    }
    
    
    public Expense findExpense(Long id){
        return expenseRepository.findById(id).get();
    }


}