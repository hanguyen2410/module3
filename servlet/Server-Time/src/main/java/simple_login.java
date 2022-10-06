import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value= {"/login"})
public class simple_login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PrintWriter write = resp.getWriter();
        write.println("<html>");
        if("admin".equals(username) && "admin".equals(password)){
            write.println("<h1>Welcome " + username + " to website</h1>");
        }else {
            write.println("<h1>Login Error</h1>");
        }
        write.println("</html>");
    }
}
