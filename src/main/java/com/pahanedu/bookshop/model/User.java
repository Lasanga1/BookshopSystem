package com.pahanedu.bookshop.model;

import com.pahanedu.bookshop.resource.factory.model.Product;
import java.time.LocalDateTime;

public class User implements Product {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private UserType userType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public enum UserType {
        ADMIN, CASHIER
    }
    
    // Constructors
    public User() {}
    
    public User(String username, String password, String fullName, UserType userType) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.userType = userType;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public UserType getUserType() {
        return userType;
    }
    
    public void setUserType(UserType userType) {
        this.userType = userType;
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
    public String getName() {
        return this.fullName;
    }
    
    @Override
    public void setName(String name) {
        this.fullName = name;
    }
    
    @Override
    public boolean isValid() {
        return username != null && !username.trim().isEmpty() &&
               password != null && !password.trim().isEmpty() &&
               fullName != null && !fullName.trim().isEmpty() &&
               userType != null;
    }
    
    @Override
    public String getDisplayInfo() {
        return String.format("User: %s (Username: %s, Type: %s)",
                           fullName, username, userType);
    }
}