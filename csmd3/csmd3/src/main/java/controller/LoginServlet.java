package controller;

import model.User;
import service.IUserService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/dashboard/login/login.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password =  req.getParameter("password");
        try {
            User user = userService.getLogin(username);
            if(user != null){
                if(user.getPassword().equals(password)){
                    User userLogin  = user;
                    HttpSession httpSession = req.getSession(true);
                    httpSession.setAttribute("userLogin", userLogin);
                    req.setAttribute("userLogin", userLogin);
                    resp.sendRedirect("/food");
                } else {
                    req.setAttribute("message", "Login unsuccessful. Please log in again");
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/dashboard/login/login.jsp");
                    dispatcher.forward(req, resp);
                }
            }else{
                req.setAttribute("message", "Account does not exist. Please log in again");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/dashboard/login/login.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
