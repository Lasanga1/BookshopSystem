package com.pahanedu.bookshop.servlet.admin;

import com.pahanedu.bookshop.dao.BillDAO;
import com.pahanedu.bookshop.model.Bill;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminBillServlet extends HttpServlet {
    
    private BillDAO billDAO = new BillDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("view".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Bill bill = billDAO.getBillById(id);
            request.setAttribute("bill", bill);
            request.getRequestDispatcher("/jsp/common/bill-details.jsp").forward(request, response);
        } else {
            List<Bill> bills = billDAO.getAllBills();
            request.setAttribute("bills", bills);
            request.getRequestDispatcher("/jsp/admin/bills.jsp").forward(request, response);
        }
    }
}