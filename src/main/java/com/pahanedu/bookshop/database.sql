-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 21, 2025 at 12:48 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pahan_edu_bookshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `id` int(11) NOT NULL,
  `bill_number` varchar(20) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `cashier_id` int(11) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `total_discount` decimal(10,2) DEFAULT 0.00,
  `total_amount` decimal(10,2) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`id`, `bill_number`, `customer_id`, `cashier_id`, `subtotal`, `total_discount`, `total_amount`, `created_at`) VALUES
(1, 'BIL000001', 2, 2, 137.99, 0.00, 137.99, '2025-07-24 15:29:48'),
(2, 'BIL000002', 4, 2, 135.49, 0.00, 135.49, '2025-08-17 15:54:47'),
(3, 'BIL000003', 5, 2, 113.24, 0.00, 113.24, '2025-08-17 18:13:18'),
(4, 'BIL000004', 7, 2, 201.75, 16.14, 185.61, '2025-08-18 13:52:01'),
(5, 'BIL000005', 8, 2, 364.45, 28.38, 336.08, '2025-08-18 14:08:15'),
(6, 'BIL000006', 11, 2, 430.21, 15.56, 414.65, '2025-08-19 06:37:04'),
(7, 'BIL000007', 12, 4, 368.00, 0.00, 368.00, '2025-08-19 16:29:01'),
(8, 'BIL000008', 5, 4, 269.00, 0.00, 269.00, '2025-08-19 19:59:45'),
(9, 'BIL000009', 7, 19, 531.50, 6.90, 524.60, '2025-08-20 09:48:23');

-- --------------------------------------------------------

--
-- Table structure for table `bill_items`
--

