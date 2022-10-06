package com.example.productdiscount;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DiscountProduct",urlPatterns = "/display-discount")
public class DiscountProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Float price = Float.parseFloat(req.getParameter("price"));
        Float discount = Float.parseFloat(req.getParameter("discount"));
        String productDescription = req.getParameter("product");
        double discountAmount = price * discount * 0.01;
        double discountPrice = price - discountAmount;
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<h1>Mo Ta: " + productDescription + "</h1>");
        writer.println("<h1>Gia: " + price +"</h1>");
        writer.println("<h1>Chiet Khau: " + discount + "</h1>");
        writer.println("<h1>Luong Chiet Khau: " + discountAmount + "</h1>");
        writer.println("<h1>Gia Sau niem yet: " + discountPrice + "</h1>");
        writer.println("</html>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
