<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanedu.bookshop.model.User" %>
<%@ page import="com.pahanedu.bookshop.model.Book" %>
<%@ page import="java.util.List" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getUserType() != User.UserType.ADMIN) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    List<Book> books = (List<Book>) request.getAttribute("books");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Books - Pahan Edu Book Shop</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
    <div class="header">
        <div class="container">
            <h1>Manage Books</h1>
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
                <h2 class="card-title">Add New Book</h2>
            </div>
            
            <form action="<%=request.getContextPath()%>/admin/books" method="post">
                <input type="hidden" name="action" value="add">
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="title" class="form-label">Title:</label>
                        <input type="text" id="title" name="title" class="form-input" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="author" class="form-label">Author:</label>
                        <input type="text" id="author" name="author" class="form-input" required>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="isbn" class="form-label">ISBN:</label>
                        <input type="text" id="isbn" name="isbn" class="form-input">
                    </div>
                    
                    <div class="form-group">
                        <label for="category" class="form-label">Category:</label>
                        <input type="text" id="category" name="category" class="form-input">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="price" class="form-label">Price:</label>
                        <input type="number" id="price" name="price" class="form-input" step="0.01" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="stockQuantity" class="form-label">Stock Quantity:</label>
                        <input type="number" id="stockQuantity" name="stockQuantity" class="form-input" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn btn-success">Add Book</button>
                </div>
            </form>
        </div>
        
        <div class="card">
            <div class="card-header">
                <h2 class="card-title">Books List</h2>
            </div>
            
            <% if (books != null && !books.isEmpty()) { %>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Author</th>
                            <th>ISBN</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Stock</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Book book : books) { %>
                            <tr>
                                <td><%= book.getTitle() %></td>
                                <td><%= book.getAuthor() %></td>
                                <td><%= book.getIsbn() != null ? book.getIsbn() : "" %></td>
                                <td><%= book.getCategory() != null ? book.getCategory() : "" %></td>
                                <td>Rs. <%= book.getPrice() %></td>
                                <td><%= book.getStockQuantity() %></td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/admin/books?action=edit&id=<%= book.getId() %>" class="btn btn-warning">Edit</a>
                                    <a href="<%=request.getContextPath()%>/admin/books?action=delete&id=<%= book.getId() %>" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this book?')">Delete</a>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } else { %>
                <p>No books found.</p>
            <% } %>
        </div>
    </div>
</body>
</html>