package com.pahanedu.bookshop;

import com.pahanedu.bookshop.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Book Model Tests")
class BookTest {
    
    private Book book;
    private LocalDateTime testDateTime;
    
    @BeforeEach
    void setUp() {
        book = new Book();
        testDateTime = LocalDateTime.now();
    }
    
    @Test
    @DisplayName("Should create book with default constructor")
    void testDefaultConstructor() {
        assertNotNull(book);
        assertEquals(0, book.getId());
        assertNull(book.getTitle());
        assertNull(book.getAuthor());
        assertNull(book.getIsbn());
        assertNull(book.getPrice());
        assertEquals(0, book.getStockQuantity());
        assertNull(book.getCategory());
        assertNull(book.getCreatedAt());
        assertNull(book.getUpdatedAt());
    }
    
    @Test
    @DisplayName("Should create book with parameterized constructor")
    void testParameterizedConstructor() {
        Book testBook = new Book("Test Book", "Test Author", "1234567890", 
                                new BigDecimal("29.99"), 10, "Fiction");
        
        assertEquals("Test Book", testBook.getTitle());
        assertEquals("Test Author", testBook.getAuthor());
        assertEquals("1234567890", testBook.getIsbn());
        assertEquals(new BigDecimal("29.99"), testBook.getPrice());
        assertEquals(10, testBook.getStockQuantity());
        assertEquals("Fiction", testBook.getCategory());
    }
    
    @Test
    @DisplayName("Should set and get book ID")
    void testSetAndGetId() {
        book.setId(1);
        assertEquals(1, book.getId());
        
        book.setId(999);
        assertEquals(999, book.getId());
    }
    
    @Test
    @DisplayName("Should set and get book title")
    void testSetAndGetTitle() {
        book.setTitle("Sample Book");
        assertEquals("Sample Book", book.getTitle());
        
        book.setTitle("Another Book Title");
        assertEquals("Another Book Title", book.getTitle());
    }
    
    @Test
    @DisplayName("Should set and get book author")
    void testSetAndGetAuthor() {
        book.setAuthor("John Doe");
        assertEquals("John Doe", book.getAuthor());
        
        book.setAuthor("Jane Smith");
        assertEquals("Jane Smith", book.getAuthor());
    }
    
    @Test
    @DisplayName("Should set and get book ISBN")
    void testSetAndGetIsbn() {
        book.setIsbn("978-0-123456-47-2");
        assertEquals("978-0-123456-47-2", book.getIsbn());
        
        book.setIsbn("0-7475-3269-9");
        assertEquals("0-7475-3269-9", book.getIsbn());
    }
    
    @Test
    @DisplayName("Should set and get book price")
    void testSetAndGetPrice() {
        BigDecimal price1 = new BigDecimal("19.99");
        book.setPrice(price1);
        assertEquals(price1, book.getPrice());
        
        BigDecimal price2 = new BigDecimal("0.00");
        book.setPrice(price2);
        assertEquals(price2, book.getPrice());
        
        BigDecimal price3 = new BigDecimal("99.99");
        book.setPrice(price3);
        assertEquals(price3, book.getPrice());
    }
    
    @Test
    @DisplayName("Should set and get stock quantity")
    void testSetAndGetStockQuantity() {
        book.setStockQuantity(0);
        assertEquals(0, book.getStockQuantity());
        
        book.setStockQuantity(100);
        assertEquals(100, book.getStockQuantity());
        
        book.setStockQuantity(999);
        assertEquals(999, book.getStockQuantity());
    }
    
    @Test
    @DisplayName("Should set and get book category")
    void testSetAndGetCategory() {
        book.setCategory("Science Fiction");
        assertEquals("Science Fiction", book.getCategory());
        
        book.setCategory("Non-Fiction");
        assertEquals("Non-Fiction", book.getCategory());
        
        book.setCategory("Biography");
        assertEquals("Biography", book.getCategory());
    }
    
    @Test
    @DisplayName("Should set and get created date")
    void testSetAndGetCreatedAt() {
        book.setCreatedAt(testDateTime);
        assertEquals(testDateTime, book.getCreatedAt());
        
        LocalDateTime anotherDateTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        book.setCreatedAt(anotherDateTime);
        assertEquals(anotherDateTime, book.getCreatedAt());
    }
    
    @Test
    @DisplayName("Should set and get updated date")
    void testSetAndGetUpdatedAt() {
        book.setUpdatedAt(testDateTime);
        assertEquals(testDateTime, book.getUpdatedAt());
        
        LocalDateTime anotherDateTime = LocalDateTime.of(2023, 12, 31, 23, 59);
        book.setUpdatedAt(anotherDateTime);
        assertEquals(anotherDateTime, book.getUpdatedAt());
    }
    
    @Test
    @DisplayName("Should implement Product interface getName method")
    void testGetName() {
        book.setTitle("Product Book");
        assertEquals("Product Book", book.getName());
    }
    
    @Test
    @DisplayName("Should implement Product interface setName method")
    void testSetName() {
        book.setName("New Product Name");
        assertEquals("New Product Name", book.getTitle());
        assertEquals("New Product Name", book.getName());
    }
    
    @Test
    @DisplayName("Should validate book with valid data")
    void testIsValidWithValidData() {
        book.setTitle("Valid Book");
        book.setAuthor("Valid Author");
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("25.00"));
        book.setStockQuantity(5);
        book.setCategory("Valid Category");
        
