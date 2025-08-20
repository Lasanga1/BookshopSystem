<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanedu.bookshop.model.User" %>
<%@ page import="com.pahanedu.bookshop.model.Bill" %>
<%@ page import="com.pahanedu.bookshop.model.BillItem" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    
    Bill bill = (Bill) request.getAttribute("bill");
    if (bill == null) {
        response.sendRedirect(request.getContextPath() + "/");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bill Details - <%= bill.getBillNumber() %></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <script>
        function printBill() {
            window.print();
        }
    </script>
</head>
<body>
    <div class="header no-print">
        <div class="container">
            <h1>Bill Details</h1>
        </div>
    </div>
    
    <nav class="nav no-print">
        <div class="container">
            <ul>
                <% if (user.getUserType() == User.UserType.ADMIN) { %>
                    <li><a href="<%=request.getContextPath()%>/jsp/admin/dashboard.jsp">Dashboard</a></li>
                    <li><a href="<%=request.getContextPath()%>/admin/bills">Back to Bills</a></li>
                <% } else { %>
                    <li><a href="<%=request.getContextPath()%>/jsp/cashier/dashboard.jsp">Dashboard</a></li>
                    <li><a href="<%=request.getContextPath()%>/cashier/bills">Back to Bills</a></li>
                <% } %>
                <li><a href="javascript:printBill()">Print Bill</a></li>
                <li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
            </ul>
        </div>
    </nav>
    
    <div class="container">
        <div class="card">
            <div class="bill-header">
                <h1>Pahan Edu Book Shop</h1>
                <h2>SALES BILL</h2>
                <p><strong>Bill Number:</strong> <%= bill.getBillNumber() %></p>
                <p><strong>Date:</strong> <%= bill.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) %></p>
            </div>
            
            <div class="bill-info">
                <div>
                    <h3>Customer Details</h3>
                    <p><strong>Account Number:</strong> <%= bill.getCustomer().getAccountNumber() %></p>
                    <p><strong>Name:</strong> <%= bill.getCustomer().getName() %></p>
                    <% if (bill.getCustomer().getAddress() != null && !bill.getCustomer().getAddress().trim().isEmpty()) { %>
                        <p><strong>Address:</strong> <%= bill.getCustomer().getAddress() %></p>
                    <% } %>
                    <% if (bill.getCustomer().getTelephone() != null && !bill.getCustomer().getTelephone().trim().isEmpty()) { %>
                        <p><strong>Telephone:</strong> <%= bill.getCustomer().getTelephone() %></p>
                    <% } %>
                </div>
                
                <div>
                    <h3>Bill Details</h3>
                    <p><strong>Cashier:</strong> <%= bill.getCashier().getFullName() %></p>
                    <p><strong>Items Count:</strong> <%= bill.getBillItems().size() %></p>
                </div>
            </div>
            
            <div class="bill-items">
                <h3>Items</h3>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Book Title</th>
                            <th>Author</th>
                            <th>Unit Price</th>
                            <th>Quantity</th>
                            <th>Subtotal</th>
                            <th>Discount</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (BillItem item : bill.getBillItems()) { %>
                            <tr>
                                <td><%= item.getBook().getTitle() %></td>
                                <td><%= item.getBook().getAuthor() %></td>
                                <td>Rs. <%= item.getUnitPrice() %></td>
                                <td><%= item.getQuantity() %></td>
                                <td>Rs. <%= item.getUnitPrice().multiply(new java.math.BigDecimal(item.getQuantity())) %></td>
                                <td>Rs. <%= item.getDiscount() %></td>
                                <td>Rs. <%= item.getTotalPrice() %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
            
            <div class="bill-total">
                <div class="total-row">
                    <span>Subtotal:</span>
                    <span>Rs. <%= bill.getSubtotal() %></span>
                </div>
                <div class="total-row">
                    <span>Total Discount:</span>
                    <span>Rs. <%= bill.getTotalDiscount() %></span>
                </div>
                <div class="total-row final-total">
                    <span>Grand Total:</span>
                    <span>Rs. <%= bill.getTotalAmount() %></span>
                </div>
            </div>
            
            <div style="text-align: center; margin-top: 30px; font-size: 14px;">
                <p>Thank you for your business!</p>
                <p>Visit us again for more books and educational materials.</p>
            </div>
        </div>
        
        <div class="no-print" style="text-align: center; margin: 20px 0;">
            <button onclick="printBill()" class="btn btn-primary">Print Bill</button>
            <% if (user.getUserType() == User.UserType.ADMIN) { %>
                <a href="<%=request.getContextPath()%>/admin/bills" class="btn btn-secondary">Back to Bills</a>
            <% } else { %>
                <a href="<%=request.getContextPath()%>/cashier/bills" class="btn btn-secondary">Back to Bills</a>
            <% } %>
        </div>
    </div>
</body>
</html>