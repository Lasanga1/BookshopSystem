package com.pahanedu.bookshop.resource.factory;

import com.pahanedu.bookshop.dao.UserDAO;
import com.pahanedu.bookshop.model.User;
import com.pahanedu.bookshop.resource.factory.model.Product;
import java.util.List;
import java.util.Optional;

/**
 * UserFactoryImpl concrete class for creating User products.
 * This is a ConcreteFactory in the Factory Pattern.
 */
public class UserFactoryImpl extends ProductFactory<User> {
    
    @Override
    public User createProduct(int id, String name) {
        User user = new User();
        user.setId(id);
        user.setUsername(name);
        user.setPassword("defaultPassword123");
        user.setFullName(name);
        user.setUserType(User.UserType.CASHIER);
        return user;
    }
    
    @Override
    protected void configureProduct(User user) {
        // Configure user-specific settings
        if (user.getUsername() != null && user.getUsername().length() < 3) {
            user.setUsername(user.getUsername() + "123");
        }
    }
    
    /**
     * Create a user with specific details
     * @param username the username
     * @param password the password
     * @param fullName the full name
     * @param userType the user type
     * @return the created User
     */
    public User createUser(String username, String password, String fullName, User.UserType userType) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setUserType(userType);
        return user;
    }
    
    // Additional methods for business logic
    private final UserDAO userDAO = new UserDAO();
    
    /**
     * Create a new User product with validation and save to database
     * @param username the username
     * @param password the password
     * @param fullName the full name
     * @param userType the user type
     * @return Optional containing the created User if successful, empty otherwise
     */
    public Optional<User> createUserWithValidation(String username, String password, String fullName, User.UserType userType) {
        
        User user = createUser(username, password, fullName, userType);
        
        if (validateUser(user)) {
            boolean success = userDAO.createUser(user);
            if (success) {
                return Optional.of(user);
            }
        }
        
        return Optional.empty();
    }
    
    /**
     * Update an existing User product
     * @param user the user to update
     * @return true if update was successful, false otherwise
     */
    public boolean updateUser(User user) {
        if (validateUser(user)) {
            return userDAO.updateUser(user);
        }
        return false;
    }
    
    /**
     * Delete a User product
     * @param userId the ID of the user to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }
    
    /**
     * Get a User by its ID
     * @param userId the user ID
     * @return Optional containing the User if found, empty otherwise
     */
    public Optional<User> getUserById(int userId) {
        User user = userDAO.getUserById(userId);
        return Optional.ofNullable(user);
    }
    
    /**
     * Get all Users
     * @return List of all users
     */
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
    
    /**
     * Validate a User object
     * @param user the user to validate
     * @return true if valid, false otherwise
     */
    private boolean validateUser(User user) {
        if (user == null) return false;
        
        // Check required fields
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return false;
        }
        
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return false;
        }
        
        if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
            return false;
        }
        
        if (user.getUserType() == null) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Search users by username or full name
     * @param searchTerm the search term
     * @return List of matching users
     */
    public List<User> searchUsers(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllUsers();
        }
        
        String term = searchTerm.toLowerCase().trim();
        return getAllUsers().stream()
                .filter(user -> user.getUsername().toLowerCase().contains(term) ||
                               user.getFullName().toLowerCase().contains(term))
                .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Validate a User object using the Product interface
     * @param user the user to validate
     * @return true if valid, false otherwise
     */
    public boolean validateProduct(User user) {
        return validateUser(user);
    }
    
    /**
     * Get validation errors for a user
     * @param user the user to validate
     * @return List of validation error messages
     */
    public List<String> getValidationErrors(User user) {
        List<String> errors = new java.util.ArrayList<>();
        
        if (user == null) {
            errors.add("User object cannot be null");
            return errors;
        }
        
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            errors.add("Username is required");
        }
        
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            errors.add("Password is required");
        }
        
        if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
            errors.add("Full name is required");
        }
        
        if (user.getUserType() == null) {
            errors.add("User type is required");
        }
        
        return errors;
    }
}
