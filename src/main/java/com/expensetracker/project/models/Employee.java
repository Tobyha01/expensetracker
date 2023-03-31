package com.expensetracker.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  
  @Column(name = "Id")
  private long id;
  
  @Column(name = " username")
  private String username;
  
  @Column(name = "email")
  private String email;

  public Employee(){

  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "employeeid")
  @JsonManagedReference
  private Set<Expense> expenses = new HashSet<>();

  public Employee (long id, String username, String email){
    this.id = id;
    this.username = username;
    this.email = email;
  }


  public long getId(){
    return id;
  }

  public String getUsername(){
    return username;
  }

  public String getEmail(){
    return email;
  }

  public Set<Expense> getExpenses(){
    return expenses;
  }
  
  public void setId(long id){
      this.id = id;
  }

  public void setUsername(String username){
    this.username = username;
  }

  public void setEmail(String email){
    this.email = email;
  }

  public void setExpenses(Set<Expense> expenses) {
    this.expenses = expenses;
  }

  public void removeExpenses() {
    this.expenses.clear();
  }
}