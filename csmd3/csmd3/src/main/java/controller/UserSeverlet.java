package controller;

import model.Food;
import model.User;
import service.IUserService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(name = "UserSeverlet",urlPatterns = "/user")
public class UserSeverlet extends HttpServlet {
private IUserService iUserService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewUser(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteFoodId(req,resp);
                    break;
                default:
                    listUser(req, resp);

            }
        }catch (SQLException sql){

        }
        catch (IOException ex) {
            throw new ServletException(ex);
        }
    }

    private void deleteFoodId(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        iUserService.deleteUser(id);
        List<User> listUser = iUserService.selectAllUsers();
        req.setAttribute("listUser", listUser);
        resp.sendRedirect("/user");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            User user = iUserService.selectUser(id);
            RequestDispatcher requestDispatcher;
            if(user ==null){
                // User khong ton tai
                requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/user.jsp");
                request.setAttribute("message", "User khong ton tai");
                List<User> list = iUserService.selectAllUsers();
                request.setAttribute("list", list);

            }else{
                requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/editUser.jsp");
                request.setAttribute("user", user);
            }

            requestDispatcher.forward(request, response);


        }catch (NumberFormatException ex){

        }


    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/user.jsp");
        List<User> listUser = iUserService.selectAllUsers();

        request.setAttribute("listUser", listUser);
        requestDispatcher.forward(request, response);
    }

    private void showNewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/createUser.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action  = req.getParameter("action");
        if(action==null){
            action="";
        }
        try{
            switch (action){
                case "create":
                    insertUser(req, resp);
                    break;
                case "edit":
                    editUser(req, resp);
                    break;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }


    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<String> errors = new ArrayList<>();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/editUser.jsp");
        User user = new User();
        try{
            int idUser = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String country = request.getParameter("country");
            String images = request.getParameter("images");

            user = iUserService.selectUser(idUser);
            boolean checkEmail  = false;
            if(user.getEmail().equals(email)){
                checkEmail = true;
            }
            user.setUsername(username);
            user.setPassword(password);
            user.setFullname(fullname);
            user.setPhone(phone);
            user.setEmail(email);
            user.setCountry(country);
            user.setImages(images);
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
            if(!constraintViolations.isEmpty()){
                for(ConstraintViolation<User> constraintViolation : constraintViolations){
                    errors.add(constraintViolation.getMessage());
                }
                request.setAttribute("user", user);
                request.setAttribute("errors", errors);
            }else{

                boolean isEmailValid = (checkEmail==true || iUserService.checkEmailExists(user.getEmail()));
                if(isEmailValid){
                    request.setAttribute("user", user);
                    errors.add("Email đã tồn tại");
                    request.setAttribute("errors", errors);

                    }
                else {
                    iUserService.updateUser(user);
                    request.setAttribute("message", "Update success!!.....");
                    List<User> listUser = iUserService.selectAllUsers();
                    request.setAttribute("listUser", listUser);
                    requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/editUser.jsp");
                    request.setAttribute("user",user);
                }

            }
            request.setAttribute("errors", errors);
            requestDispatcher.forward(request, response);
        }catch (NumberFormatException numberFormatException){
            //
            errors.add("Định dạng của country không hợp lệ");
            request.setAttribute("errors", errors);
            request.setAttribute("user", user);
            requestDispatcher.forward(request, response);
        }




    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<String> errors = new ArrayList<>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String images = request.getParameter("images");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/createUser.jsp");
        User user = new User();
        try{

            user.setUsername(username);
            user.setPassword(password);
            user.setFullname(fullname);
            user.setPhone(phone);
            user.setEmail(email);
            user.setCountry(country);
            user.setImages(images);
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

            if(!constraintViolations.isEmpty()){
                for(ConstraintViolation<User> constraintViolation : constraintViolations){
                    errors.add(constraintViolation.getMessage());
                }
                request.setAttribute("user", user);
                request.setAttribute("errors", errors);
            }else{
                // Không có lỗi
                // Kiểm tra email có tồn tại hay chưa
                if(iUserService.checkEmailExists(email)){
                    //Da ton tai email
                    request.setAttribute("user", user);
                    errors.add("Email đã tồn tại");
                }else{
                    iUserService.insertUser(user);
                    request.setAttribute("message", "Insert success!!.....");
                }

            }
            request.setAttribute("errors", errors);
            requestDispatcher.forward(request, response);
        }catch (NumberFormatException numberFormatException){
            //
            errors.add("Định dạng của country không hợp lệ");
            request.setAttribute("errors", errors);
            request.setAttribute("user", user);
            requestDispatcher.forward(request, response);
        }


    }

    @Override
    public void init() throws ServletException {
        iUserService = new UserService();
        }

    }


