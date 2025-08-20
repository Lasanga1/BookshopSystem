package com.pahanedu.bookshop.servlet.webservice;

import com.pahanedu.bookshop.dao.CustomerDAO;
import com.pahanedu.bookshop.model.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Customer Web Service Servlet for basic customer operations
 * Provides web service functionality using only basic servlet technology
 */
@WebServlet("/api/customers/*")
public class CustomerWebServiceServlet extends HttpServlet {
    
    private final CustomerDAO customerDAO = new CustomerDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/";
        }
        
        try {
            if (pathInfo.equals("/all")) {
                getAllCustomers(out);
            } else if (pathInfo.equals("/count")) {
                getCustomerCount(out);
            } else if (pathInfo.matches("/\\d+")) {
                // Extract ID from path like /123
                int id = Integer.parseInt(pathInfo.substring(1));
                getCustomerById(out, id);
            } else if (pathInfo.matches("/\\d+/exists")) {
                // Extract ID from path like /123/exists
                int id = Integer.parseInt(pathInfo.substring(1, pathInfo.lastIndexOf("/")));
                checkCustomerExists(out, id);
            } else if (pathInfo.startsWith("/account/")) {
                // Extract account number from path like /account/CUST123
                String accountNumber = pathInfo.substring("/account/".length());
                getCustomerByAccount(out, accountNumber);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("Endpoint not found. Available endpoints: /all, /count, /{id}, /{id}/exists, /account/{accountNumber}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("Error: " + e.getMessage());
        }
    }
    
    private void getAllCustomers(PrintWriter out) {
        List<Customer> customers = customerDAO.getAllCustomers();
        StringBuilder response = new StringBuilder();
        
        for (Customer customer : customers) {
            response.append("ID: ").append(customer.getId())
                   .append(", Name: ").append(customer.getName())
                   .append(", Account: ").append(customer.getAccountNumber())
                   .append(", Address: ").append(customer.getAddress())
                   .append(", Phone: ").append(customer.getTelephone())
                   .append("\n");
        }
        
        out.println(response.toString());
    }
    
    private void getCustomerById(PrintWriter out, int id) {
        Customer customer = customerDAO.getCustomerById(id);
        if (customer != null) {
            String response = "ID: " + customer.getId() + 
                           ", Name: " + customer.getName() + 
                           ", Account: " + customer.getAccountNumber() + 
                           ", Address: " + customer.getAddress() + 
                           ", Phone: " + customer.getTelephone();
            out.println(response);
        } else {
            out.println("Customer not found with ID: " + id);
        }
    }
    
    private void checkCustomerExists(PrintWriter out, int id) {
        Customer customer = customerDAO.getCustomerById(id);
        String response = "Customer ID: " + id + ", Exists: " + (customer != null);
        out.println(response);
    }
    
    private void getCustomerByAccount(PrintWriter out, String accountNumber) {
        List<Customer> customers = customerDAO.getAllCustomers();
        
        for (Customer customer : customers) {
            if (customer.getAccountNumber() != null && 
                customer.getAccountNumber().equals(accountNumber)) {
                String response = "ID: " + customer.getId() + 
                               ", Name: " + customer.getName() + 
                               ", Account: " + customer.getAccountNumber() + 
                               ", Address: " + customer.getAddress() + 
                               ", Phone: " + customer.getTelephone();
                out.println(response);
                return;
            }
        }
        
        out.println("Customer not found with account: " + accountNumber);
    }
    
    private void getCustomerCount(PrintWriter out) {
        List<Customer> customers = customerDAO.getAllCustomers();
        out.println("Total customers: " + customers.size());
    }
}
