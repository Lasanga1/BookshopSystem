<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanedu.bookshop.model.User" %>
<%@ page import="com.pahanedu.bookshop.model.Customer" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getUserType() != User.UserType.CASHIER) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    Customer customer = (Customer) request.getAttribute("customer");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Customer - Pahan Edu Book Shop</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
    <div class="header">
        <div class="container">
            <h1>Edit Customer</h1>
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
        <div class="card">
            <div class="card-header">
                <h2 class="card-title">Edit Customer Details</h2>
            </div>
            
            <form action="<%=request.getContextPath()%>/cashier/customers" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= customer.getId() %>">
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="accountNumber" class="form-label">Account Number:</label>
                        <input type="text" id="accountNumber" name="accountNumber" class="form-input" value="<%= customer.getAccountNumber() %>" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="name" class="form-label">Name:</label>
                        <input type="text" id="name" name="name" class="form-input" value="<%= customer.getName() %>" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="address" class="form-label">Address:</label>
                    <textarea id="address" name="address" class="form-textarea" rows="3"><%= customer.getAddress() != null ? customer.getAddress() : "" %></textarea>
                </div>
                
                <div class="form-group">
                    <label for="telephone" class="form-label">Telephone:</label>
                    <input type="text" id="telephone" name="telephone" class="form-input" value="<%= customer.getTelephone() != null ? customer.getTelephone() : "" %>">
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn btn-success">Update Customer</button>
                    <a href="<%=request.getContextPath()%>/cashier/customers" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>