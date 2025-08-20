package com.pahanedu.bookshop;

import com.pahanedu.bookshop.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Customer Model Tests")
class CustomerTest {
    
    private Customer customer;
    private LocalDateTime testDateTime;
    
    @BeforeEach
    void setUp() {
        customer = new Customer();
        testDateTime = LocalDateTime.now();
    }
    
    @Test
    @DisplayName("Should create customer with default constructor")
    void testDefaultConstructor() {
        assertNotNull(customer);
        assertEquals(0, customer.getId());
        assertNull(customer.getAccountNumber());
        assertNull(customer.getName());
        assertNull(customer.getAddress());
        assertNull(customer.getTelephone());
        assertNull(customer.getCreatedAt());
        assertNull(customer.getUpdatedAt());
    }
    
    @Test
    @DisplayName("Should create customer with parameterized constructor")
    void testParameterizedConstructor() {
        Customer testCustomer = new Customer("ACC001", "John Doe", "123 Main St", "555-1234");
        
        assertEquals("ACC001", testCustomer.getAccountNumber());
        assertEquals("John Doe", testCustomer.getName());
        assertEquals("123 Main St", testCustomer.getAddress());
        assertEquals("555-1234", testCustomer.getTelephone());
    }
    
    @Test
    @DisplayName("Should set and get customer ID")
    void testSetAndGetId() {
        customer.setId(1);
        assertEquals(1, customer.getId());
        
        customer.setId(999);
        assertEquals(999, customer.getId());
        
        customer.setId(0);
        assertEquals(0, customer.getId());
    }
    
    @Test
    @DisplayName("Should set and get account number")
    void testSetAndGetAccountNumber() {
        customer.setAccountNumber("ACC001");
        assertEquals("ACC001", customer.getAccountNumber());
        
        customer.setAccountNumber("CUST123");
        assertEquals("CUST123", customer.getAccountNumber());
        
        customer.setAccountNumber("12345");
        assertEquals("12345", customer.getAccountNumber());
    }
    
    @Test
    @DisplayName("Should set and get customer name")
    void testSetAndGetName() {
        customer.setName("John Doe");
        assertEquals("John Doe", customer.getName());
        
        customer.setName("Jane Smith");
        assertEquals("Jane Smith", customer.getName());
        
        customer.setName("Dr. Robert Johnson III");
        assertEquals("Dr. Robert Johnson III", customer.getName());
    }
    
    @Test
    @DisplayName("Should set and get customer address")
    void testSetAndGetAddress() {
        customer.setAddress("123 Main Street");
        assertEquals("123 Main Street", customer.getAddress());
        
        customer.setAddress("456 Oak Avenue, Suite 100");
        assertEquals("456 Oak Avenue, Suite 100", customer.getAddress());
        
        customer.setAddress("P.O. Box 789, City, State 12345");
        assertEquals("P.O. Box 789, City, State 12345", customer.getAddress());
    }
    
    @Test
    @DisplayName("Should set and get customer telephone")
    void testSetAndGetTelephone() {
        customer.setTelephone("555-1234");
        assertEquals("555-1234", customer.getTelephone());
        
        customer.setTelephone("(555) 123-4567");
        assertEquals("(555) 123-4567", customer.getTelephone());
        
        customer.setTelephone("+1-555-123-4567");
        assertEquals("+1-555-123-4567", customer.getTelephone());
        
        customer.setTelephone("123.456.7890");
        assertEquals("123.456.7890", customer.getTelephone());
    }
    
    @Test
    @DisplayName("Should set and get created date")
    void testSetAndGetCreatedAt() {
        customer.setCreatedAt(testDateTime);
        assertEquals(testDateTime, customer.getCreatedAt());
        
        LocalDateTime anotherDateTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        customer.setCreatedAt(anotherDateTime);
        assertEquals(anotherDateTime, customer.getCreatedAt());
        
        customer.setCreatedAt(null);
        assertNull(customer.getCreatedAt());
    }
    
    @Test
    @DisplayName("Should set and get updated date")
    void testSetAndGetUpdatedAt() {
        customer.setUpdatedAt(testDateTime);
        assertEquals(testDateTime, customer.getUpdatedAt());
        
        LocalDateTime anotherDateTime = LocalDateTime.of(2023, 12, 31, 23, 59);
        customer.setUpdatedAt(anotherDateTime);
        assertEquals(anotherDateTime, customer.getUpdatedAt());
        
        customer.setUpdatedAt(null);
        assertNull(customer.getUpdatedAt());
    }
    
    @Test
    @DisplayName("Should validate customer with valid data")
    void testIsValidWithValidData() {
        customer.setAccountNumber("ACC001");
        customer.setName("John Doe");
        customer.setAddress("123 Main St");
        customer.setTelephone("555-1234");
        
        assertTrue(customer.isValid());
    }
    
    @Test
    @DisplayName("Should not validate customer with null account number")
    void testIsValidWithNullAccountNumber() {
        customer.setAccountNumber(null);
        customer.setName("John Doe");
        customer.setAddress("123 Main St");
        customer.setTelephone("555-1234");
        
        assertFalse(customer.isValid());
    }
    
    @Test
    @DisplayName("Should not validate customer with empty account number")
    void testIsValidWithEmptyAccountNumber() {
        customer.setAccountNumber("");
        customer.setName("John Doe");
        customer.setAddress("123 Main St");
        customer.setTelephone("555-1234");
        
        assertFalse(customer.isValid());
    }
    
