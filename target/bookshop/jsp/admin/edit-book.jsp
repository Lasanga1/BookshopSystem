<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanedu.bookshop.model.User" %>
<%@ page import="com.pahanedu.bookshop.model.Book" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getUserType() != User.UserType.ADMIN) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    Book book = (Book) request.getAttribute("book");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Book - Pahan Edu Book Shop</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
    <div class="header">
        <div class="container">
            <h1>Edit Book</h1>
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
                <h2 class="card-title">Edit Book Details</h2>
            </div>
            
            <form action="<%=request.getContextPath()%>/admin/books" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= book.getId() %>">
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="title" class="form-label">Title:</label>
                        <input type="text" id="title" name="title" class="form-input" value="<%= book.getTitle() %>" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="author" class="form-label">Author:</label>
                        <input type="text" id="author" name="author" class="form-input" value="<%= book.getAuthor() %>" required>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="isbn" class="form-label">ISBN:</label>
                        <input type="text" id="isbn" name="isbn" class="form-input" value="<%= book.getIsbn() != null ? book.getIsbn() : "" %>">
                    </div>
                    
                    <div class="form-group">
                        <label for="category" class="form-label">Category:</label>
                        <input type="text" id="category" name="category" class="form-input" value="<%= book.getCategory() != null ? book.getCategory() : "" %>">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="price" class="form-label">Price:</label>
                        <input type="number" id="price" name="price" class="form-input" step="0.01" value="<%= book.getPrice() %>" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="stockQuantity" class="form-label">Stock Quantity:</label>
                        <input type="number" id="stockQuantity" name="stockQuantity" class="form-input" value="<%= book.getStockQuantity() %>" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn btn-success">Update Book</button>
                    <a href="<%=request.getContextPath()%>/admin/books" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>