package com.expensetracker.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// import java.util.List;
import java.util.Set;

import javax.persistence.*;

// import java.util.HashSet;
// import java.util.Set;

@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  
  @Column(/* name = "Id" */)
  private Long id;
  
  @Column(/* name = " username" */)
  private String username;
  
  @Column(/* name = "email" */)
  private String email;

  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  public Set<Expense> expenses;
  // private Set<Expense> expenses = new HashSet<>();
  
  public Employee(){

  }

  public Employee (Long id, String username, String email){
    this.id = id;
    this.username = username;
    this.email = email;
  }


  public Long getId(){
    return id;
  }

  public String getUsername(){
    return username;
  }

  public String getEmail(){
    return email;
  }

  public void setId(Long id){
      this.id = id;
  }

  public void setUsername(String username){
    this.username = username;
  }
  
  public void setEmail(String email){
    this.email = email;
  }


}