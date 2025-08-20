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
    <title>Cashier Help - Pahan Edu Book Shop</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
    <div class="header">
        <div class="container">
            <h1>Cashier Help Guide</h1>
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
                <h2 class="card-title">How to Use Cashier Features</h2>
            </div>
            
            <div class="help-section">
                <div class="help-item">
                    <h3>Managing Customers</h3>
                    <p>Add and manage customer information for billing:</p>
                    <ul class="help-steps">
                        <li>Navigate to "Manage Customers" from the dashboard</li>
                        <li>Fill the form to add new customers</li>
                        <li>Account Number and Name are required fields</li>
                        <li>Address and Telephone are optional but recommended</li>
                        <li>Use unique account numbers for each customer</li>
                        <li>Edit customer details when needed using the "Edit" button</li>
                        <li>Delete customers only if they have no associated bills</li>
                    </ul>
                </div>
                
                <div class="help-item">
                    <h3>Creating Bills</h3>
                    <p>Create sales bills for customers:</p>
                    <ul class="help-steps">
                        <li>Click "Create Bill" from dashboard or navigation</li>
                        <li>Select a customer from the dropdown</li>
                        <li>Click "Add Item" to add books to the bill</li>
                        <li>For each item: select book, enter quantity, apply discount if needed</li>
                        <li>The system automatically calculates item totals and grand total</li>
                        <li>Check stock availability before adding large quantities</li>
                        <li>Click "Create Bill" to save and automatically print</li>
                        <li>The bill will be automatically numbered and dated</li>
                    </ul>
                </div>
                
                <div class="help-item">
                    <h3>Viewing and Printing Bills</h3>
                    <p>Access and print previous bills:</p>
                    <ul class="help-steps">
                        <li>Navigate to "View Bills" to see all bills</li>
                        <li>Click "View" on any bill to see detailed information</li>
                        <li>Use the "Print" button to print bills</li>
                        <li>Bills show all item details, customer info, and totals</li>
                        <li>Recent bills appear at the top of the list</li>
                    </ul>
                </div>
                
                <div class="help-item">
                    <h3>Working with Discounts</h3>
                    <p>How to apply discounts correctly:</p>
                    <ul class="help-steps">
                        <li>Discounts are entered as percentages (0-100)</li>
                        <li>Discount applies to individual item totals</li>
                        <li>Example: 10% discount on Rs. 100 item = Rs. 90 final price</li>
                        <li>Use 0 for no discount (default)</li>
                        <li>Be careful with discount amounts - they cannot exceed 100%</li>
                    </ul>
                </div>
                
                <div class="help-item">
                    <h3>Tips for Efficient Operation</h3>
                    <ul class="help-steps">
                        <li>Always verify customer details before creating bills</li>
                        <li>Check book stock before promising availability to customers</li>
                        <li>Double-check quantities and discounts before submitting</li>
                        <li>Print bills immediately after creation for customer records</li>
                        <li>Keep customer information updated for better service</li>
                        <li>Logout when stepping away from the system</li>
                    </ul>
                </div>
                
                <div class="help-item">
                    <h3>Troubleshooting</h3>
                    <ul class="help-steps">
                        <li>If a book is out of stock, reduce quantity or contact admin</li>
                        <li>If customer dropdown is empty, add customers first</li>
                        <li>If bill creation fails, check all required fields</li>
                        <li>For system issues, contact the administrator</li>
                        <li>Always save your work before navigating away</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>