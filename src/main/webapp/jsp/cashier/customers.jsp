<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanedu.bookshop.model.User" %>
<%@ page import="com.pahanedu.bookshop.model.Customer" %>
<%@ page import="java.util.List" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getUserType() != User.UserType.CASHIER) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Customers - Pahan Edu Book Shop</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
    <div class="header">
        <div class="container">
            <h1>Manage Customers</h1>
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
        <% if (request.getAttribute("success") != null) { %>
            <div class="alert alert-success">
                <%= request.getAttribute("success") %>
            </div>
        <% } %>
        
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        
        <div class="card">
            <div class="card-header">
                <h2 class="card-title">Add New Customer</h2>
            </div>
            
            <form action="<%=request.getContextPath()%>/cashier/customers" method="post">
                <input type="hidden" name="action" value="add">
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="accountNumber" class="form-label">Account Number:</label>
                        <input type="text" id="accountNumber" name="accountNumber" class="form-input" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="name" class="form-label">Name:</label>
                        <input type="text" id="name" name="name" class="form-input" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="address" class="form-label">Address:</label>
                    <textarea id="address" name="address" class="form-textarea" rows="3"></textarea>
                </div>
                
                <div class="form-group">
                    <label for="telephone" class="form-label">Telephone:</label>
                    <input type="text" id="telephone" name="telephone" class="form-input">
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn btn-success">Add Customer</button>
                </div>
            </form>
        </div>
        
        <div class="card">
            <div class="card-header">
                <h2 class="card-title">Customer List</h2>
            </div>
            
            <% if (customers != null && !customers.isEmpty()) { %>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Account Number</th>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Telephone</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Customer customer : customers) { %>
                            <tr>
                                <td><%= customer.getAccountNumber() %></td>
                                <td><%= customer.getName() %></td>
                                <td><%= customer.getAddress() != null ? customer.getAddress() : "" %></td>
                                <td><%= customer.getTelephone() != null ? customer.getTelephone() : "" %></td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/cashier/customers?action=edit&id=<%= customer.getId() %>" class="btn btn-warning">Edit</a>
                                    <a href="<%=request.getContextPath()%>/cashier/customers?action=delete&id=<%= customer.getId() %>" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this customer?')">Delete</a>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } else { %>
                <p>No customers found.</p>
            <% } %>
        </div>
    </div>
</body>
</html>