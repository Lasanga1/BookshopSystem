package com.pahanedu.bookshop.servlet.admin;

import com.pahanedu.bookshop.dao.BookDAO;
import com.pahanedu.bookshop.model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class AdminBookServlet extends HttpServlet {
    
    private BookDAO bookDAO = new BookDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Book book = bookDAO.getBookById(id);
            request.setAttribute("book", book);
            request.getRequestDispatcher("/jsp/admin/edit-book.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            bookDAO.deleteBook(id);
            response.sendRedirect(request.getContextPath() + "/admin/books");
        } else {
            List<Book> books = bookDAO.getAllBooks();
            request.setAttribute("books", books);
            request.getRequestDispatcher("/jsp/admin/books.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            Book book = new Book();
            book.setTitle(request.getParameter("title"));
            book.setAuthor(request.getParameter("author"));
            book.setIsbn(request.getParameter("isbn"));
            book.setPrice(new BigDecimal(request.getParameter("price")));
            book.setStockQuantity(Integer.parseInt(request.getParameter("stockQuantity")));
            book.setCategory(request.getParameter("category"));
            
            if (bookDAO.createBook(book)) {
                request.setAttribute("success", "Book added successfully");
            } else {
                request.setAttribute("error", "Failed to add book");
            }
        } else if ("update".equals(action)) {
            Book book = new Book();
            book.setId(Integer.parseInt(request.getParameter("id")));
            book.setTitle(request.getParameter("title"));
            book.setAuthor(request.getParameter("author"));
            book.setIsbn(request.getParameter("isbn"));
            book.setPrice(new BigDecimal(request.getParameter("price")));
            book.setStockQuantity(Integer.parseInt(request.getParameter("stockQuantity")));
            book.setCategory(request.getParameter("category"));
            
            if (bookDAO.updateBook(book)) {
                request.setAttribute("success", "Book updated successfully");
            } else {
                request.setAttribute("error", "Failed to update book");
            }
        }
        
        List<Book> books = bookDAO.getAllBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/jsp/admin/books.jsp").forward(request, response);
    }
}