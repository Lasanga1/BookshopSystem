package com.pahanedu.bookshop.resource.factory.model;

/**
 * Product interface for the Factory Design Pattern.
 * This interface defines the contract for all products that can be created by the factory.
 */
public interface Product {
    
    /**
     * Get the unique identifier of the product
     * @return the product ID
     */
    int getId();
    
    /**
     * Set the unique identifier of the product
     * @param id the product ID to set
     */
    void setId(int id);
    
    /**
     * Get the name/title of the product
     * @return the product name/title
     */
    String getName();
    
    /**
     * Set the name/title of the product
     * @param name the product name/title to set
     */
    void setName(String name);
    
    /**
     * Validate if the product data is valid
     * @return true if valid, false otherwise
     */
    boolean isValid();
    
    /**
     * Get a string representation of the product
     * @return formatted string representation
     */
    String getDisplayInfo();
}
