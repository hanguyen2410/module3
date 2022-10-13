package controller;

import model.Product;
import service.ProductService;
import service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateForm(req,resp);
                break;
            case "edit":
                showEditForm(req,resp);
                break;
            case "delete":
                break;
            case "findByName":
                break;
            case "view":
                break;
            default:
                listProducts(req,resp);
                break;
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
    int id = Integer.parseInt(req.getParameter("id"));

    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) {
    RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
    try {
        dispatcher.forward(req,resp);
    }catch (ServletException e){
        e.printStackTrace();
    }catch (IOException e){
        e.printStackTrace();
    }
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> products = this.productService.findAll();
        req.setAttribute("products",products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
        try {
            dispatcher.forward(req,resp);
        }catch (ServletException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createProduct(req,resp);
                break;
            case "edit":
                break;
            case "delete":
                break;
            case "findByName":
                break;
            default:
                break;
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) {
    String nameProduct = req.getParameter("name");
    double price = Double.parseDouble(req.getParameter("price"));
    String description = req.getParameter("description");
    String producer = req.getParameter("producer");
    int id = (int) (Math.random() * 1000);
    Product product = new Product(id,nameProduct,price,description,producer);
    this.productService.save(product);
    RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
    req.setAttribute("message","New Product was created");
    try {
        dispatcher.forward(req,resp);
    }catch (ServletException e){
        e.printStackTrace();
    }catch (IOException e){
        e.printStackTrace();
    }
    }

}
