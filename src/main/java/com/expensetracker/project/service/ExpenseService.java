package com.expensetracker.project.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.expensetracker.project.models.Expense;
import com.expensetracker.project.repositorys.ExpenseRepository;

import java.util.List;

@Service
public class ExpenseService{
    @Autowired
    ExpenseRepository expenseRepository;

    public List<Expense> findAllExpenses() {
        return expenseRepository.findAll();
    }
    
    public Expense findExpense(Long id){
        return expenseRepository.findById(id).get();
    }

    ExpenseRepository expensRepository;
    public List<Expense> findAllExpense(){
        return expenseRepository.findAll();
    }
}