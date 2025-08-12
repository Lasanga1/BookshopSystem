-- Create database
CREATE DATABASE IF NOT EXISTS pahan_edu_bookshop;
USE pahan_edu_bookshop;

-- Users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    user_type ENUM('ADMIN', 'CASHIER') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Books table
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    price DECIMAL(10,2) NOT NULL,
    stock_quantity INT DEFAULT 0,
    category VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Customers table
CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    address TEXT,
    telephone VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bills table
CREATE TABLE bills (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bill_number VARCHAR(20) UNIQUE NOT NULL,
    customer_id INT NOT NULL,
    cashier_id INT NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    total_discount DECIMAL(10,2) DEFAULT 0.00,
    total_amount DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (cashier_id) REFERENCES users(id)
);

-- Bill Items table
CREATE TABLE bill_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bill_id INT NOT NULL,
    book_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    discount DECIMAL(10,2) DEFAULT 0.00,
    total_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (bill_id) REFERENCES bills(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);

-- Insert default admin user (password: admin123)
INSERT INTO users (username, password, full_name, user_type) VALUES 
('admin', 'admin123', 'System Administrator', 'ADMIN');

-- Insert sample books
INSERT INTO books (title, author, isbn, price, stock_quantity, category) VALUES
('Java Programming', 'Herbert Schildt', '978-0071808556', 45.99, 50, 'Programming'),
('Database Systems', 'Ramez Elmasri', '978-0133970777', 89.50, 30, 'Database'),
('Web Development', 'Jon Duckett', '978-1118008188', 35.75, 25, 'Web'),
('Data Structures', 'Michael Goodrich', '978-1118771334', 67.25, 40, 'Programming'),
('Software Engineering', 'Ian Sommerville', '978-0133943030', 92.00, 20, 'Engineering');

-- Insert sample customers
INSERT INTO customers (account_number, name, address, telephone) VALUES
('ACC001', 'John Doe', '123 Main Street, Colombo', '0771234567'),
('ACC002', 'Jane Smith', '456 Galle Road, Kandy', '0777654321'),
('ACC003', 'Mike Johnson', '789 Temple Road, Negombo', '0712345678');