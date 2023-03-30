package com.expensetracker.project.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    
    @Column
    private String note;
    
    @Column
    private String category;
    
    @Column
    private double amount;

    public Expense(Long inputId, String inputNote, String inputCategory, double inputAmount){
        this.id = inputId;
        this.note = inputNote;
        this.category = inputCategory;
        this.amount = inputAmount;
    }

    public Expense(){

    }

    public Long getId(){
      return id;
    }

    public String getNote(){
      return note;
    }

    public String getCategory(){
      return category;
    }

    public double getAmount(){
        return amount;
    }
    
    public void setId(Long id){
      this.id = id;
    }

    public void setNote(String note){
      this.note = note;
    }

    public void setCategory(String category){
      this.category = category;
    }
    
    public void setAmount(double amount){
        this.amount = amount;
    }
}