<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanedu.bookshop.model.User" %>
<%@ page import="com.pahanedu.bookshop.model.Bill" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getUserType() != User.UserType.CASHIER) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    List<Bill> bills = (List<Bill>) request.getAttribute("bills");
    String success = request.getParameter("success");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Bills - Pahan Edu Book Shop</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
    <div class="header">
        <div class="container">
            <h1>Bills</h1>
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
        <% if (success != null) { %>
            <div class="alert alert-success">
                <%= success %>
            </div>
        <% } %>
        
        <div class="card">
            <div class="card-header">
                <h2 class="card-title">Bills List</h2>
                <a href="<%=request.getContextPath()%>/cashier/bills?action=create" class="btn btn-success">Create New Bill</a>
            </div>
            
            <% if (bills != null && !bills.isEmpty()) { %>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Bill Number</th>
                            <th>Customer</th>
                            <th>Total Amount</th>
                            <th>Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Bill bill : bills) { %>
                            <tr>
                                <td><%= bill.getBillNumber() %></td>
                                <td><%= bill.getCustomer().getName() %></td>
                                <td>Rs. <%= bill.getTotalAmount() %></td>
                                <td><%= bill.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) %></td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/cashier/bills?action=view&id=<%= bill.getId() %>" class="btn btn-primary">View</a>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } else { %>
                <p>No bills found.</p>
            <% } %>
        </div>
    </div>
</body>
</html>