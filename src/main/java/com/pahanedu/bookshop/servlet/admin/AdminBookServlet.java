package com.pahanedu.bookshop.servlet.admin;

import com.pahanedu.bookshop.resource.factory.ProductFactoryManager;
import com.pahanedu.bookshop.model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class AdminBookServlet extends HttpServlet {
    
    private ProductFactoryManager factoryManager = new ProductFactoryManager();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Optional<Book> bookOpt = factoryManager.getBookById(id);
            if (bookOpt.isPresent()) {
                request.setAttribute("book", bookOpt.get());
            }
            request.getRequestDispatcher("/jsp/admin/edit-book.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            factoryManager.deleteBook(id);
            response.sendRedirect(request.getContextPath() + "/admin/books");
        } else {
            List<Book> books = factoryManager.getAllBooks();
            request.setAttribute("books", books);
            request.getRequestDispatcher("/jsp/admin/books.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String isbn = request.getParameter("isbn");
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            String category = request.getParameter("category");
            
            Optional<Book> bookOpt = factoryManager.createBook(title, author, isbn, price, stockQuantity, category);
            
            if (bookOpt.isPresent()) {
                request.setAttribute("success", "Book added successfully using Factory Pattern");
            } else {
                request.setAttribute("error", "Failed to add book. Please check your input data.");
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
            
            if (factoryManager.updateBook(book)) {
                request.setAttribute("success", "Book updated successfully using Factory Pattern");
            } else {
                request.setAttribute("error", "Failed to update book. Please check your input data.");
            }
        }
        
        List<Book> books = factoryManager.getAllBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/jsp/admin/books.jsp").forward(request, response);
    }
}