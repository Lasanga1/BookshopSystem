<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanedu.bookshop.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getUserType() != User.UserType.CASHIER) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cashier Dashboard - Pahan Edu Book Shop</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
    <div class="header">
        <div class="container">
            <h1>Pahan Edu Book Shop - Cashier Dashboard</h1>
            <p>Welcome, <%= user.getFullName() %></p>
        </div>
    </div>
    
    <nav class="nav">
        <div class="container">
            <ul>
                <li><a href="<%=request.getContextPath()%>/jsp/cashier/dashboard.jsp">Dashboard</a></li>
                <li><a href="<%=request.getContextPath()%>/cashier/customers">Manage Customers</a></li>
                <li><a href="<%=request.getContextPath()%>/cashier/bills?action=create">Create Bill</a></li>
                <li><a href="<%=request.getContextPath()%>/cashier/bills">View Bills</a></li>
                <li><a href="<%=request.getContextPath()%>/jsp/cashier/help.jsp">Help</a></li>
                <li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
            </ul>
        </div>
    </nav>
    
    <div class="container">
        <div class="dashboard-grid">
            <div class="dashboard-card">
                <h3>Manage Customers</h3>
                <p>Add, edit, and view customer information</p>
                <a href="<%=request.getContextPath()%>/cashier/customers" class="btn btn-primary">Go to Customers</a>
            </div>
            
            <div class="dashboard-card">
                <h3>Create New Bill</h3>
                <p>Create a new sales bill for customers</p>
                <a href="<%=request.getContextPath()%>/cashier/bills?action=create" class="btn btn-success">Create Bill</a>
            </div>
            
            <div class="dashboard-card">
                <h3>View Bills</h3>
                <p>View and print previous bills</p>
                <a href="<%=request.getContextPath()%>/cashier/bills" class="btn btn-primary">Go to Bills</a>
            </div>
            
            <div class="dashboard-card">
                <h3>Help</h3>
                <p>Learn how to use the cashier features</p>
                <a href="<%=request.getContextPath()%>/jsp/cashier/help.jsp" class="btn btn-secondary">View Help</a>
            </div>
        </div>
    </div>
</body>
</html>