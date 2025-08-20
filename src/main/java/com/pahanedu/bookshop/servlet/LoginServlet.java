package com.pahanedu.bookshop.servlet;

import com.pahanedu.bookshop.dao.UserDAO;
import com.pahanedu.bookshop.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    
    private UserDAO userDAO = new UserDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User user = userDAO.authenticate(username, password);
        
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
            // Redirect based on user type
            if (user.getUserType() == User.UserType.ADMIN) {
                response.sendRedirect(request.getContextPath() + "/jsp/admin/dashboard.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/jsp/cashier/dashboard.jsp");
            }
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
}