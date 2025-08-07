package com.pahanedu.bookshop.servlet.admin;

import com.pahanedu.bookshop.dao.UserDAO;
import com.pahanedu.bookshop.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminUserServlet extends HttpServlet {
    
    private UserDAO userDAO = new UserDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = userDAO.getUserById(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/jsp/admin/edit-user.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            userDAO.deleteUser(id);
            response.sendRedirect(request.getContextPath() + "/admin/users");
        } else {
            List<User> users = userDAO.getAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/jsp/admin/users.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            User user = new User();
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setFullName(request.getParameter("fullName"));
            user.setUserType(User.UserType.valueOf(request.getParameter("userType")));
            
            if (userDAO.createUser(user)) {
                request.setAttribute("success", "User added successfully");
            } else {
                request.setAttribute("error", "Failed to add user");
            }
        } else if ("update".equals(action)) {
            User user = new User();
            user.setId(Integer.parseInt(request.getParameter("id")));
            user.setUsername(request.getParameter("username"));
            user.setFullName(request.getParameter("fullName"));
            user.setUserType(User.UserType.valueOf(request.getParameter("userType")));
            
            if (userDAO.updateUser(user)) {
                request.setAttribute("success", "User updated successfully");
            } else {
                request.setAttribute("error", "Failed to update user");
            }
        }
        
        List<User> users = userDAO.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/jsp/admin/users.jsp").forward(request, response);
    }
}