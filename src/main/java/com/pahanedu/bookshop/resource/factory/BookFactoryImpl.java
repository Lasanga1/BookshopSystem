package com.pahanedu.bookshop.resource.factory;

import com.pahanedu.bookshop.dao.BookDAO;
import com.pahanedu.bookshop.model.Book;
import com.pahanedu.bookshop.resource.factory.model.Product;
import java.util.List;
import java.util.Optional;

/**
 * BookFactoryImpl concrete class for creating Book products.
 * This is a ConcreteFactory in the Factory Pattern.
 */
public class BookFactoryImpl extends ProductFactory<Book> {
    
    @Override
    public Book createProduct(int id, String name) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(name);
        book.setAuthor("Unknown Author");
        book.setIsbn("ISBN-" + System.currentTimeMillis());
        book.setPrice(java.math.BigDecimal.ZERO);
        book.setStockQuantity(0);
        book.setCategory("General");
        return book;
    }
    
    @Override
    protected void configureProduct(Book book) {
        // Configure book-specific settings
        if (book.getTitle() != null && book.getTitle().contains("Fiction")) {
            book.setCategory("Fiction");
        } else if (book.getTitle() != null && book.getTitle().contains("Science")) {
            book.setCategory("Science");
        }
    }
    
    /**
     * Create a book with specific details
     * @param title the book title
     * @param author the book author
     * @param isbn the book ISBN
     * @param price the book price
     * @param stockQuantity the stock quantity
     * @param category the book category
     * @return the created Book
     */
    public Book createBook(String title, String author, String isbn, 
                          java.math.BigDecimal price, int stockQuantity, String category) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        book.setCategory(category);
        return book;
    }
    
    // Additional methods for business logic
    private final BookDAO bookDAO = new BookDAO();
    
    /**
     * Create a new Book product with validation and save to database
     * @param title the book title
     * @param author the book author
     * @param isbn the book ISBN
     * @param price the book price
     * @param stockQuantity the initial stock quantity
     * @param category the book category
     * @return Optional containing the created Book if successful, empty otherwise
     */
    public Optional<Book> createBookWithValidation(String title, String author, String isbn, 
                                                 java.math.BigDecimal price, int stockQuantity, String category) {
        
        Book book = createBook(title, author, isbn, price, stockQuantity, category);
        
        if (validateBook(book)) {
            boolean success = bookDAO.createBook(book);
            if (success) {
                return Optional.of(book);
            }
        }
        
        return Optional.empty();
    }
    
    /**
     * Update an existing Book product
     * @param book the book to update
     * @return true if update was successful, false otherwise
     */
    public boolean updateBook(Book book) {
        if (validateBook(book)) {
            return bookDAO.updateBook(book);
        }
        return false;
    }
    
    /**
     * Delete a Book product
     * @param bookId the ID of the book to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteBook(int bookId) {
        return bookDAO.deleteBook(bookId);
    }
    
    /**
     * Get a Book by its ID
     * @param bookId the book ID
     * @return Optional containing the Book if found, empty otherwise
     */
    public Optional<Book> getBookById(int bookId) {
        Book book = bookDAO.getBookById(bookId);
        return Optional.ofNullable(book);
    }
    
    /**
     * Get all Books
     * @return List of all books
     */
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }
    
    /**
     * Validate a Book object
     * @param book the book to validate
     * @return true if valid, false otherwise
     */
    private boolean validateBook(Book book) {
        if (book == null) return false;
        
        // Check required fields
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            return false;
        }
        
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            return false;
        }
        
        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            return false;
        }
        
        if (book.getPrice() == null || book.getPrice().compareTo(java.math.BigDecimal.ZERO) < 0) {
            return false;
        }
        
        if (book.getStockQuantity() < 0) {
            return false;
        }
        
        if (book.getCategory() == null || book.getCategory().trim().isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Search books by title or author
     * @param searchTerm the search term
     * @return List of matching books
     */
    public List<Book> searchBooks(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllBooks();
        }
        
        String term = searchTerm.toLowerCase().trim();
        return getAllBooks().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(term) ||
                               book.getAuthor().toLowerCase().contains(term))
                .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Get books by category
     * @param category the category to filter by
     * @return List of books in the specified category
     */
    public List<Book> getBooksByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            return getAllBooks();
        }
        
        String cat = category.toLowerCase().trim();
        return getAllBooks().stream()
                .filter(book -> book.getCategory().toLowerCase().equals(cat))
                .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Check if a book is in stock
     * @param bookId the book ID
     * @param quantity the required quantity
     * @return true if book is in stock, false otherwise
     */
    public boolean isInStock(int bookId, int quantity) {
        Optional<Book> bookOpt = getBookById(bookId);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            return book.getStockQuantity() >= quantity;
        }
        return false;
    }
    
    /**
     * Validate a Book object using the Product interface
     * @param book the book to validate
     * @return true if valid, false otherwise
     */
    public boolean validateProduct(Book book) {
        return validateBook(book);
    }
}
