package com.pahanedu.bookshop.servlet.cashier;

import com.pahanedu.bookshop.dao.CustomerDAO;
import com.pahanedu.bookshop.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CashierCustomerServlet extends HttpServlet {
    
    private CustomerDAO customerDAO = new CustomerDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Customer customer = customerDAO.getCustomerById(id);
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("/jsp/cashier/edit-customer.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            customerDAO.deleteCustomer(id);
            response.sendRedirect(request.getContextPath() + "/cashier/customers");
        } else {
            List<Customer> customers = customerDAO.getAllCustomers();
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("/jsp/cashier/customers.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            Customer customer = new Customer();
            customer.setAccountNumber(request.getParameter("accountNumber"));
            customer.setName(request.getParameter("name"));
            customer.setAddress(request.getParameter("address"));
            customer.setTelephone(request.getParameter("telephone"));
            
            if (customerDAO.createCustomer(customer)) {
                request.setAttribute("success", "Customer added successfully");
            } else {
                request.setAttribute("error", "Failed to add customer");
            }
        } else if ("update".equals(action)) {
            Customer customer = new Customer();
            customer.setId(Integer.parseInt(request.getParameter("id")));
            customer.setAccountNumber(request.getParameter("accountNumber"));
            customer.setName(request.getParameter("name"));
            customer.setAddress(request.getParameter("address"));
            customer.setTelephone(request.getParameter("telephone"));
            
            if (customerDAO.updateCustomer(customer)) {
                request.setAttribute("success", "Customer updated successfully");
            } else {
                request.setAttribute("error", "Failed to update customer");
            }
        }
        
        List<Customer> customers = customerDAO.getAllCustomers();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/jsp/cashier/customers.jsp").forward(request, response);
    }
}