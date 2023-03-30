package com.expensetracker.project.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.expensetracker.project.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{


}