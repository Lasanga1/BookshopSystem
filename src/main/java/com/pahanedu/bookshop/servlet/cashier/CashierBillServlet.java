package com.pahanedu.bookshop.servlet.cashier;

import com.pahanedu.bookshop.dao.BillDAO;
import com.pahanedu.bookshop.dao.BookDAO;
import com.pahanedu.bookshop.dao.CustomerDAO;
import com.pahanedu.bookshop.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CashierBillServlet extends HttpServlet {
    
    private BillDAO billDAO = new BillDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private BookDAO bookDAO = new BookDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("create".equals(action)) {
            List<Customer> customers = customerDAO.getAllCustomers();
            List<Book> books = bookDAO.getAllBooks();
            request.setAttribute("customers", customers);
            request.setAttribute("books", books);
            request.getRequestDispatcher("/jsp/cashier/create-bill.jsp").forward(request, response);
        } else if ("view".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Bill bill = billDAO.getBillById(id);
            request.setAttribute("bill", bill);
            request.getRequestDispatcher("/jsp/common/bill-details.jsp").forward(request, response);
        } else {
            List<Bill> bills = billDAO.getAllBills();
            request.setAttribute("bills", bills);
            request.getRequestDispatcher("/jsp/cashier/bills.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // Get form data
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String[] bookIds = request.getParameterValues("bookId");
        String[] quantities = request.getParameterValues("quantity");
        String[] discounts = request.getParameterValues("discount");
        
        if (bookIds == null || quantities == null || discounts == null) {
            request.setAttribute("error", "Please add at least one item to the bill");
            doGet(request, response);
            return;
        }
        
        // Create bill items
        List<BillItem> billItems = new ArrayList<>();
        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal totalDiscount = BigDecimal.ZERO;
        
        for (int i = 0; i < bookIds.length; i++) {
            int bookId = Integer.parseInt(bookIds[i]);
            int quantity = Integer.parseInt(quantities[i]);
            BigDecimal discount = new BigDecimal(discounts[i]);
            
            Book book = bookDAO.getBookById(bookId);
            BigDecimal unitPrice = book.getPrice();
            BigDecimal itemTotal = unitPrice.multiply(new BigDecimal(quantity));
            BigDecimal itemDiscount = itemTotal.multiply(discount).divide(new BigDecimal(100));
            BigDecimal itemFinal = itemTotal.subtract(itemDiscount);
            
            BillItem item = new BillItem();
            item.setBookId(bookId);
            item.setQuantity(quantity);
            item.setUnitPrice(unitPrice);
            item.setDiscount(itemDiscount);
            item.setTotalPrice(itemFinal);
            
            billItems.add(item);
            subtotal = subtotal.add(itemTotal);
            totalDiscount = totalDiscount.add(itemDiscount);
        }
        
        BigDecimal totalAmount = subtotal.subtract(totalDiscount);
        
        // Create bill
        Bill bill = new Bill();
        bill.setBillNumber(billDAO.generateBillNumber());
        bill.setCustomerId(customerId);
        bill.setCashierId(user.getId());
        bill.setSubtotal(subtotal);
        bill.setTotalDiscount(totalDiscount);
        bill.setTotalAmount(totalAmount);
        
        if (billDAO.createBill(bill, billItems)) {
            // Get the created bill for display
            response.sendRedirect(request.getContextPath() + "/cashier/bills?success=Bill created successfully");
        } else {
            request.setAttribute("error", "Failed to create bill");
            doGet(request, response);
        }
    }
}