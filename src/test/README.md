# Bookshop System Unit Tests

This directory contains comprehensive unit tests for the Bookshop System using JUnit 5 and Mockito.

## Test Structure

### Model Tests
- **BookTest.java** - Tests for the Book model class
  - Constructor tests
  - Getter/setter tests
  - Validation logic tests
  - Edge case tests
  - Product interface implementation tests

- **CustomerTest.java** - Tests for the Customer model class
  - Constructor tests
  - Getter/setter tests
  - Validation logic tests
  - Edge case tests
  - Product interface implementation tests

### DAO Tests
- **BookDAOTest.java** - Tests for the BookDAO class
  - CRUD operation tests
  - Database error handling tests
  - Mock database connection tests
  - Stock update tests

- **CustomerDAOTest.java** - Tests for the CustomerDAO class
  - CRUD operation tests
  - Database error handling tests
  - Mock database connection tests

### Test Structure
The tests are organized into logical groups for easy execution.

## Running the Tests

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher
- JUnit 5 and Mockito dependencies (already added to pom.xml)

### Running Tests with Maven

1. **Run all tests:**
   ```bash
   mvn test
   ```

2. **Run specific test class:**
   ```bash
   mvn test -Dtest=BookTest
   mvn test -Dtest=CustomerTest
   mvn test -Dtest=BookDAOTest
   mvn test -Dtest=CustomerDAOTest
   ```

3. **Run tests with detailed output:**
   ```bash
   mvn test -Dtest=BookTest -Dsurefire.useFile=false
   ```

### Running Tests in IDE

Most IDEs (IntelliJ IDEA, Eclipse, VS Code) will automatically detect and run JUnit 5 tests:

1. Right-click on the test file or test method
2. Select "Run Test" or "Debug Test"
3. View results in the test runner window

## Test Coverage

### Book Model Tests
- ✅ Default constructor
- ✅ Parameterized constructor
- ✅ All getter/setter methods
- ✅ Validation logic (isValid method)
- ✅ Product interface implementation
- ✅ Display info generation
- ✅ Edge cases (null, empty, whitespace)
- ✅ Special characters and long strings

### Customer Model Tests
- ✅ Default constructor
- ✅ Parameterized constructor
- ✅ All getter/setter methods
- ✅ Validation logic (isValid method)
- ✅ Product interface implementation
- ✅ Display info generation
- ✅ Edge cases (null, empty, whitespace)
- ✅ Special characters and long strings

### BookDAO Tests
- ✅ Create book (success/failure scenarios)
- ✅ Get all books (with data/empty)
- ✅ Get book by ID (found/not found)
- ✅ Update book (success/failure scenarios)
- ✅ Delete book (success/failure scenarios)
- ✅ Update stock (success/insufficient quantity)
- ✅ Database error handling
- ✅ Connection failure handling

### CustomerDAO Tests
- ✅ Create customer (success/failure scenarios)
- ✅ Get all customers (with data/empty)
- ✅ Get customer by ID (found/not found)
- ✅ Update customer (success/failure scenarios)
- ✅ Delete customer (success/failure scenarios)
- ✅ Database error handling
- ✅ Connection failure handling
- ✅ Result set error handling

## Test Patterns Used

### Arrange-Act-Assert (AAA)
All tests follow the AAA pattern:
- **Arrange**: Set up test data and mocks
- **Act**: Execute the method under test
- **Assert**: Verify the expected results

### Mocking Strategy
- **DatabaseConnection**: Mocked using MockedStatic for static method calls
- **Connection, PreparedStatement, ResultSet**: Mocked using Mockito
- **SQL Exceptions**: Simulated to test error handling

### Comprehensive Test Coverage
- Tests both null and empty string scenarios separately
- Covers all edge cases with individual test methods

### Edge Case Testing
- Null values
- Empty strings
- Whitespace-only strings
- Very long strings
- Special characters
- Boundary values

## Best Practices Implemented

1. **Descriptive Test Names**: Using `@DisplayName` for readable test descriptions
2. **Comprehensive Coverage**: Testing both success and failure scenarios
3. **Proper Mocking**: Isolating units under test from external dependencies
4. **Error Handling**: Testing exception scenarios and error conditions
5. **Edge Cases**: Testing boundary conditions and unusual inputs
6. **Clean Setup**: Using `@BeforeEach` for test initialization
7. **Resource Management**: Proper cleanup of mocked static resources

## Adding New Tests

When adding new tests:

1. Follow the existing naming convention
2. Use descriptive test names with `@DisplayName`
3. Follow the AAA pattern
4. Test both positive and negative scenarios
5. Include edge cases and error conditions
6. Update this README if adding new test categories

## Troubleshooting

### Common Issues

1. **Mockito Version Conflicts**: Ensure Mockito version is compatible with JUnit 5
2. **Static Mocking Issues**: Use `MockedStatic` properly with try-with-resources
3. **Database Connection Errors**: Tests use mocked connections, not real database
4. **Test Discovery**: Ensure test classes are in the correct package structure

### Debug Mode
Run tests with debug output:
```bash
mvn test -X
```

### Test Reports
Maven Surefire generates test reports in `target/surefire-reports/` directory.
