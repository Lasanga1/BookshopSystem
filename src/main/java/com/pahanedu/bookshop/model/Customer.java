package com.pahanedu.bookshop.model;

import com.pahanedu.bookshop.resource.factory.model.Product;
import java.time.LocalDateTime;

public class Customer implements Product {
    private int id;
    private String accountNumber;
    private String name;
    private String address;
    private String telephone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public Customer() {}
    
    public Customer(String accountNumber, String name, String address, String telephone) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    // Product interface implementation
    @Override
    public boolean isValid() {
        return accountNumber != null && !accountNumber.trim().isEmpty() &&
               name != null && !name.trim().isEmpty() &&
               address != null && !address.trim().isEmpty() &&
               telephone != null && !telephone.trim().isEmpty();
    }
    
    @Override
    public String getDisplayInfo() {
        return String.format("Customer: %s (Account: %s, Address: %s, Phone: %s)",
                           name, accountNumber, address, telephone);
    }
}