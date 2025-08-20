<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanedu.bookshop.model.User" %>
<%@ page import="com.pahanedu.bookshop.model.Customer" %>
<%@ page import="com.pahanedu.bookshop.model.Book" %>
<%@ page import="java.util.List" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getUserType() != User.UserType.CASHIER) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
    List<Book> books = (List<Book>) request.getAttribute("books");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Bill - Pahan Edu Book Shop</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <script>
        function addItem() {
            const container = document.getElementById('items-container');
            const itemCount = container.children.length;
            
            const itemRow = document.createElement('div');
            itemRow.className = 'item-row';
            itemRow.innerHTML = `
                <div class="form-group">
                    <label class="form-label">Book:</label>
                    <select name="bookId" class="form-select" required>
                        <option value="">Select Book</option>
                        <% if (books != null) { %>
                            <% for (Book book : books) { %>
                                <option value="<%= book.getId() %>" data-price="<%= book.getPrice() %>" data-stock="<%= book.getStockQuantity() %>">
                                    <%= book.getTitle() %> - Rs. <%= book.getPrice() %> (Stock: <%= book.getStockQuantity() %>)
                                </option>
                            <% } %>
                        <% } %>
                    </select>
                </div>
                
                <div class="form-group">
                    <label class="form-label">Quantity:</label>
                    <input type="number" name="quantity" class="form-input" min="1" required onchange="calculateItemTotal(this)">
                </div>
                
                <div class="form-group">
                    <label class="form-label">Discount (%):</label>
                    <input type="number" name="discount" class="form-input" min="0" max="100" value="0" step="0.01" onchange="calculateItemTotal(this)">
                </div>
                
                <div class="form-group">
                    <label class="form-label">Total:</label>
                    <input type="text" class="form-input item-total" readonly>
                </div>
                
                <div class="form-group">
                    <button type="button" class="btn btn-danger" onclick="removeItem(this)">Remove</button>
                </div>
            `;
            
            container.appendChild(itemRow);
        }
        
        function removeItem(button) {
            button.closest('.item-row').remove();
            calculateGrandTotal();
        }
        
        function calculateItemTotal(input) {
            const row = input.closest('.item-row');
            const bookSelect = row.querySelector('select[name="bookId"]');
            const quantityInput = row.querySelector('input[name="quantity"]');
            const discountInput = row.querySelector('input[name="discount"]');
            const totalInput = row.querySelector('.item-total');
            
            if (bookSelect.value && quantityInput.value) {
                const price = parseFloat(bookSelect.selectedOptions[0].getAttribute('data-price'));
                const quantity = parseInt(quantityInput.value);
                const discount = parseFloat(discountInput.value) || 0;
                
                const subtotal = price * quantity;
                const discountAmount = (subtotal * discount) / 100;
                const total = subtotal - discountAmount;
                
                totalInput.value = 'Rs. ' + total.toFixed(2);
            }
            
            calculateGrandTotal();
        }
        
        function calculateGrandTotal() {
            const itemTotals = document.querySelectorAll('.item-total');
            let grandTotal = 0;
            
            itemTotals.forEach(input => {
                if (input.value) {
                    const value = parseFloat(input.value.replace('Rs. ', ''));
                    if (!isNaN(value)) {
                        grandTotal += value;
                    }
                }
            });
            
            document.getElementById('grand-total').textContent = 'Rs. ' + grandTotal.toFixed(2);
        }
    </script>
</head>
<body>
    <div class="header">
        <div class="container">
            <h1>Create New Bill</h1>
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
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        
        <div class="card">
            <div class="card-header">
                <h2 class="card-title">Bill Information</h2>
            </div>
            
            <form action="<%=request.getContextPath()%>/cashier/bills" method="post">
                <div class="form-group">
                    <label for="customerId" class="form-label">Customer:</label>
                    <select id="customerId" name="customerId" class="form-select" required>
                        <option value="">Select Customer</option>
                        <% if (customers != null) { %>
                            <% for (Customer customer : customers) { %>
                                <option value="<%= customer.getId() %>">
                                    <%= customer.getAccountNumber() %> - <%= customer.getName() %>
                                </option>
                            <% } %>
                        <% } %>
                    </select>
                </div>
                
                <div class="card-header">
                    <h3>Bill Items</h3>
                    <button type="button" class="btn btn-secondary" onclick="addItem()">Add Item</button>
                </div>
                
                <div id="items-container">
                    <!-- Items will be added here dynamically -->
                </div>
                
                <div class="bill-total">
                    <div class="total-row">
                        <span><strong>Grand Total: <span id="grand-total">Rs. 0.00</span></strong></span>
                    </div>
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn btn-success">Create Bill</button>
                    <a href="<%=request.getContextPath()%>/cashier/bills" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
    
    <script>
        // Add first item by default
        addItem();
    </script>
</body>
</html>