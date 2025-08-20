package com.pahanedu.bookshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Bill {
    private int id;
    private String billNumber;
    private int customerId;
    private int cashierId;
    private BigDecimal subtotal;
    private BigDecimal totalDiscount;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    
    // Additional fields for display
    private Customer customer;
    private User cashier;
    private List<BillItem> billItems;
    
    // Constructors
    public Bill() {}
    
    public Bill(String billNumber, int customerId, int cashierId, BigDecimal subtotal, 
               BigDecimal totalDiscount, BigDecimal totalAmount) {
        this.billNumber = billNumber;
        this.customerId = customerId;
        this.cashierId = cashierId;
        this.subtotal = subtotal;
        this.totalDiscount = totalDiscount;
        this.totalAmount = totalAmount;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getBillNumber() {
        return billNumber;
    }
    
    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }
    
    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    public int getCashierId() {
        return cashierId;
    }
    
    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }
    
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    
    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }
    
    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public User getCashier() {
        return cashier;
    }
    
    public void setCashier(User cashier) {
        this.cashier = cashier;
    }
    
    public List<BillItem> getBillItems() {
        return billItems;
    }
    
    public void setBillItems(List<BillItem> billItems) {
        this.billItems = billItems;
    }
}