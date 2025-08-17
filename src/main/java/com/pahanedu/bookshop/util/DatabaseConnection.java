package com.pahanedu.bookshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private static final String URL = "jdbc:mysql://localhost:3306/pahan_edu_bookshop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    // Private constructor to prevent instantiation
    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found", e);
        }
    }
    
    // Public static method to get the singleton instance
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}