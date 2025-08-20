package com.pahanedu.bookshop.resource.factory;

import com.pahanedu.bookshop.resource.factory.model.Product;

/**
 * ProductFactory abstract class for the Factory Pattern.
 * This defines the factory method that concrete implementations must implement.
 * 
 * @param <T> the type of product this factory creates
 */
public abstract class ProductFactory<T extends Product> {
    
    /**
     * Abstract factory method to create a product
     * @param id the product ID
     * @param name the product name
     * @return the created product
     */
    public abstract T createProduct(int id, String name);
    
    /**
     * Template method that creates and configures a product
     * @param id the product ID
     * @param name the product name
     * @return the created and configured product
     */
    public final T createAndConfigureProduct(int id, String name) {
        T product = createProduct(id, name);
        configureProduct(product);
        return product;
    }
    
    /**
     * Hook method for configuring the product after creation
     * @param product the product to configure
     */
    protected abstract void configureProduct(T product);
    
    /**
     * Check if this factory can create a product with the given parameters
     * @param id the product ID
     * @param name the product name
     * @return true if the factory can create the product, false otherwise
     */
    protected boolean canCreateProduct(int id, String name) {
        return id > 0 && name != null && !name.trim().isEmpty();
    }
}
