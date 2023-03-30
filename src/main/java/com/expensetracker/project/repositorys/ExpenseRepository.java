package com.expensetracker.project.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.expensetracker.project.models.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

}