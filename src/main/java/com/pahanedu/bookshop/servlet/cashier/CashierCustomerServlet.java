package com.pahanedu.bookshop.servlet.cashier;

import com.pahanedu.bookshop.resource.factory.ProductFactoryManager;
import com.pahanedu.bookshop.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CashierCustomerServlet extends HttpServlet {
    
    private ProductFactoryManager factoryManager = new ProductFactoryManager();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Optional<Customer> customerOpt = factoryManager.getCustomerById(id);
            if (customerOpt.isPresent()) {
                request.setAttribute("customer", customerOpt.get());
            }
            request.getRequestDispatcher("/jsp/cashier/edit-customer.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            factoryManager.deleteCustomer(id);
            response.sendRedirect(request.getContextPath() + "/cashier/customers");
        } else {
            List<Customer> customers = factoryManager.getAllCustomers();
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("/jsp/cashier/customers.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String telephone = request.getParameter("telephone");
            
            Optional<Customer> customerOpt = factoryManager.createCustomer(name, address, telephone);
            
            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                request.setAttribute("success", "Customer added successfully using Factory Pattern! Account Number: " + customer.getAccountNumber());
            } else {
                request.setAttribute("error", "Failed to add customer. Please check your input data.");
            }
        } else if ("update".equals(action)) {
            Customer customer = new Customer();
            customer.setId(Integer.parseInt(request.getParameter("id")));
            customer.setAccountNumber(request.getParameter("accountNumber"));
            customer.setName(request.getParameter("name"));
            customer.setAddress(request.getParameter("address"));
            customer.setTelephone(request.getParameter("telephone"));
            
            if (factoryManager.updateCustomer(customer)) {
                request.setAttribute("success", "Customer updated successfully using Factory Pattern");
            } else {
                request.setAttribute("error", "Failed to update customer. Please check your input data.");
            }
        }
        
        List<Customer> customers = factoryManager.getAllCustomers();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/jsp/cashier/customers.jsp").forward(request, response);
    }
}