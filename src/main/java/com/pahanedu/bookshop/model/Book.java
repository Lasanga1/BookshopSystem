package com.pahanedu.bookshop.model;

import com.pahanedu.bookshop.resource.factory.model.Product;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Book implements Product {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private int stockQuantity;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public Book() {}
    
    public Book(String title, String author, String isbn, BigDecimal price, int stockQuantity, String category) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public int getStockQuantity() {
        return stockQuantity;
    }
    
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
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
        return this.title;
    }
    
    @Override
    public void setName(String name) {
        this.title = name;
    }
    
    @Override
    public boolean isValid() {
        return title != null && !title.trim().isEmpty() &&
               author != null && !author.trim().isEmpty() &&
               isbn != null && !isbn.trim().isEmpty() &&
               price != null && price.compareTo(BigDecimal.ZERO) >= 0 &&
               stockQuantity >= 0 &&
               category != null && !category.trim().isEmpty();
    }
    
    @Override
    public String getDisplayInfo() {
        return String.format("Book: %s by %s (ISBN: %s, Price: $%s, Stock: %d, Category: %s)",
                           title, author, isbn, price, stockQuantity, category);
    }
}