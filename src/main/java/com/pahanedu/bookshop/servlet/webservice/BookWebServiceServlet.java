package com.pahanedu.bookshop.servlet.webservice;

import com.pahanedu.bookshop.dao.BookDAO;
import com.pahanedu.bookshop.model.Book;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Book Web Service Servlet for basic book operations
 * Provides web service functionality using only basic servlet technology
 */
@WebServlet("/api/books/*")
public class BookWebServiceServlet extends HttpServlet {
    
    private final BookDAO bookDAO = new BookDAO();
    
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
                getAllBooks(out);
            } else if (pathInfo.equals("/count")) {
                getBookCount(out);
            } else if (pathInfo.matches("/\\d+")) {
                // Extract ID from path like /123
                int id = Integer.parseInt(pathInfo.substring(1));
                getBookById(out, id);
            } else if (pathInfo.matches("/\\d+/stock")) {
                // Extract ID from path like /123/stock
                int id = Integer.parseInt(pathInfo.substring(1, pathInfo.lastIndexOf("/")));
                String quantityStr = request.getParameter("quantity");
                int quantity = quantityStr != null ? Integer.parseInt(quantityStr) : 1;
                checkStock(out, id, quantity);
            } else if (pathInfo.startsWith("/category/")) {
                // Extract category from path like /category/Fiction
                String category = pathInfo.substring("/category/".length());
                getBooksByCategory(out, category);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("Endpoint not found. Available endpoints: /all, /count, /{id}, /{id}/stock, /category/{category}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("Error: " + e.getMessage());
        }
    }
    
    private void getAllBooks(PrintWriter out) {
        List<Book> books = bookDAO.getAllBooks();
        StringBuilder response = new StringBuilder();
        
        for (Book book : books) {
            response.append("ID: ").append(book.getId())
                   .append(", Title: ").append(book.getTitle())
                   .append(", Author: ").append(book.getAuthor())
                   .append(", Price: ").append(book.getPrice())
                   .append(", Stock: ").append(book.getStockQuantity())
                   .append("\n");
        }
        
        out.println(response.toString());
    }
    
    private void getBookById(PrintWriter out, int id) {
        Book book = bookDAO.getBookById(id);
        if (book != null) {
            String response = "ID: " + book.getId() + 
                           ", Title: " + book.getTitle() + 
                           ", Author: " + book.getAuthor() + 
                           ", Price: " + book.getPrice() + 
                           ", Stock: " + book.getStockQuantity();
            out.println(response);
        } else {
            out.println("Book not found with ID: " + id);
        }
    }
    
    private void checkStock(PrintWriter out, int id, int quantity) {
        Book book = bookDAO.getBookById(id);
        if (book != null) {
            boolean inStock = book.getStockQuantity() >= quantity;
            String response = "Book ID: " + id + 
                           ", Required: " + quantity + 
                           ", Available: " + book.getStockQuantity() + 
                           ", In Stock: " + inStock;
            out.println(response);
        } else {
            out.println("Book not found with ID: " + id);
        }
    }
    
    private void getBooksByCategory(PrintWriter out, String category) {
        List<Book> books = bookDAO.getAllBooks();
        StringBuilder response = new StringBuilder();
        int count = 0;
        
        for (Book book : books) {
            if (book.getCategory() != null && 
                book.getCategory().toLowerCase().contains(category.toLowerCase())) {
                response.append("ID: ").append(book.getId())
                       .append(", Title: ").append(book.getTitle())
                       .append(", Author: ").append(book.getAuthor())
                       .append(", Category: ").append(book.getCategory())
                       .append("\n");
                count++;
            }
        }
        
        if (count == 0) {
            out.println("No books found in category: " + category);
        } else {
            out.println(response.toString());
        }
    }
    
    private void getBookCount(PrintWriter out) {
        List<Book> books = bookDAO.getAllBooks();
        out.println("Total books: " + books.size());
    }
}
