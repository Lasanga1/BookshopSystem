package com.pahanedu.bookshop.dao;

import com.pahanedu.bookshop.model.Bill;
import com.pahanedu.bookshop.model.BillItem;
import com.pahanedu.bookshop.model.Book;
import com.pahanedu.bookshop.model.Customer;
import com.pahanedu.bookshop.model.User;
import com.pahanedu.bookshop.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BillDAO {
    
    public boolean createBill(Bill bill, List<BillItem> billItems) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            conn.setAutoCommit(false);
            
            // Insert bill
            String billSql = "INSERT INTO bills (bill_number, customer_id, cashier_id, subtotal, total_discount, total_amount) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement billStmt = conn.prepareStatement(billSql, Statement.RETURN_GENERATED_KEYS);
            billStmt.setString(1, bill.getBillNumber());
            billStmt.setInt(2, bill.getCustomerId());
            billStmt.setInt(3, bill.getCashierId());
            billStmt.setBigDecimal(4, bill.getSubtotal());
            billStmt.setBigDecimal(5, bill.getTotalDiscount());
            billStmt.setBigDecimal(6, bill.getTotalAmount());
            
            billStmt.executeUpdate();
            
            ResultSet rs = billStmt.getGeneratedKeys();
            int billId = 0;
            if (rs.next()) {
                billId = rs.getInt(1);
            }
            
            // Insert bill items
            String itemSql = "INSERT INTO bill_items (bill_id, book_id, quantity, unit_price, discount, total_price) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement itemStmt = conn.prepareStatement(itemSql);
            
            for (BillItem item : billItems) {
                itemStmt.setInt(1, billId);
                itemStmt.setInt(2, item.getBookId());
                itemStmt.setInt(3, item.getQuantity());
                itemStmt.setBigDecimal(4, item.getUnitPrice());
                itemStmt.setBigDecimal(5, item.getDiscount());
                itemStmt.setBigDecimal(6, item.getTotalPrice());
                itemStmt.addBatch();
                
                // Update book stock
                String stockSql = "UPDATE books SET stock_quantity = stock_quantity - ? WHERE id = ?";
                PreparedStatement stockStmt = conn.prepareStatement(stockSql);
                stockStmt.setInt(1, item.getQuantity());
                stockStmt.setInt(2, item.getBookId());
                stockStmt.executeUpdate();
            }
            
            itemStmt.executeBatch();
            conn.commit();
            return true;
            
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT b.*, c.name as customer_name, u.full_name as cashier_name " +
                    "FROM bills b " +
                    "JOIN customers c ON b.customer_id = c.id " +
                    "JOIN users u ON b.cashier_id = u.id " +
                    "ORDER BY b.created_at DESC";
        
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillNumber(rs.getString("bill_number"));
                bill.setCustomerId(rs.getInt("customer_id"));
                bill.setCashierId(rs.getInt("cashier_id"));
                bill.setSubtotal(rs.getBigDecimal("subtotal"));
                bill.setTotalDiscount(rs.getBigDecimal("total_discount"));
                bill.setTotalAmount(rs.getBigDecimal("total_amount"));
                bill.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                
                // Set customer info
                Customer customer = new Customer();
                customer.setName(rs.getString("customer_name"));
                bill.setCustomer(customer);
                
                // Set cashier info
                User cashier = new User();
                cashier.setFullName(rs.getString("cashier_name"));
                bill.setCashier(cashier);
                
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
    
    public Bill getBillById(int id) {
        String sql = "SELECT b.*, c.account_number, c.name as customer_name, c.address, c.telephone, " +
                    "u.full_name as cashier_name " +
                    "FROM bills b " +
                    "JOIN customers c ON b.customer_id = c.id " +
                    "JOIN users u ON b.cashier_id = u.id " +
                    "WHERE b.id = ?";
        
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillNumber(rs.getString("bill_number"));
                bill.setCustomerId(rs.getInt("customer_id"));
                bill.setCashierId(rs.getInt("cashier_id"));
                bill.setSubtotal(rs.getBigDecimal("subtotal"));
                bill.setTotalDiscount(rs.getBigDecimal("total_discount"));
                bill.setTotalAmount(rs.getBigDecimal("total_amount"));
                bill.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                
                // Set customer info
                Customer customer = new Customer();
                customer.setAccountNumber(rs.getString("account_number"));
                customer.setName(rs.getString("customer_name"));
                customer.setAddress(rs.getString("address"));
                customer.setTelephone(rs.getString("telephone"));
                bill.setCustomer(customer);
                
                // Set cashier info
                User cashier = new User();
                cashier.setFullName(rs.getString("cashier_name"));
                bill.setCashier(cashier);
                
                // Get bill items
                bill.setBillItems(getBillItemsByBillId(id));
                
                return bill;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<BillItem> getBillItemsByBillId(int billId) {
        List<BillItem> items = new ArrayList<>();
        String sql = "SELECT bi.*, b.title, b.author " +
                    "FROM bill_items bi " +
                    "JOIN books b ON bi.book_id = b.id " +
                    "WHERE bi.bill_id = ?";
        
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, billId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                BillItem item = new BillItem();
                item.setId(rs.getInt("id"));
                item.setBillId(rs.getInt("bill_id"));
                item.setBookId(rs.getInt("book_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getBigDecimal("unit_price"));
                item.setDiscount(rs.getBigDecimal("discount"));
                item.setTotalPrice(rs.getBigDecimal("total_price"));
                
                // Set book info
                Book book = new Book();
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                item.setBook(book);
                
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
    
    public String generateBillNumber() {
        String sql = "SELECT MAX(CAST(SUBSTRING(bill_number, 4) AS UNSIGNED)) as max_num FROM bills WHERE bill_number LIKE 'BIL%'";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            int nextNum = 1;
            if (rs.next()) {
                nextNum = rs.getInt("max_num") + 1;
            }
            return String.format("BIL%06d", nextNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "BIL000001";
    }
}