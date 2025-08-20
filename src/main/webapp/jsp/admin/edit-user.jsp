<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanedu.bookshop.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getUserType() != User.UserType.ADMIN) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    User editUser = (User) request.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User - Pahan Edu Book Shop</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
    <div class="header">
        <div class="container">
            <h1>Edit User</h1>
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
                <h2 class="card-title">Edit User Details</h2>
            </div>
            
            <form action="<%=request.getContextPath()%>/admin/users" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= editUser.getId() %>">
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="username" class="form-label">Username:</label>
                        <input type="text" id="username" name="username" class="form-input" value="<%= editUser.getUsername() %>" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="fullName" class="form-label">Full Name:</label>
                        <input type="text" id="fullName" name="fullName" class="form-input" value="<%= editUser.getFullName() %>" required>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="password" class="form-label">Password (leave blank to keep unchanged):</label>
                        <input type="password" id="password" name="password" class="form-input">
                    </div>
                    
                    <div class="form-group">
                        <label for="userType" class="form-label">User Type:</label>
                        <select id="userType" name="userType" class="form-select" required>
                            <option value="ADMIN" <%= editUser.getUserType() == User.UserType.ADMIN ? "selected" : "" %>>Admin</option>
                            <option value="CASHIER" <%= editUser.getUserType() == User.UserType.CASHIER ? "selected" : "" %>>Cashier</option>
                        </select>
                    </div>
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn btn-success">Update User</button>
                    <a href="<%=request.getContextPath()%>/admin/users" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>