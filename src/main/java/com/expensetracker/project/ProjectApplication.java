package com.expensetracker.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.expensetracker.project.repositorys.EmployeeRepository;
import com.expensetracker.project.repositorys.ExpenseRepository;

@SpringBootApplication
public class ProjectApplication {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ExpenseRepository expenseRepository;
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class);
	}

	@Bean
	public CommandLineRunner runner(){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

			}
		};
	}
}