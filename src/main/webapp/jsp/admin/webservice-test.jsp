<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Web Service Test - Book Shop Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Web Service Test Page</h1>
        <p>This page demonstrates the web services working alongside your existing application.</p>
        
        <div class="section">
            <h2>Book Web Services</h2>
            <div class="button-group">
                <button onclick="testBookService('/api/books/all')">Get All Books</button>
                <button onclick="testBookService('/api/books/count')">Get Book Count</button>
                <button onclick="testBookService('/api/books/1')">Get Book ID 1</button>
                <button onclick="testBookService('/api/books/1/stock?quantity=5')">Check Stock</button>
                <button onclick="testBookService('/api/books/category/Fiction')">Get Fiction Books</button>
            </div>
        </div>
        
        <div class="section">
            <h2>Customer Web Services</h2>
            <div class="button-group">
                <button onclick="testCustomerService('/api/customers/all')">Get All Customers</button>
                <button onclick="testCustomerService('/api/customers/count')">Get Customer Count</button>
                <button onclick="testCustomerService('/api/customers/1')">Get Customer ID 1</button>
                <button onclick="testCustomerService('/api/customers/1/exists')">Check Customer Exists</button>
            </div>
        </div>
        
        <div class="section">
            <h2>User Web Services</h2>
            <div class="button-group">
                <button onclick="testUserService('/api/users/all')">Get All Users</button>
                <button onclick="testUserService('/api/users/count')">Get User Count</button>
                <button onclick="testUserService('/api/users/1')">Get User ID 1</button>
                <button onclick="testUserService('/api/users/type/ADMIN')">Get Admin Users</button>
            </div>
        </div>
        
        <div class="section">
            <h2>Test Results</h2>
            <div id="results" class="results-box">
                <p>Click any button above to test the web services...</p>
            </div>
        </div>
        
        <div class="section">
            <a href="${pageContext.request.contextPath}/jsp/admin/dashboard.jsp" class="btn">Back to Dashboard</a>
        </div>
    </div>
    
    <script>
        function testBookService(url) {
            fetch(url)
                .then(response => response.text())
                .then(data => {
                    document.getElementById('results').innerHTML = '<h3>Book Service Response:</h3><pre>' + data + '</pre>';
                })
                .catch(error => {
                    document.getElementById('results').innerHTML = '<h3>Error:</h3><pre>' + error + '</pre>';
                });
        }
        
        function testCustomerService(url) {
            fetch(url)
                .then(response => response.text())
                .then(data => {
                    document.getElementById('results').innerHTML = '<h3>Customer Service Response:</h3><pre>' + data + '</pre>';
                })
                .catch(error => {
                    document.getElementById('results').innerHTML = '<h3>Error:</h3><pre>' + error + '</pre>';
                });
        }
        
        function testUserService(url) {
            fetch(url)
                .then(response => response.text())
                .then(data => {
                    document.getElementById('results').innerHTML = '<h3>User Service Response:</h3><pre>' + data + '</pre>';
                })
                .catch(error => {
                    document.getElementById('results').innerHTML = '<h3>Error:</h3><pre>' + error + '</pre>';
                });
        }
    </script>
</body>
</html>
