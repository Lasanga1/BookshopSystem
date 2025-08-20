package com.pahanedu.bookshop.servlet.admin;

import com.pahanedu.bookshop.resource.factory.ProductFactoryManager;
import com.pahanedu.bookshop.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AdminUserServlet extends HttpServlet {
    
    private ProductFactoryManager factoryManager = new ProductFactoryManager();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Optional<User> userOpt = factoryManager.getUserById(id);
            if (userOpt.isPresent()) {
                request.setAttribute("user", userOpt.get());
            }
            request.getRequestDispatcher("/jsp/admin/edit-user.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            factoryManager.deleteUser(id);
            response.sendRedirect(request.getContextPath() + "/admin/users");
        } else {
            List<User> users = factoryManager.getAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/jsp/admin/users.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullName");
            User.UserType userType = User.UserType.valueOf(request.getParameter("userType"));
            
            Optional<User> userOpt = factoryManager.createUser(username, password, fullName, userType);
            
            if (userOpt.isPresent()) {
                request.setAttribute("success", "User added successfully using Factory Pattern");
            } else {
                request.setAttribute("error", "Failed to add user. Please check your input data.");
            }
        } else if ("update".equals(action)) {
            User user = new User();
            user.setId(Integer.parseInt(request.getParameter("id")));
            user.setUsername(request.getParameter("username"));
            user.setFullName(request.getParameter("fullName"));
            user.setUserType(User.UserType.valueOf(request.getParameter("userType")));
            // Handle password update if provided
            String password = request.getParameter("password");
            if (password != null && !password.trim().isEmpty()) {
                user.setPassword(password);
            }
            // updateUser should handle password update if set
            if (factoryManager.updateUser(user)) {
                request.setAttribute("success", "User updated successfully using Factory Pattern");
            } else {
                request.setAttribute("error", "Failed to update user. Please check your input data.");
            }
        }
        
        List<User> users = factoryManager.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/jsp/admin/users.jsp").forward(request, response);
    }
}