    @Test
    @DisplayName("Should not validate customer with null name")
    void testIsValidWithNullName() {
        customer.setAccountNumber("ACC001");
        customer.setName(null);
        customer.setAddress("123 Main St");
        customer.setTelephone("555-1234");
        
        assertFalse(customer.isValid());
    }
    
    @Test
    @DisplayName("Should not validate customer with empty name")
    void testIsValidWithEmptyName() {
        customer.setAccountNumber("ACC001");
        customer.setName("");
        customer.setAddress("123 Main St");
        customer.setTelephone("555-1234");
        
        assertFalse(customer.isValid());
    }
    
    @Test
    @DisplayName("Should not validate customer with null address")
    void testIsValidWithNullAddress() {
        customer.setAccountNumber("ACC001");
        customer.setName("John Doe");
        customer.setAddress(null);
        customer.setTelephone("555-1234");
        
        assertFalse(customer.isValid());
    }
    
    @Test
    @DisplayName("Should not validate customer with empty address")
    void testIsValidWithEmptyAddress() {
        customer.setAccountNumber("ACC001");
        customer.setName("John Doe");
        customer.setAddress("");
        customer.setTelephone("555-1234");
        
        assertFalse(customer.isValid());
    }
    
    @Test
    @DisplayName("Should not validate customer with null telephone")
    void testIsValidWithNullTelephone() {
        customer.setAccountNumber("ACC001");
        customer.setName("John Doe");
        customer.setAddress("123 Main St");
        customer.setTelephone(null);
        
        assertFalse(customer.isValid());
    }
    
    @Test
    @DisplayName("Should not validate customer with empty telephone")
    void testIsValidWithEmptyTelephone() {
        customer.setAccountNumber("ACC001");
        customer.setName("John Doe");
        customer.setAddress("123 Main St");
        customer.setTelephone("");
        
        assertFalse(customer.isValid());
    }
    
    @Test
    @DisplayName("Should not validate customer with whitespace-only strings")
    void testIsValidWithWhitespaceOnlyStrings() {
        customer.setAccountNumber("   ");
        customer.setName("   ");
        customer.setAddress("   ");
        customer.setTelephone("   ");
        
        assertFalse(customer.isValid());
    }
    
    @Test
    @DisplayName("Should validate customer with trimmed whitespace")
    void testIsValidWithTrimmedWhitespace() {
        customer.setAccountNumber("  ACC001  ");
        customer.setName("  John Doe  ");
        customer.setAddress("  123 Main St  ");
        customer.setTelephone("  555-1234  ");
        
        assertTrue(customer.isValid());
    }
    
    @Test
    @DisplayName("Should generate correct display info")
    void testGetDisplayInfo() {
        customer.setName("John Doe");
        customer.setAccountNumber("ACC001");
        customer.setAddress("123 Main St");
        customer.setTelephone("555-1234");
        
        String expected = "Customer: John Doe (Account: ACC001, Address: 123 Main St, Phone: 555-1234)";
        assertEquals(expected, customer.getDisplayInfo());
    }
    
    @Test
    @DisplayName("Should handle edge case with very long strings")
    void testEdgeCaseWithLongStrings() {
        String longAccountNumber = "A".repeat(1000);
        String longName = "B".repeat(1000);
        String longAddress = "C".repeat(1000);
        String longTelephone = "D".repeat(1000);
        
        customer.setAccountNumber(longAccountNumber);
        customer.setName(longName);
        customer.setAddress(longAddress);
        customer.setTelephone(longTelephone);
        
        assertTrue(customer.isValid());
        assertEquals(longAccountNumber, customer.getAccountNumber());
        assertEquals(longName, customer.getName());
        assertEquals(longAddress, customer.getAddress());
        assertEquals(longTelephone, customer.getTelephone());
    }
    
    @Test
    @DisplayName("Should handle special characters in strings")
    void testSpecialCharactersInStrings() {
        customer.setAccountNumber("ACC-001_123");
        customer.setName("José María O'Connor-Smith");
        customer.setAddress("123 Main St., Apt. #4B, Floor 2");
        customer.setTelephone("+1-(555)-123-4567 ext. 123");
        
        assertTrue(customer.isValid());
        assertEquals("ACC-001_123", customer.getAccountNumber());
        assertEquals("José María O'Connor-Smith", customer.getName());
        assertEquals("123 Main St., Apt. #4B, Floor 2", customer.getAddress());
        assertEquals("+1-(555)-123-4567 ext. 123", customer.getTelephone());
    }
    
    @Test
    @DisplayName("Should handle numeric strings")
    void testNumericStrings() {
        customer.setAccountNumber("12345");
        customer.setName("12345");
        customer.setAddress("12345");
        customer.setTelephone("12345");
        
        assertTrue(customer.isValid());
        assertEquals("12345", customer.getAccountNumber());
        assertEquals("12345", customer.getName());
        assertEquals("12345", customer.getAddress());
        assertEquals("12345", customer.getTelephone());
    }
    
    @Test
    @DisplayName("Should validate customer with minimum valid data")
    void testIsValidWithMinimumValidData() {
        customer.setAccountNumber("A");
        customer.setName("B");
        customer.setAddress("C");
        customer.setTelephone("D");
        
        assertTrue(customer.isValid());
    }
}
