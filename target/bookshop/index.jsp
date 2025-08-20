<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanedu.bookshop.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user != null) {
        if (user.getUserType() == User.UserType.ADMIN) {
            response.sendRedirect("jsp/admin/dashboard.jsp");
        } else {
            response.sendRedirect("jsp/cashier/dashboard.jsp");
        }
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pahan Edu Book Shop - Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="login-container">
        <div class="login-card">
            <div class="login-title">
                <h1>Pahan Edu Book Shop</h1>
                <p>Management System</p>
            </div>
            
            <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>
            
            <form action="login" method="post">
                <div class="form-group">
                    <label for="username" class="form-label">Username:</label>
                    <input type="text" id="username" name="username" class="form-input" required>
                </div>
                
                <div class="form-group">
                    <label for="password" class="form-label">Password:</label>
                    <input type="password" id="password" name="password" class="form-input" required>
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn btn-primary" style="width: 100%;">Login</button>
                </div>
            </form>
            
            <div style="text-align: center; margin-top: 20px; font-size: 14px; color: #7f8c8d;">
                <p>Default Admin Login:</p>
                <p><strong>Username:</strong>admin</p>
                <p><strong>Password:</strong>admin123</p>
            </div>
        </div>
    </div>
</body>
</html>