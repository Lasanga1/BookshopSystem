package com.pahanedu.bookshop.resource.factory;

import com.pahanedu.bookshop.dao.CustomerDAO;
import com.pahanedu.bookshop.model.Customer;
import com.pahanedu.bookshop.resource.factory.model.Product;
import java.util.List;
import java.util.Optional;

/**
 * CustomerFactoryImpl concrete class for creating Customer products.
 * This is a ConcreteFactory in the Factory Pattern.
 */
public class CustomerFactoryImpl extends ProductFactory<Customer> {
    
    @Override
    public Customer createProduct(int id, String name) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setAccountNumber(generateAccountNumber());
        customer.setAddress("Address not specified");
        customer.setTelephone("Phone not specified");
        return customer;
    }
    
    @Override
    protected void configureProduct(Customer customer) {
        // Configure customer-specific settings
        if (customer.getName() != null && customer.getName().length() > 20) {
            customer.setName(customer.getName().substring(0, 20));
        }
    }
    
    /**
     * Create a customer with specific details
     * @param name the customer name
     * @param address the customer address
     * @param telephone the customer telephone
     * @return the created Customer
     */
    public Customer createCustomer(String name, String address, String telephone) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAccountNumber(generateAccountNumber());
        customer.setAddress(address);
        customer.setTelephone(telephone);
        return customer;
    }
    
    /**
     * Generate a unique account number
     * @return a unique account number
     */
    private String generateAccountNumber() {
        String prefix = "CUST";
        long timestamp = System.currentTimeMillis();
        return prefix + String.format("%06d", timestamp % 1000000);
    }
    
    // Additional methods for business logic
    private final CustomerDAO customerDAO = new CustomerDAO();
    
    /**
     * Create a new Customer product with validation and save to database
     * @param name the customer name
     * @param address the customer address
     * @param telephone the customer telephone
     * @return Optional containing the created Customer if successful, empty otherwise
     */
    public Optional<Customer> createCustomerWithValidation(String name, String address, String telephone) {
        
        Customer customer = createCustomer(name, address, telephone);
        
        if (validateCustomer(customer)) {
            boolean success = customerDAO.createCustomer(customer);
            if (success) {
                return Optional.of(customer);
            }
        }
        
        return Optional.empty();
    }
    
    /**
     * Update an existing Customer product
     * @param customer the customer to update
     * @return true if update was successful, false otherwise
     */
    public boolean updateCustomer(Customer customer) {
        if (validateCustomer(customer)) {
            return customerDAO.updateCustomer(customer);
        }
        return false;
    }
    
    /**
     * Delete a Customer product
     * @param customerId the ID of the customer to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteCustomer(int customerId) {
        return customerDAO.deleteCustomer(customerId);
    }
    
    /**
     * Get a Customer by its ID
     * @param customerId the customer ID
     * @return Optional containing the Customer if found, empty otherwise
     */
    public Optional<Customer> getCustomerById(int customerId) {
        Customer customer = customerDAO.getCustomerById(customerId);
        return Optional.ofNullable(customer);
    }
    
    /**
     * Get all Customers
     * @return List of all customers
     */
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
    
    /**
     * Validate a Customer object
     * @param customer the customer to validate
     * @return true if valid, false otherwise
     */
    private boolean validateCustomer(Customer customer) {
        if (customer == null) return false;
        
        // Check required fields
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            return false;
        }
        
        if (customer.getAddress() == null || customer.getAddress().trim().isEmpty()) {
            return false;
        }
        
        if (customer.getTelephone() == null || customer.getTelephone().trim().isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Search customers by name or account number
     * @param searchTerm the search term
     * @return List of matching customers
     */
    public List<Customer> searchCustomers(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllCustomers();
        }
        
        String term = searchTerm.toLowerCase().trim();
        return getAllCustomers().stream()
                .filter(customer -> customer.getName().toLowerCase().contains(term) ||
                                   customer.getAccountNumber().toLowerCase().contains(term))
                .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Get customers by address area
     * @param address the address to filter by
     * @return List of customers in the specified address area
     */
    public List<Customer> getCustomersByAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            return getAllCustomers();
        }
        
        String addr = address.toLowerCase().trim();
        return getAllCustomers().stream()
                .filter(customer -> customer.getAddress().toLowerCase().contains(addr))
                .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Validate a Customer object using the Product interface
     * @param customer the customer to validate
     * @return true if valid, false otherwise
     */
    public boolean validateProduct(Customer customer) {
        return validateCustomer(customer);
    }
    
    /**
     * Get validation errors for a customer
     * @param customer the customer to validate
     * @return List of validation error messages
     */
    public List<String> getValidationErrors(Customer customer) {
        List<String> errors = new java.util.ArrayList<>();
        
        if (customer == null) {
            errors.add("Customer object cannot be null");
            return errors;
        }
        
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            errors.add("Customer name is required");
        }
        
        if (customer.getAddress() == null || customer.getAddress().trim().isEmpty()) {
            errors.add("Customer address is required");
        }
        
        if (customer.getTelephone() == null || customer.getTelephone().trim().isEmpty()) {
            errors.add("Customer telephone is required");
        }
        
        return errors;
    }
}
