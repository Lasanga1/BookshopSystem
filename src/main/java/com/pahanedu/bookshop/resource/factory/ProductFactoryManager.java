package com.pahanedu.bookshop.resource.factory;

import com.pahanedu.bookshop.resource.factory.BookFactoryImpl;
import com.pahanedu.bookshop.resource.factory.CustomerFactoryImpl;
import com.pahanedu.bookshop.resource.factory.UserFactoryImpl;
import com.pahanedu.bookshop.model.Book;
import com.pahanedu.bookshop.model.Customer;
import com.pahanedu.bookshop.model.User;
import com.pahanedu.bookshop.resource.factory.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * ProductFactoryManager provides a centralized way to manage different product factories.
 * This class demonstrates the Factory Design Pattern in action.
 */
public class ProductFactoryManager {
    
    private final BookFactoryImpl bookFactory;
    private final CustomerFactoryImpl customerFactory;
    private final UserFactoryImpl userFactory;
    
    public ProductFactoryManager() {
        this.bookFactory = new BookFactoryImpl();
        this.customerFactory = new CustomerFactoryImpl();
        this.userFactory = new UserFactoryImpl();
    }
    
    /**
     * Get the Book factory
     * @return BookFactoryImpl instance
     */
    public BookFactoryImpl getBookFactory() {
        return bookFactory;
    }
    
    /**
     * Get the Customer factory
     * @return CustomerFactoryImpl instance
     */
    public CustomerFactoryImpl getCustomerFactory() {
        return customerFactory;
    }
    
    /**
     * Get the User factory
     * @return UserFactoryImpl instance
     */
    public UserFactoryImpl getUserFactory() {
        return userFactory;
    }
    
    /**
     * Create a new book using the factory
     * @param title the book title
     * @param author the book author
     * @param isbn the book ISBN
     * @param price the book price
     * @param stockQuantity the initial stock quantity
     * @param category the book category
     * @return Optional containing the created Book if successful, empty otherwise
     */
    public Optional<Book> createBook(String title, String author, String isbn, 
                                   java.math.BigDecimal price, int stockQuantity, String category) {
        return bookFactory.createBookWithValidation(title, author, isbn, price, stockQuantity, category);
    }
    
    /**
     * Create a new customer using the factory
     * @param name the customer name
     * @param address the customer address
     * @param telephone the customer telephone number
     * @return Optional containing the created Customer if successful, empty otherwise
     */
    public Optional<Customer> createCustomer(String name, String address, String telephone) {
        return customerFactory.createCustomerWithValidation(name, address, telephone);
    }
    
    /**
     * Search books by title or author
     * @param searchTerm the search term
     * @return List of matching books
     */
    public List<Book> searchBooks(String searchTerm) {
        return bookFactory.searchBooks(searchTerm);
    }
    
    /**
     * Search customers by name or account number
     * @param searchTerm the search term
     * @return List of matching customers
     */
    public List<Customer> searchCustomers(String searchTerm) {
        return customerFactory.searchCustomers(searchTerm);
    }
    
    /**
     * Get all books
     * @return List of all books
     */
    public List<Book> getAllBooks() {
        return bookFactory.getAllBooks();
    }
    
    /**
     * Get all customers
     * @return List of all customers
     */
    public List<Customer> getAllCustomers() {
        return customerFactory.getAllCustomers();
    }
    
    /**
     * Get a book by ID
     * @param bookId the book ID
     * @return Optional containing the Book if found, empty otherwise
     */
    public Optional<Book> getBookById(int bookId) {
        return bookFactory.getBookById(bookId);
    }
    
    /**
     * Get a customer by ID
     * @param customerId the customer ID
     * @return Optional containing the Customer if found, empty otherwise
     */
    public Optional<Customer> getCustomerById(int customerId) {
        return customerFactory.getCustomerById(customerId);
    }
    
    /**
     * Update a book
     * @param book the book to update
     * @return true if update was successful, false otherwise
     */
    public boolean updateBook(Book book) {
        return bookFactory.updateBook(book);
    }
    
    /**
     * Update a customer
     * @param customer the customer to update
     * @return true if update was successful, false otherwise
     */
    public boolean updateCustomer(Customer customer) {
        return customerFactory.updateCustomer(customer);
    }
    
    /**
     * Delete a book
     * @param bookId the book ID
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteBook(int bookId) {
        return bookFactory.deleteBook(bookId);
    }
    
    /**
     * Delete a customer
     * @param customerId the customer ID
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteCustomer(int customerId) {
        return customerFactory.deleteCustomer(customerId);
    }
    
    /**
     * Check if a book is in stock
     * @param bookId the book ID
     * @param quantity the required quantity
     * @return true if book is in stock, false otherwise
     */
    public boolean isBookInStock(int bookId, int quantity) {
        return bookFactory.isInStock(bookId, quantity);
    }
    
    /**
     * Get books by category
     * @param category the category to filter by
     * @return List of books in the specified category
     */
    public List<Book> getBooksByCategory(String category) {
        return bookFactory.getBooksByCategory(category);
    }
    
    /**
     * Get customers by address area
     * @param address the address to filter by
     * @return List of customers in the specified address area
     */
    public List<Customer> getCustomersByAddress(String address) {
        return customerFactory.getCustomersByAddress(address);
    }
    
    /**
     * Validate a book
     * @param book the book to validate
     * @return true if valid, false otherwise
     */
    public boolean validateBook(Book book) {
        return bookFactory.validateProduct(book);
    }
    
    /**
     * Validate a customer
     * @param customer the customer to validate
     * @return true if valid, false otherwise
     */
    public boolean validateCustomer(Customer customer) {
        return customerFactory.validateProduct(customer);
    }
    
    /**
     * Get validation errors for a customer
     * @param customer the customer to validate
     * @return List of validation error messages
     */
    public List<String> getCustomerValidationErrors(Customer customer) {
        return customerFactory.getValidationErrors(customer);
    }
    
    /**
     * Create a new user using the factory
     * @param username the username
     * @param password the password
     * @param fullName the full name
     * @param userType the user type
     * @return Optional containing the created User if successful, empty otherwise
     */
    public Optional<User> createUser(String username, String password, String fullName, User.UserType userType) {
        return userFactory.createUserWithValidation(username, password, fullName, userType);
    }
    
    /**
     * Update a user
     * @param user the user to update
     * @return true if update was successful, false otherwise
     */
    public boolean updateUser(User user) {
        return userFactory.updateUser(user);
    }
    
    /**
     * Delete a user
     * @param userId the user ID
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteUser(int userId) {
        return userFactory.deleteUser(userId);
    }
    
    /**
     * Get a user by ID
     * @param userId the user ID
     * @return Optional containing the User if found, empty otherwise
     */
    public Optional<User> getUserById(int userId) {
        return userFactory.getUserById(userId);
    }
    
    /**
     * Get all users
     * @return List of all users
     */
    public List<User> getAllUsers() {
        return userFactory.getAllUsers();
    }
    
    /**
     * Search users by username or full name
     * @param searchTerm the search term
     * @return List of matching users
     */
    public List<User> searchUsers(String searchTerm) {
        return userFactory.searchUsers(searchTerm);
    }
    
    /**
     * Validate a user
     * @param user the user to validate
     * @return true if valid, false otherwise
     */
    public boolean validateUser(User user) {
        return userFactory.validateProduct(user);
    }
    
    /**
     * Get validation errors for a user
     * @param user the user to validate
     * @return List of validation error messages
     */
    public List<String> getUserValidationErrors(User user) {
        return userFactory.getValidationErrors(user);
    }
}
