package com.pahanedu.bookshop.resource.factory;

import com.pahanedu.bookshop.model.Book;
import com.pahanedu.bookshop.model.Customer;
import com.pahanedu.bookshop.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Simple test class to verify that the Factory Pattern is working correctly.
 * This class demonstrates how the factories are used in the application.
 */
public class FactoryPatternTest {
    
    public static void main(String[] args) {
        System.out.println("=== Factory Pattern Test ===");
        
        // Create factory manager
        ProductFactoryManager factoryManager = new ProductFactoryManager();
        
        // Test Book Factory
        System.out.println("\n--- Testing Book Factory ---");
        Optional<Book> bookOpt = factoryManager.createBook(
            "Test Book", "Test Author", "ISBN-123", 
            new BigDecimal("29.99"), 10, "Fiction"
        );
        
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            System.out.println("✓ Book created successfully: " + book.getTitle());
            System.out.println("  - Valid: " + book.isValid());
            System.out.println("  - Display Info: " + book.getDisplayInfo());
        } else {
            System.out.println("✗ Failed to create book");
        }
        
        // Test Customer Factory
        System.out.println("\n--- Testing Customer Factory ---");
        Optional<Customer> customerOpt = factoryManager.createCustomer(
            "John Doe", "123 Main St", "555-1234"
        );
        
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            System.out.println("✓ Customer created successfully: " + customer.getName());
            System.out.println("  - Account Number: " + customer.getAccountNumber());
            System.out.println("  - Valid: " + customer.isValid());
            System.out.println("  - Display Info: " + customer.getDisplayInfo());
        } else {
            System.out.println("✗ Failed to create customer");
        }
        
        // Test User Factory
        System.out.println("\n--- Testing User Factory ---");
        Optional<User> userOpt = factoryManager.createUser(
            "testuser", "password123", "Test User", User.UserType.CASHIER
        );
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            System.out.println("✓ User created successfully: " + user.getFullName());
            System.out.println("  - Username: " + user.getUsername());
            System.out.println("  - Valid: " + user.isValid());
            System.out.println("  - Display Info: " + user.getDisplayInfo());
        } else {
            System.out.println("✗ Failed to create user");
        }
        
        // Test Product Interface Methods
        System.out.println("\n--- Testing Product Interface ---");
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            System.out.println("Book ID: " + book.getId());
            System.out.println("Book Name: " + book.getName());
            book.setName("Updated Book Title");
            System.out.println("Updated Book Name: " + book.getName());
        }
        
        System.out.println("\n=== Factory Pattern Test Complete ===");
    }
}
