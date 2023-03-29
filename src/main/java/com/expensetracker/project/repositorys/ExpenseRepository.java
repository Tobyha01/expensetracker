package com.expensetracker.project.repositorys;

// import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.expensetracker.project.models.Expense;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
    List<Expense> findByEmployeeId(Long employeeId);
    
    List<Expense> findAll(Long employeeId);

    
    // @Transactional
    // List<Expense> findByStatusAndEmployeeId(boolean status, int employeeId);

    // @Transactional
    // void deleteByEmployeeId(int employeeId);

}