CREATE TABLE `bill_items` (
  `id` int(11) NOT NULL,
  `bill_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unit_price` decimal(10,2) NOT NULL,
  `discount` decimal(10,2) DEFAULT 0.00,
  `total_price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bill_items`
--

INSERT INTO `bill_items` (`id`, `bill_id`, `book_id`, `quantity`, `unit_price`, `discount`, `total_price`) VALUES
(1, 1, 1, 1, 45.99, 0.00, 45.99),
(2, 1, 5, 1, 92.00, 0.00, 92.00),
(3, 2, 2, 1, 89.50, 0.00, 89.50),
(4, 2, 1, 1, 45.99, 0.00, 45.99),
(5, 3, 4, 1, 67.25, 0.00, 67.25),
(6, 3, 1, 1, 45.99, 0.00, 45.99),
(7, 4, 4, 3, 67.25, 16.14, 185.61),
(8, 5, 4, 2, 67.25, 5.38, 129.12),
(9, 5, 1, 5, 45.99, 23.00, 206.96),
(10, 6, 4, 1, 67.25, 0.00, 67.25),
(11, 6, 2, 2, 89.50, 2.69, 176.32),
(12, 6, 1, 4, 45.99, 12.88, 171.08),
(13, 7, 5, 4, 92.00, 0.00, 368.00),
(14, 8, 4, 4, 67.25, 0.00, 269.00),
(15, 9, 5, 5, 92.00, 6.90, 453.10),
(16, 9, 3, 2, 35.75, 0.00, 71.50);

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `author` varchar(100) NOT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock_quantity` int(11) DEFAULT 0,
  `category` varchar(50) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`id`, `title`, `author`, `isbn`, `price`, `stock_quantity`, `category`, `created_at`, `updated_at`) VALUES
(1, 'Java Programming', 'Herbert Schildt', '978-0071808556', 45.99, 38, 'Programming', '2025-07-24 15:13:59', '2025-08-19 06:37:04'),
(2, 'Database Systems', 'Ramez Elmasri', '978-0133970777', 89.50, 27, 'Database', '2025-07-24 15:13:59', '2025-08-19 06:37:04'),
(3, 'Web Development', 'Jon Duckett', '978-1118008188', 35.75, 23, 'Web', '2025-07-24 15:13:59', '2025-08-20 09:48:24'),
(4, 'Data Structures', 'Michael Goodrich', '978-1118771334', 67.25, 29, 'Programming', '2025-07-24 15:13:59', '2025-08-19 19:59:45'),
(5, 'Software Engineering', 'Ian Sommerville', '978-0133943030', 92.00, 10, 'Engineering', '2025-07-24 15:13:59', '2025-08-20 09:48:24');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `account_number` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` text DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `account_number`, `name`, `address`, `telephone`, `created_at`, `updated_at`) VALUES
(1, 'ACC001', 'John Doe', '123 Main Street, Colombo', '0771234567', '2025-07-24 15:13:59', '2025-07-24 15:13:59'),
(2, 'ACC002', 'Jane Smith', '456 Galle Road, Kandy', '0777654321', '2025-07-24 15:13:59', '2025-07-24 15:13:59'),
(3, 'ACC003', 'Mike Johnson', '789 Temple Road, Negombo', '0712345678', '2025-07-24 15:13:59', '2025-07-24 15:13:59'),
(4, 'CUST000784', 'Godfrey K', '274 Gongithota, Wattala', '0760925351', '2025-08-17 15:53:20', '2025-08-17 15:53:20'),
(5, 'CUST967388', 'Ganga Lakmini', '546 Mabola, Wattala', '0771468006', '2025-08-17 17:49:27', '2025-08-17 17:49:27'),
(7, 'CUST004949', ' Kim Jeon', '567 Bambalapitiya', '0789456324', '2025-08-18 13:50:04', '2025-08-18 13:50:38'),
(8, 'CUST978938', 'Henry Lucas', '789/A Mabola, Wattala', '0789567488', '2025-08-18 14:06:18', '2025-08-18 14:06:57'),
(11, 'CUST361144', 'Ruwanga Sandeepani', '337 Horagolla, Ganemulla', '0725123959', '2025-08-19 06:36:01', '2025-08-19 06:36:01'),
(12, 'CUST656345', 'Thisavie', '67 Makola', '0778989994', '2025-08-19 16:24:16', '2025-08-19 16:24:16');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `user_type` enum('ADMIN','CASHIER') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `full_name`, `user_type`, `created_at`, `updated_at`) VALUES
(1, 'admin', 'admin123', 'System Administrator', 'ADMIN', '2025-07-24 15:13:59', '2025-07-24 15:13:59'),
(2, 'Lasanga', 'lasanga', 'Sandaruwani Lasanga', 'CASHIER', '2025-07-24 15:27:50', '2025-07-24 15:27:50'),
(3, 'Akila', 'akila123', 'Akila Dharshani', 'CASHIER', '2025-08-19 06:57:06', '2025-08-19 06:57:06'),
(4, 'Cheng', 'cheng345', 'Chi Cheng', 'CASHIER', '2025-08-19 16:02:45', '2025-08-19 16:02:45'),
(8, 'Eshini', 'eshini123', 'Maleesha Eshini', 'CASHIER', '2025-08-20 06:52:30', '2025-08-20 06:52:30'),
(10, 'Sandaruwani', 'sanda', 'Sandaruwani Lasanga', 'CASHIER', '2025-08-20 07:07:54', '2025-08-20 07:08:08'),
(15, 'Brake', 'Brake', 'Brake William', 'CASHIER', '2025-08-20 09:32:25', '2025-08-20 09:32:25'),
(17, 'Lucas', 'lucas', 'Lucas Phil', 'CASHIER', '2025-08-20 09:39:44', '2025-08-20 09:39:44'),
(19, 'Anne', 'anne', 'Anne Marie', 'CASHIER', '2025-08-20 09:44:54', '2025-08-20 09:44:54');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `bill_number` (`bill_number`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `cashier_id` (`cashier_id`);

--
-- Indexes for table `bill_items`
--
ALTER TABLE `bill_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `bill_id` (`bill_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `isbn` (`isbn`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `account_number` (`account_number`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `bill_items`
--
ALTER TABLE `bill_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `bills_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `bills_ibfk_2` FOREIGN KEY (`cashier_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `bill_items`
--
ALTER TABLE `bill_items`
  ADD CONSTRAINT `bill_items_ibfk_1` FOREIGN KEY (`bill_id`) REFERENCES `bills` (`id`),
  ADD CONSTRAINT `bill_items_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
