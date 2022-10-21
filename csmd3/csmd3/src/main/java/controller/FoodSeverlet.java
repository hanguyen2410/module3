package controller;

import model.Food;
import model.Type;
import service.FoodService;
import service.IFoodService;
import service.IType;
import service.TypeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(name="FoodServlet", urlPatterns = "/food")
public class FoodSeverlet extends HttpServlet {
    private IFoodService iFoodService;
    private IType iType;

    @Override
    public void init() throws ServletException {
        iFoodService = new FoodService();
        iType = new TypeService();

        List<Type> typeList = iType.selectAllType();

        if(this.getServletContext().getAttribute("listType")==null){
            this.getServletContext().setAttribute("listType", typeList);
        }
    }
    private void listFoodPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 3;
        String q = "";
        int type = -1;
        if (req.getParameter("q") != null) {
            q = req.getParameter("q");
        }
        if (req.getParameter("type") != null) {
            type = Integer.parseInt(req.getParameter("type"));
        }
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));
        List<Food> foodList = iFoodService.selectAllFoodsPaggingFilter((page - 1) * recordsPerPage, recordsPerPage, q, type);
        int noOfRecords = iFoodService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        System.out.println(getClass() + " listFoodPage " + foodList);
        req.setAttribute("foodList", foodList);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("q", q);
        req.setAttribute("type", type);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/dashboard/food/food.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        try {
            switch (action){
                case "create":
                    showNewForm(req,resp);
                    break;
                case "edit":
                    showEditForm(req,resp);
                    break;
                case "delete":
                    deleteFoodId(req,resp);
                    break;
                default:
                    listFoodPage(req,resp);
                    break;
            }
        }catch (IOException | SQLException ex){
            throw new ServletException(ex);
        }
    }

    private void deleteFoodId(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        iFoodService.deleteFood(id);
        List<Food> foodList = iFoodService.findAll();
        req.setAttribute("foodList", foodList);
        resp.sendRedirect("/food");
    }

    private void listFood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher =req.getRequestDispatcher("/WEB-INF/dashboard/food/food.jsp");
        List<Food>  foodList = iFoodService.findAll();
        req.setAttribute("list", foodList);
        requestDispatcher.forward(req,resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
        int id = Integer.parseInt(req.getParameter("id"));
        Food food = iFoodService.selectFood(id);
        RequestDispatcher requestDispatcher;
        if(food == null){
            requestDispatcher = req.getRequestDispatcher("/WEB-INF/dashboard/food/food.jsp");
            req.setAttribute("message", "Món ăn không tồn tại");
            List<Food> foodList = iFoodService.findAll();
            req.setAttribute("list",foodList);
        }else {
            requestDispatcher = req.getRequestDispatcher("/WEB-INF/dashboard/edit.jsp");
            req.setAttribute("food",food);
        }
        requestDispatcher.forward(req,resp);
    }catch (NumberFormatException ex){

    }
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/dashboard/createfood.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        try{
            switch (action){
                case "create":
                    insertFood(req,resp);
                    break;
                case "edit":
                    editFood(req,resp);
                    break;
                default:
                    break;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }



    private void editFood(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
            List<String> errors = new ArrayList<>();
            RequestDispatcher requestDispatcher =req.getRequestDispatcher("/WEB-INF/dashboard/edit.jsp");
            Food food = new Food();
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                String nameFood = req.getParameter("nameFood");
                Double price = Double.parseDouble(req.getParameter("price"));
                String descrip = req.getParameter("descrip");
                String images = req.getParameter("images");
                int idType = Integer.parseInt(req.getParameter("type"));
                food = iFoodService.selectFood(id);
                boolean checkFood = false;
                if(food.getNameFood().equals(nameFood)){
                    checkFood = true;
                }
                food.setNameFood(nameFood);
                food.setPrice(price);
                food.setDescription(descrip);
                food.setImages(images);
                food.setType(idType);
                ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
                Validator validator = validatorFactory.getValidator();
                Set<ConstraintViolation<Food>> constraintViolations = validator.validate(food);
                if(!constraintViolations.isEmpty()){
                    for(ConstraintViolation<Food> constraintViolation : constraintViolations){
                        errors.add(constraintViolation.getMessage());
                    }
                    req.setAttribute("food",food);
                    req.setAttribute("errors",errors);
                }else{
                    Type type = iType.selectType(idType);
                    boolean isNameFoodValid = (checkFood == true|| !iFoodService.checkNameFood(food.getNameFood()));
                    if(isNameFoodValid){
                        if(type == null){
                            errors.add("Type không hợp lệ");
                            req.setAttribute("errors",errors);
                        }else {
                            iFoodService.updateFood(food);
                            req.setAttribute("message", "Update success!!.....");
                            List<Food> listFood = iFoodService.findAll();
                            req.setAttribute("list", listFood);
                            requestDispatcher = req.getRequestDispatcher("/WEB-INF/dashboard/edit.jsp");
                            req.setAttribute("food", food);
                        }
                    }else {
                        if(type == null){
                            errors.add("Type không hợp lệ");
                            req.setAttribute("errors",errors);
                        }
                        req.setAttribute("food",food);
                        errors.add("Tên món ăn đã tồn tại");
                        req.setAttribute("errors",errors);
                    }
                }
                requestDispatcher.forward(req,resp);
            }catch (NumberFormatException numberFormatException){
            errors.add("Định dạng của type không hợp lệ");
            req.setAttribute("errors",errors);
            req.setAttribute("food",food);
            requestDispatcher.forward(req,resp);
            }
    }

    private void insertFood(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<String> errors = new ArrayList<>();

        Food food = new Food();
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/dashboard/createfood.jsp");
        try {

            String nameFood = req.getParameter("nameFood").trim();
            Double price = Double.parseDouble(req.getParameter("price"));
            String descrip = req.getParameter("descrip").trim();
            String images = req.getParameter("images");
            food.setNameFood(nameFood);
            food.setPrice(price);
            food.setDescription(descrip);
            food.setImages(images);
            int type = Integer.parseInt(req.getParameter("type"));
            food.setType(type);
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Food>> constraintViolations = validator.validate(food);
            if(!constraintViolations.isEmpty()){
                for(ConstraintViolation<Food> constraintViolation : constraintViolations){
                    errors.add(constraintViolation.getMessage());
                }
                req.setAttribute("food",food);

            }else {
                if(iFoodService.checkNameFood(nameFood)){
                    req.setAttribute("food",food);
                    errors.add("Tên món ăn đã tồn tại");
                }else {
                    iFoodService.insertFood(food);
                    req.setAttribute("message","Thêm món ăn thành công!!");
                }
            }
            req.setAttribute("errors",errors);
            requestDispatcher.forward(req,resp);
        }catch (NumberFormatException numberFormatException){
            errors.add("Định dạng type không đúng");
            req.setAttribute("errors",errors);
            req.setAttribute("food",food);
            requestDispatcher.forward(req,resp);
        }
    }
}
