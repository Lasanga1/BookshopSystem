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
    <title>Admin Dashboard - Pahan Edu Book Shop</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
    <div class="header">
        <div class="container">
            <h1>Pahan Edu Book Shop - Admin Dashboard</h1>
            <p>Welcome, <%= user.getFullName() %></p>
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
        <div class="dashboard-grid">
            <div class="dashboard-card">
                <h3>Manage Books</h3>
                <p>Add, edit, and delete books from the inventory</p>
                <a href="<%=request.getContextPath()%>/admin/books" class="btn btn-primary">Go to Books</a>
            </div>
            
            <div class="dashboard-card">
                <h3>Manage Users</h3>
                <p>Add new cashiers and manage existing users</p>
                <a href="<%=request.getContextPath()%>/admin/users" class="btn btn-primary">Go to Users</a>
            </div>
            
            <div class="dashboard-card">
                <h3>View Bills</h3>
                <p>View all bills created by cashiers</p>
                <a href="<%=request.getContextPath()%>/admin/bills" class="btn btn-primary">Go to Bills</a>
            </div>
            
            <div class="dashboard-card">
                <h3>Help</h3>
                <p>Learn how to use the admin features</p>
                <a href="<%=request.getContextPath()%>/jsp/admin/help.jsp" class="btn btn-secondary">View Help</a>
            </div>
            
            
        </div>
    </div>
</body>
</html>