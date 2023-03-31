package com.expensetracker.project.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.expensetracker.project.models.Employee;

// import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    // List<Employee> findAll();
    
    // List<Employee> findById(long id);


}