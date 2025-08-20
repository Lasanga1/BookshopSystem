package com.pahanedu.bookshop.servlet.webservice;

import com.pahanedu.bookshop.dao.UserDAO;
import com.pahanedu.bookshop.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * User Web Service Servlet for basic user operations
 * Provides web service functionality using only basic servlet technology
 */
@WebServlet("/api/users/*")
public class UserWebServiceServlet extends HttpServlet {
    
    private final UserDAO userDAO = new UserDAO();
    
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
                getAllUsers(out);
            } else if (pathInfo.equals("/count")) {
                getUserCount(out);
            } else if (pathInfo.matches("/\\d+")) {
                // Extract ID from path like /123
                int id = Integer.parseInt(pathInfo.substring(1));
                getUserById(out, id);
            } else if (pathInfo.matches("/\\d+/exists")) {
                // Extract ID from path like /123/exists
                int id = Integer.parseInt(pathInfo.substring(1, pathInfo.lastIndexOf("/")));
                checkUserExists(out, id);
            } else if (pathInfo.startsWith("/type/")) {
                // Extract user type from path like /type/ADMIN
                String userType = pathInfo.substring("/type/".length());
                getUsersByType(out, userType);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("Endpoint not found. Available endpoints: /all, /count, /{id}, /{id}/exists, /type/{userType}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("Error: " + e.getMessage());
        }
    }
    
    private void getAllUsers(PrintWriter out) {
        List<User> users = userDAO.getAllUsers();
        StringBuilder response = new StringBuilder();
        
        for (User user : users) {
            response.append("ID: ").append(user.getId())
                   .append(", Username: ").append(user.getUsername())
                   .append(", Full Name: ").append(user.getFullName())
                   .append(", Type: ").append(user.getUserType())
                   .append("\n");
        }
        
        out.println(response.toString());
    }
    
    private void getUserById(PrintWriter out, int id) {
        User user = userDAO.getUserById(id);
        if (user != null) {
            String response = "ID: " + user.getId() + 
                           ", Username: " + user.getUsername() + 
                           ", Full Name: " + user.getFullName() + 
                           ", Type: " + user.getUserType();
            out.println(response);
        } else {
            out.println("User not found with ID: " + id);
        }
    }
    
    private void checkUserExists(PrintWriter out, int id) {
        User user = userDAO.getUserById(id);
        String response = "User ID: " + id + ", Exists: " + (user != null);
        out.println(response);
    }
    
    private void getUsersByType(PrintWriter out, String userType) {
        List<User> users = userDAO.getAllUsers();
        StringBuilder response = new StringBuilder();
        int count = 0;
        
        for (User user : users) {
            if (user.getUserType() != null && 
                user.getUserType().toString().equalsIgnoreCase(userType)) {
                response.append("ID: ").append(user.getId())
                       .append(", Username: ").append(user.getUsername())
                       .append(", Full Name: ").append(user.getFullName())
                       .append(", Type: ").append(user.getUserType())
                       .append("\n");
                count++;
            }
        }
        
        if (count == 0) {
            out.println("No users found with type: " + userType);
        } else {
            out.println(response.toString());
        }
    }
    
    private void getUserCount(PrintWriter out) {
        List<User> users = userDAO.getAllUsers();
        out.println("Total users: " + users.size());
    }
}
