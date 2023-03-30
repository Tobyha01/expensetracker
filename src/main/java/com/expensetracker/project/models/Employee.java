package com.expensetracker.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

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