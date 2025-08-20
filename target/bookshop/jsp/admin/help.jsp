<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanedu.bookshop.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getUserType() != User.UserType.ADMIN) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Help - Pahan Edu Book Shop</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
    <div class="header">
        <div class="container">
            <h1>Admin Help Guide</h1>
        </div>
    </div>
    
    <nav class="nav">
        <div class="container">
            <ul>
                <li><a href="<%=request.getContextPath()%>/jsp/admin/dashboard.jsp">Dashboard</a></li>
                <li><a href="<%=request.getContextPath()%>/admin/books">Manage Books</a></li>
                <li><a href="<%=request.getContextPath()%>/admin/users">Manage Users</a></li>
                <li><a href="<%=request.getContextPath()%>/admin/bills">View Bills</a></li>
                <li><a href="<%=request.getContextPath()%>/jsp/admin/help.jsp">Help</a></li>
                <li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
            </ul>
        </div>
    </nav>
    
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2 class="card-title">How to Use Admin Features</h2>
            </div>
            
            <div class="help-section">
                <div class="help-item">
                    <h3>Managing Books</h3>
                    <p>As an admin, you can manage the book inventory:</p>
                    <ul class="help-steps">
                        <li>Navigate to "Manage Books" from the dashboard</li>
                        <li>Use the form at the top to add new books</li>
                        <li>Fill in all required fields: Title, Author, Price, and Stock Quantity</li>
                        <li>ISBN and Category are optional but recommended</li>
                        <li>Click "Add Book" to save the new book</li>
                        <li>Use "Edit" button to modify existing books</li>
                        <li>Use "Delete" button to remove books (confirm the action)</li>
                    </ul>
                </div>
                
                <div class="help-item">
                    <h3>Managing Users</h3>
                    <p>Create and manage cashier accounts and other admin accounts:</p>
                    <ul class="help-steps">
                        <li>Navigate to "Manage Users" from the dashboard</li>
                        <li>Fill the form to create new users</li>
                        <li>Choose user type: ADMIN or CASHIER</li>
                        <li>Enter a strong password for new users</li>
                        <li>Users will use their username and password to login</li>
                        <li>You can edit user details or delete users as needed</li>
                    </ul>
                </div>
                
                <div class="help-item">
                    <h3>Viewing Bills</h3>
                    <p>Monitor all sales transactions created by cashiers:</p>
                    <ul class="help-steps">
                        <li>Navigate to "View Bills" to see all bills</li>
                        <li>Click "View" on any bill to see detailed information</li>
                        <li>Bills show customer details, items purchased, and totals</li>
                        <li>You can print any bill for record keeping</li>
                        <li>Bills are automatically numbered and dated</li>
                    </ul>
                </div>
                
                <div class="help-item">
                    <h3>System Security</h3>
                    <p>Important security considerations:</p>
                    <ul class="help-steps">
                        <li>Always use strong passwords for user accounts</li>
                        <li>Passwords are automatically encrypted in the system</li>
                        <li>Regular logout when not using the system</li>
                        <li>Only create necessary user accounts</li>
                        <li>Monitor bill creation for unusual activity</li>
                    </ul>
                </div>
                
                <div class="help-item">
                    <h3>Tips for Better Management</h3>
                    <ul class="help-steps">
                        <li>Keep book information updated and accurate</li>
                        <li>Monitor stock levels regularly</li>
                        <li>Review bills periodically for business insights</li>
                        <li>Train cashiers properly on system usage</li>
                        <li>Backup your data regularly</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>