        assertTrue(book.isValid());
    }
    
    @Test
    @DisplayName("Should not validate book with null title")
    void testIsValidWithNullTitle() {
        book.setTitle(null);
        book.setAuthor("Valid Author");
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("25.00"));
        book.setStockQuantity(5);
        book.setCategory("Valid Category");
        
        assertFalse(book.isValid());
    }
    
    @Test
    @DisplayName("Should not validate book with empty title")
    void testIsValidWithEmptyTitle() {
        book.setTitle("");
        book.setAuthor("Valid Author");
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("25.00"));
        book.setStockQuantity(5);
        book.setCategory("Valid Category");
        
        assertFalse(book.isValid());
    }
    
    @Test
    @DisplayName("Should not validate book with null author")
    void testIsValidWithNullAuthor() {
        book.setTitle("Valid Book");
        book.setAuthor(null);
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("25.00"));
        book.setStockQuantity(5);
        book.setCategory("Valid Category");
        
        assertFalse(book.isValid());
    }
    
    @Test
    @DisplayName("Should not validate book with empty author")
    void testIsValidWithEmptyAuthor() {
        book.setTitle("Valid Book");
        book.setAuthor("");
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("25.00"));
        book.setStockQuantity(5);
        book.setCategory("Valid Category");
        
        assertFalse(book.isValid());
    }
    
    @Test
    @DisplayName("Should not validate book with null ISBN")
    void testIsValidWithNullIsbn() {
        book.setTitle("Valid Book");
        book.setAuthor("Valid Author");
        book.setIsbn(null);
        book.setPrice(new BigDecimal("25.00"));
        book.setStockQuantity(5);
        book.setCategory("Valid Category");
        
        assertFalse(book.isValid());
    }
    
    @Test
    @DisplayName("Should not validate book with empty ISBN")
    void testIsValidWithEmptyIsbn() {
        book.setTitle("Valid Book");
        book.setAuthor("Valid Author");
        book.setIsbn("");
        book.setPrice(new BigDecimal("25.00"));
        book.setStockQuantity(5);
        book.setCategory("Valid Category");
        
        assertFalse(book.isValid());
    }
    
    @Test
    @DisplayName("Should not validate book with null price")
    void testIsValidWithNullPrice() {
        book.setTitle("Valid Book");
        book.setAuthor("Valid Author");
        book.setIsbn("1234567890");
        book.setPrice(null);
        book.setStockQuantity(5);
        book.setCategory("Valid Category");
        
        assertFalse(book.isValid());
    }
    
    @Test
    @DisplayName("Should not validate book with negative price")
    void testIsValidWithNegativePrice() {
        book.setTitle("Valid Book");
        book.setAuthor("Valid Author");
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("-5.00"));
        book.setStockQuantity(5);
        book.setCategory("Valid Category");
        
        assertFalse(book.isValid());
    }
    
    @Test
    @DisplayName("Should validate book with zero price")
    void testIsValidWithZeroPrice() {
        book.setTitle("Valid Book");
        book.setAuthor("Valid Author");
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("0.00"));
        book.setStockQuantity(5);
        book.setCategory("Valid Category");
        
        assertTrue(book.isValid());
    }
    
    @Test
    @DisplayName("Should not validate book with negative stock quantity")
    void testIsValidWithNegativeStock() {
        book.setTitle("Valid Book");
        book.setAuthor("Valid Author");
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("25.00"));
        book.setStockQuantity(-5);
        book.setCategory("Valid Category");
        
        assertFalse(book.isValid());
    }
    
    @Test
    @DisplayName("Should validate book with zero stock quantity")
    void testIsValidWithZeroStock() {
        book.setTitle("Valid Book");
        book.setAuthor("Valid Author");
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("25.00"));
        book.setStockQuantity(0);
        book.setCategory("Valid Category");
        
        assertTrue(book.isValid());
    }
    
    @Test
    @DisplayName("Should not validate book with null category")
    void testIsValidWithNullCategory() {
        book.setTitle("Valid Book");
        book.setAuthor("Valid Author");
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("25.00"));
        book.setStockQuantity(5);
        book.setCategory(null);
        
        assertFalse(book.isValid());
    }
    
    @Test
    @DisplayName("Should not validate book with empty category")
    void testIsValidWithEmptyCategory() {
        book.setTitle("Valid Book");
        book.setAuthor("Valid Author");
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("25.00"));
        book.setStockQuantity(5);
        book.setCategory("");
        
        assertFalse(book.isValid());
    }
    
    @Test
    @DisplayName("Should generate correct display info")
    void testGetDisplayInfo() {
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("1234567890");
        book.setPrice(new BigDecimal("29.99"));
        book.setStockQuantity(15);
        book.setCategory("Fiction");
        
        String expected = "Book: Test Book by Test Author (ISBN: 1234567890, Price: $29.99, Stock: 15, Category: Fiction)";
        assertEquals(expected, book.getDisplayInfo());
    }
    
    @Test
    @DisplayName("Should handle edge case with very long strings")
    void testEdgeCaseWithLongStrings() {
        String longTitle = "A".repeat(1000);
        String longAuthor = "B".repeat(1000);
        String longIsbn = "C".repeat(1000);
        String longCategory = "D".repeat(1000);
        
        book.setTitle(longTitle);
        book.setAuthor(longAuthor);
        book.setIsbn(longIsbn);
        book.setPrice(new BigDecimal("999.99"));
        book.setStockQuantity(Integer.MAX_VALUE);
        book.setCategory(longCategory);
        
        assertTrue(book.isValid());
        assertEquals(longTitle, book.getTitle());
        assertEquals(longAuthor, book.getAuthor());
        assertEquals(longIsbn, book.getIsbn());
        assertEquals(longCategory, book.getCategory());
        assertEquals(Integer.MAX_VALUE, book.getStockQuantity());
    }
}
