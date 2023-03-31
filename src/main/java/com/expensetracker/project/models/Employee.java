package com.expensetracker.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "employee_generator")
    
    @Column(name = "Id")
    private Integer id;
    
    @Column(name = " username")
    private String username;
    
    @Column(name = "email")
    private String email;

    public Employee(){

    }

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    public Set<Expense> expenses;

    public Employee (Integer id, String username, String email){
      this.id = id;
      this.username = username;
      this.email = email;
    }


    public Integer getId(){
      return id;
    }

    public String getUsername(){
      return username;
    }

    public String getEmail(){
      return email;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setUsername(String username){
      this.username = username;
    }
    
    public void setEmail(String email){
      this.email = email;
    }
}